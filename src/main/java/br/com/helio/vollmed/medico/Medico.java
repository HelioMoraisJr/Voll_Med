package br.com.helio.vollmed.medico;

import br.com.helio.vollmed.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos") //especificar a tabela no banco de dados
@Entity(name = "Medicos") //Esta anotação define que a classe é uma entidade JPA, ou seja, ela representa uma tabela no banco de dados
@Getter //Esta anotação vem do projeto Lombok e é usada para gerar automaticamente métodos getters
@NoArgsConstructor //Também do Lombok, esta anotação gera um construtor sem argumentos para a classe. É útil para frameworks que exigem um construtor padrão, como o JPA.
@AllArgsConstructor //esta anotação do Lombok gera um construtor com um argumento para cada campo da classe. Isso é útil para criar rapidamente instâncias da classe com todos os campos preenchidos.

public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    private  boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.crm = dados.crm();
        this.telefone = dados.telefone();
        this.especialidade = dados.especialidade();
        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }

        if(dados.telefone() != null){
            this.telefone = dados.telefone();
        }

        if(dados.endereco() != null){
            this.endereco.atualizarInformacoes(dados.endereco());
        }

    }

    public void excluir() {
        this.ativo = false;
    }
}
