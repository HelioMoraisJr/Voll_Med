package br.com.helio.vollmed.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.helio.vollmed.paciente.DadosCadastroPaciente;

@RestController
@RequestMapping("paciente")
public class PacienteController {
	
	@PostMapping
	public void pacienteCadastro(@RequestBody DadosCadastroPaciente dadosPaciente) {
		
		System.out.println(dadosPaciente);
		
	}

}
