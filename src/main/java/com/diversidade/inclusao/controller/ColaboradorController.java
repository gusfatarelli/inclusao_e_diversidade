package com.diversidade.inclusao.controller;

import com.diversidade.inclusao.dto.ColaboradorDTO;
import com.diversidade.inclusao.dto.TreinamentoDTO;
import com.diversidade.inclusao.service.ColaboradorService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST para colaboradores.
 */
@RestController
@RequestMapping("/api/colaboradores")
public class ColaboradorController {
    private final ColaboradorService colaboradorService;

    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','HR')")
    public List<ColaboradorDTO> listar() {
        return colaboradorService.listarTodos();
    }

    @GetMapping("/{id}/treinamentos")
    @PreAuthorize("hasAnyRole('ADMIN','HR','USER')")
    public List<TreinamentoDTO> listarTreinamentos(@PathVariable Long id) {
        return colaboradorService.listarTreinamentos(id);
    }
}