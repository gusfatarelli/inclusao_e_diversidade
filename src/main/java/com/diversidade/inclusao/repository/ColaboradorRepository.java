package com.diversidade.inclusao.repository;

import com.diversidade.inclusao.entity.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório de colaboradores.
 */
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
}