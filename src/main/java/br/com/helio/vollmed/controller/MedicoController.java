package br.com.helio.vollmed.controller;

import br.com.helio.vollmed.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repository; // Injeta o repositório de Médicos
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
		// Cadastra um novo médico
		repository.save(new Medico(dados));
	}
	@GetMapping
	public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
			// Lista todos os médicos ativos com paginação
			return  repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
		}
	@PutMapping
	@Transactional
		public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
		// Atualiza informações de um médico existente
			var medico = repository.getReferenceById(dados.id());
			medico.atualizarInformacoes(dados);
	}

	//@DeleteMapping("/{id}")
	//@Transactional
	//public void excluir(@PathVariable Long id){
	//	repository.deleteById(id);
	//}

	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		// Marca um médico como inativo (exclusão lógica)
		var medico = repository.getReferenceById(id);
		medico.excluir();
	}
}
