package br.com.helio.vollmed.paciente;

import br.com.helio.vollmed.endereco.DadosEndereco;

public record DadosCadastroPaciente(String nome, String email,String cpf,  DadosEndereco endereco) {

}
