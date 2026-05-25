package com.diversidade.inclusao.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;

/**
 * Entidade que representa uma vaga de emprego.
 */
@Data
@Entity
@Table(name = "TB_VAGAS")
public class Vaga {
    @Id
    @SequenceGenerator(name = "seq_vaga", sequenceName = "SEQ_VAGA", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vaga")
    @Column(name = "ID_VAGA")
    private Long id;

    @Column(name = "CARGO", nullable = false)
    private String cargo;

    @Column(name = "STATUS", nullable = false)
    private String status;

    @Column(name = "FLAG_AFIRMATIVA", length = 1, nullable = false)
    private String flagAfirmativa;

    @ManyToOne
    @JoinColumn(name = "FK_DEP")
    private Departamento departamento;

    @OneToMany(mappedBy = "vaga")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Candidato> candidatos;
}