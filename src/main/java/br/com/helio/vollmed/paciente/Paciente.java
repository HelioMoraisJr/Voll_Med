package br.com.helio.vollmed.paciente;

import br.com.helio.vollmed.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "paciente")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private  String telefone;
    private Endereco endereco;

    private  boolean ativo;

    public Paciente(DadosCadastroPaciente dadosPaciente) {
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
