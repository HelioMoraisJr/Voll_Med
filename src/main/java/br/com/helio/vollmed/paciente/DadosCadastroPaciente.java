package br.com.helio.vollmed.paciente;

import br.com.helio.vollmed.endereco.DadosEndereco;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroPaciente(

        @NotBlank
        String nome,

        @NotBlank
        String email,


        String cpf,

        @NotBlank
        String telefone,

        @NotNull @Valid DadosEndereco endereco) {

}
