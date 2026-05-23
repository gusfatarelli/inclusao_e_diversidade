package com.diversidade.inclusao.controller;

import com.diversidade.inclusao.dto.CandidatoDTO;
import com.diversidade.inclusao.dto.CreateCandidatoDTO;
import com.diversidade.inclusao.service.CandidatoService;
import org.springframework.security.access.prepost.PreAuthorize;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para candidatos.
 */
@RestController
@RequestMapping("/api/candidatos")
@Validated
public class CandidatoController {
    private final CandidatoService candidatoService;

    public CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public CandidatoDTO criar(@Valid @RequestBody CreateCandidatoDTO dto) {
        return candidatoService.inscrever(dto);
    }

    @GetMapping("/vaga/{vagaId}")
    @PreAuthorize("hasAnyRole('ADMIN','HR','USER')")
    public List<CandidatoDTO> listarPorVaga(@PathVariable Long vagaId) {
        return candidatoService.listarPorVaga(vagaId);
    }
}