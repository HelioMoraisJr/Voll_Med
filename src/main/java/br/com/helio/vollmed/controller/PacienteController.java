package br.com.helio.vollmed.controller;

import br.com.helio.vollmed.medico.DadosAtualizacaoMedico;
import br.com.helio.vollmed.paciente.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("paciente")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;

	@PostMapping
	@Transactional
	public void cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dadosPaciente) {
		repository.save(new Paciente(dadosPaciente));
	}

	@GetMapping
	public Page<DadosListagemPaciente> ListarPaciente(@PageableDefault(size = 10, sort = {"nome"})Pageable paginacao){
		return repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
	}

	@PutMapping
	@Transactional
	public void atualizarPaciente(@RequestBody @Valid DadosAtualizacaoPaciente dadosPaciente){
		var paciente = repository.getReferenceById(dadosPaciente.id());
		paciente.atualizarInformacoes(dadosPaciente);
	}

	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id){
		var paciente = repository.getReferenceById(id);
		paciente.excluir();
	}

}
