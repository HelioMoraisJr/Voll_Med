package br.com.helio.vollmed.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicoRepository extends JpaRepository<Medico, Long> {
    // Método para encontrar todos os médicos ativos com paginação
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
