package br.com.helio.vollmed.controller;

import br.com.helio.vollmed.domain.medico.*;
import br.com.helio.vollmed.domain.medico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

	@Autowired
	private MedicoRepository repository; // Injeta o repositório de Médicos
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
		// Cadastra um novo médico
		var medico = new Medico(dados);
		repository.save(new Medico(dados));

		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao){
			// Lista todos os médicos ativos com paginação
			var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
			return ResponseEntity.ok(page);
		}
	@PutMapping
	@Transactional
		public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
		// Atualiza informações de um médico existente
			var medico = repository.getReferenceById(dados.id());
			medico.atualizarInformacoes(dados);

			return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

	//@DeleteMapping("/{id}")
	//@Transactional
	//public void excluir(@PathVariable Long id){
	//	repository.deleteById(id);
	//}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		// Marca um médico como inativo (exclusão lógica)
		var medico = repository.getReferenceById(id);
		medico.excluir();

		return ResponseEntity.noContent().build();
	}

	@GetMapping ("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		var medico = repository.getReferenceById(id);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

}
