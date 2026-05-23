package com.diversidade.inclusao.repository;

import com.diversidade.inclusao.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositório de vagas de emprego.
 */
public interface VagaRepository extends JpaRepository<Vaga, Long> {
    List<Vaga> findByStatus(String status);
    List<Vaga> findByDepartamentoId(Long departamentoId);
}