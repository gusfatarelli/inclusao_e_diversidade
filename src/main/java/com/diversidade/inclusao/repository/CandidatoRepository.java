package com.diversidade.inclusao.repository;

import com.diversidade.inclusao.entity.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositório de candidatos.
 */
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    List<Candidato> findByVagaIdOrderByScoreDiversidadeDesc(Long vagaId);
}