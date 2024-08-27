package br.com.helio.vollmed.domain.paciente;

import br.com.helio.vollmed.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "paciente")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private  String telefone;
    @Embedded
    private Endereco endereco;

    private  boolean ativo;

    public Paciente(DadosCadastroPaciente dadosPaciente) {
        this.ativo = true;
        this.nome = dadosPaciente.nome();
        this.email = dadosPaciente.email();
        this.cpf = dadosPaciente.cpf();
        this.telefone = dadosPaciente.telefone();
        this.endereco = new Endereco(dadosPaciente.endereco());

    }

    public void atualizarInformacoes(DadosAtualizacaoPaciente dadosPaciente) {
        if(dadosPaciente.nome() != null){
            this.nome = dadosPaciente.nome();
        }

        if(dadosPaciente.telefone() != null){
            this.telefone = dadosPaciente.telefone();
        }

        if(dadosPaciente.endereco() != null){
            this.endereco.atualizarInformacoes(dadosPaciente.endereco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
