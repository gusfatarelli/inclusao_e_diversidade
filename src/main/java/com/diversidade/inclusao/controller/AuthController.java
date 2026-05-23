package com.diversidade.inclusao.controller;

import com.diversidade.inclusao.dto.AuthenticationRequest;
import com.diversidade.inclusao.dto.AuthenticationResponse;
import com.diversidade.inclusao.dto.RegisterRequest;
import com.diversidade.inclusao.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador responsável pelos endpoints de autenticação e registro. Permite
 * criar um novo usuário e realizar login, retornando um token JWT válido
 * que deve ser incluído no cabeçalho Authorization das requisições seguintes.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    /**
     * Endpoint para registrar um novo usuário.
     *
     * @param request dados de registro
     * @return token JWT para o usuário recém-criado
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    /**
     * Endpoint de login. Recebe credenciais de autenticação e retorna
     * um token JWT se o usuário estiver autenticado.
     *
     * @param request credenciais de login
     * @return token JWT
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}