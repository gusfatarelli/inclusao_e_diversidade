package com.diversidade.inclusao.repository;

import com.diversidade.inclusao.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório de departamentos.
 */
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
}