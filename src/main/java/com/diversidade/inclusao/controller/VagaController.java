package com.diversidade.inclusao.controller;

import com.diversidade.inclusao.dto.CreateVagaDTO;
import com.diversidade.inclusao.dto.UpdateVagaStatusDTO;
import com.diversidade.inclusao.dto.VagaDTO;
import com.diversidade.inclusao.service.VagaService;
import org.springframework.security.access.prepost.PreAuthorize;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para operações de vagas.
 */
@RestController
@RequestMapping("/api/vagas")
@Validated
public class VagaController {
    private final VagaService vagaService;

    public VagaController(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','HR','USER')")
    public List<VagaDTO> listar(@RequestParam(required = false) String status,
                                @RequestParam(required = false) Long departamentoId) {
        return vagaService.listar(status, departamentoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public VagaDTO criar(@Valid @RequestBody CreateVagaDTO dto) {
        return vagaService.criarVaga(dto);
    }

    @PutMapping("/{id}/status")
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public VagaDTO atualizarStatus(@PathVariable Long id,
                                   @Valid @RequestBody UpdateVagaStatusDTO dto) {
        return vagaService.atualizarStatus(id, dto.status());
    }
}