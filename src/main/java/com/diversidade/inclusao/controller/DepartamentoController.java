package com.diversidade.inclusao.controller;

import com.diversidade.inclusao.dto.CreateDepartamentoDTO;
import com.diversidade.inclusao.dto.DepartamentoDTO;
import com.diversidade.inclusao.service.DepartamentoService;
import org.springframework.security.access.prepost.PreAuthorize;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

/**
 * Controlador REST para departamentos.
 */
@RestController
@RequestMapping("/api/departamentos")
@Validated
public class DepartamentoController {
    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','HR','USER')")
    public List<DepartamentoDTO> listar() {
        return departamentoService.listarTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public DepartamentoDTO criar(@Valid @RequestBody CreateDepartamentoDTO dto) {
        return departamentoService.criar(dto);
    }

    @GetMapping("/{id}/indicador-diversidade")
    @PreAuthorize("hasAnyRole('ADMIN','HR','USER')")
    public ResponseEntity<BigDecimal> indicadorDiversidade(@PathVariable Long id) {
        BigDecimal indicador = departamentoService.calcularIndicadorDiversidade(id);
        return ResponseEntity.ok(indicador);
    }
}