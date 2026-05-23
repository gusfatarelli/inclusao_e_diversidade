package com.diversidade.inclusao.repository;

import com.diversidade.inclusao.entity.TreinamentoLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositório de logs de treinamento.
 */
public interface TreinamentoLogRepository extends JpaRepository<TreinamentoLog, Long> {
    List<TreinamentoLog> findByColaboradorId(Long colaboradorId);
}