package com.diversidade.inclusao.service;

import com.diversidade.inclusao.dto.CandidatoDTO;
import com.diversidade.inclusao.dto.CreateCandidatoDTO;
import com.diversidade.inclusao.entity.Candidato;
import com.diversidade.inclusao.entity.Vaga;
import com.diversidade.inclusao.repository.CandidatoRepository;
import com.diversidade.inclusao.repository.VagaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço de inscrição e consulta de candidatos.
 */
@Service
public class CandidatoService {
    private final CandidatoRepository candidatoRepo;
    private final VagaRepository vagaRepo;

    public CandidatoService(CandidatoRepository candidatoRepo, VagaRepository vagaRepo) {
        this.candidatoRepo = candidatoRepo;
        this.vagaRepo = vagaRepo;
    }

    @Transactional
    public CandidatoDTO inscrever(CreateCandidatoDTO dto) {
        Vaga vaga = vagaRepo.findById(dto.vagaId())
                .orElseThrow(() -> new IllegalArgumentException("Vaga não encontrada"));
        Candidato candidato = new Candidato();
        candidato.setId(System.currentTimeMillis());
        candidato.setNome(dto.nome());
        candidato.setScoreDiversidade(dto.scoreDiversidade());
        candidato.setVaga(vaga);
        candidatoRepo.save(candidato);
        return new CandidatoDTO(candidato.getId(), candidato.getNome(), candidato.getScoreDiversidade(), vaga.getId());
    }

    public List<CandidatoDTO> listarPorVaga(Long vagaId) {
        return candidatoRepo.findByVagaIdOrderByScoreDiversidadeDesc(vagaId)
                .stream()
                .map(c -> new CandidatoDTO(c.getId(), c.getNome(), c.getScoreDiversidade(), vagaId))
                .collect(Collectors.toList());
    }
}