package com.diversidade.inclusao.service;

import com.diversidade.inclusao.dto.CreateDepartamentoDTO;
import com.diversidade.inclusao.dto.DepartamentoDTO;
import com.diversidade.inclusao.entity.Colaborador;
import com.diversidade.inclusao.entity.Departamento;
import com.diversidade.inclusao.repository.ColaboradorRepository;
import com.diversidade.inclusao.repository.DepartamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço responsável pelas operações de departamento.
 */
@Service
public class DepartamentoService {
    private final DepartamentoRepository departamentoRepo;
    private final ColaboradorRepository colaboradorRepo;

    public DepartamentoService(DepartamentoRepository departamentoRepo, ColaboradorRepository colaboradorRepo) {
        this.departamentoRepo = departamentoRepo;
        this.colaboradorRepo = colaboradorRepo;
    }

    public List<DepartamentoDTO> listarTodos() {
        return departamentoRepo.findAll().stream()
                .map(dep -> new DepartamentoDTO(dep.getId(), dep.getNome(), dep.getMetaDiversidade()))
                .collect(Collectors.toList());
    }

    @Transactional
    public DepartamentoDTO criar(CreateDepartamentoDTO dto) {
        Departamento dep = new Departamento();
        // ID gerado de forma determinística apenas para fins demonstrativos; ideal é usar sequence
        dep.setId((long) (dto.nome().hashCode() & 0xfffffff));
        dep.setNome(dto.nome());
        dep.setMetaDiversidade(dto.metaDiversidade());
        departamentoRepo.save(dep);
        return new DepartamentoDTO(dep.getId(), dep.getNome(), dep.getMetaDiversidade());
    }

    /**
     * Calcula a proporção de colaboradores diversos do departamento.
     */
    public BigDecimal calcularIndicadorDiversidade(Long departamentoId) {
        List<Colaborador> colaboradores = colaboradorRepo.findAll().stream()
                .filter(c -> c.getDepartamento() != null && departamentoId.equals(c.getDepartamento().getId()))
                .collect(Collectors.toList());
        if (colaboradores.isEmpty()) {
            return BigDecimal.ZERO;
        }
        long diversos = colaboradores.stream()
                .filter(c -> "S".equalsIgnoreCase(c.getPcd()) || (c.getEtnia() != null && !"Branca".equalsIgnoreCase(c.getEtnia())))
                .count();
        return BigDecimal.valueOf(diversos).divide(BigDecimal.valueOf(colaboradores.size()), 2, RoundingMode.HALF_UP);
    }
}