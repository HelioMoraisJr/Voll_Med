package br.com.helio.vollmed.paciente;

import br.com.helio.vollmed.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(
    @NotNull
    Long id,
    String nome,
    String telefone,
    DadosEndereco endereco){

}
