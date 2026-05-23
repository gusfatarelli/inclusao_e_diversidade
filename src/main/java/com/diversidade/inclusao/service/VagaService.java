package com.diversidade.inclusao.service;

import com.diversidade.inclusao.dto.CreateVagaDTO;
import com.diversidade.inclusao.dto.VagaDTO;
import com.diversidade.inclusao.entity.Departamento;
import com.diversidade.inclusao.entity.Vaga;
import com.diversidade.inclusao.repository.DepartamentoRepository;
import com.diversidade.inclusao.repository.VagaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Serviço de gerenciamento de vagas.
 */
@Service
public class VagaService {
    private final VagaRepository vagaRepo;
    private final DepartamentoRepository departamentoRepo;

    public VagaService(VagaRepository vagaRepo, DepartamentoRepository departamentoRepo) {
        this.vagaRepo = vagaRepo;
        this.departamentoRepo = departamentoRepo;
    }

    @Transactional
    public VagaDTO criarVaga(CreateVagaDTO dto) {
        Departamento dep = departamentoRepo.findById(dto.departamentoId())
                .orElseThrow(() -> new IllegalArgumentException("Departamento não encontrado"));
        // Bloqueio de vagas não inclusivas conforme política ESG
        if ("N".equalsIgnoreCase(dto.flagAfirmativa()) && dep.getMetaDiversidade().compareTo(new BigDecimal("0.10")) < 0) {
            throw new IllegalStateException("Departamento não atende meta mínima de diversidade para vagas não afirmativas");
        }
        Vaga vaga = new Vaga();
        vaga.setId(System.currentTimeMillis());
        vaga.setCargo(dto.cargo());
        vaga.setStatus("ABERTA");
        vaga.setFlagAfirmativa(dto.flagAfirmativa());
        vaga.setDepartamento(dep);
        vagaRepo.save(vaga);
        return new VagaDTO(vaga.getId(), vaga.getCargo(), vaga.getStatus(), vaga.getFlagAfirmativa(), dep.getId());
    }

    @Transactional
    public VagaDTO atualizarStatus(Long id, String status) {
        Vaga vaga = vagaRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada"));
        vaga.setStatus(status);
        vagaRepo.save(vaga);
        return new VagaDTO(vaga.getId(), vaga.getCargo(), vaga.getStatus(), vaga.getFlagAfirmativa(), vaga.getDepartamento().getId());
    }

    public List<VagaDTO> listar(String status, Long departamentoId) {
        Stream<Vaga> stream = vagaRepo.findAll().stream();
        if (status != null) {
            stream = stream.filter(v -> v.getStatus() != null && v.getStatus().equalsIgnoreCase(status));
        }
        if (departamentoId != null) {
            stream = stream.filter(v -> v.getDepartamento() != null && departamentoId.equals(v.getDepartamento().getId()));
        }
        return stream
                .map(v -> new VagaDTO(v.getId(), v.getCargo(), v.getStatus(), v.getFlagAfirmativa(), v.getDepartamento().getId()))
                .collect(Collectors.toList());
    }
}