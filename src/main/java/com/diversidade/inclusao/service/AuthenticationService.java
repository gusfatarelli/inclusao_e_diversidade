package com.diversidade.inclusao.service;

import com.diversidade.inclusao.dto.AuthenticationRequest;
import com.diversidade.inclusao.dto.AuthenticationResponse;
import com.diversidade.inclusao.dto.RegisterRequest;
import com.diversidade.inclusao.entity.Usuario;
import com.diversidade.inclusao.repository.UsuarioRepository;
import com.diversidade.inclusao.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Serviço responsável por lidar com registro de novos usuários e
 * autenticação (login). Utiliza {@link JwtService} para gerar os
 * tokens JWT e persiste os usuários no banco de dados através de
 * {@link UsuarioRepository}.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Registra um novo usuário na base de dados. A senha recebida é
     * codificada antes do armazenamento. Após o registro, um token
     * JWT é gerado e retornado ao cliente.
     *
     * @param request informações de registro
     * @return resposta contendo o token JWT
     */
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        // Codifica a senha antes de persistir
        var user = Usuario.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();
        usuarioRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    /**
     * Autentica o usuário usando o {@link AuthenticationManager}. Caso o
     * usuário e senha estejam corretos, um token JWT é gerado e
     * retornado ao cliente.
     *
     * @param request credenciais de autenticação
     * @return resposta contendo o token JWT
     * @throws AuthenticationException se as credenciais forem inválidas
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) throws AuthenticationException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        var user = (Usuario) authentication.getPrincipal();
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }
}