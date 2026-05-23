package com.diversidade.inclusao.service;

import com.diversidade.inclusao.dto.ColaboradorDTO;
import com.diversidade.inclusao.dto.TreinamentoDTO;
import com.diversidade.inclusao.entity.Colaborador;
import com.diversidade.inclusao.entity.TreinamentoLog;
import com.diversidade.inclusao.repository.ColaboradorRepository;
import com.diversidade.inclusao.repository.TreinamentoLogRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço para operações relacionadas aos colaboradores.
 */
@Service
public class ColaboradorService {
    private final ColaboradorRepository colaboradorRepo;
    private final TreinamentoLogRepository treinamentoRepo;

    public ColaboradorService(ColaboradorRepository colaboradorRepo, TreinamentoLogRepository treinamentoRepo) {
        this.colaboradorRepo = colaboradorRepo;
        this.treinamentoRepo = treinamentoRepo;
    }

    public List<ColaboradorDTO> listarTodos() {
        return colaboradorRepo.findAll().stream()
                .map(c -> new ColaboradorDTO(
                        c.getId(),
                        c.getNome(),
                        c.getGenero(),
                        c.getEtnia(),
                        c.getPcd(),
                        c.getDepartamento() != null ? c.getDepartamento().getId() : null,
                        c.getDepartamento() != null ? c.getDepartamento().getNome() : null))
                .collect(Collectors.toList());
    }

    public List<TreinamentoDTO> listarTreinamentos(Long colaboradorId) {
        List<TreinamentoLog> logs = treinamentoRepo.findByColaboradorId(colaboradorId);
        return logs.stream()
                .map(t -> new TreinamentoDTO(t.getId(), t.getTipoTreinamento(), t.getDataConclusao()))
                .collect(Collectors.toList());
    }
}