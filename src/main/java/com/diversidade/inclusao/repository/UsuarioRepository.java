package com.diversidade.inclusao.repository;

import com.diversidade.inclusao.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório para a entidade {@link Usuario}. Disponibiliza operações CRUD
 * e consulta por nome de usuário. Usado pelo serviço de autenticação para
 * carregar os detalhes do usuário a partir do banco de dados.
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    /**
     * Busca um usuário pelo nome de usuário (username). Retorna
     * um {@link Optional} para evitar NullPointerException.
     *
     * @param username nome de usuário
     * @return usuário correspondente, se existir
     */
    Optional<Usuario> findByUsername(String username);
}