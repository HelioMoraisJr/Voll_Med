package br.com.helio.vollmed.domain.paciente;

import br.com.helio.vollmed.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoPaciente(
    @NotNull
    Long id,
    String nome,
    String telefone,
    DadosEndereco endereco){

}
