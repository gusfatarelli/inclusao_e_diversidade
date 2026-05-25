package com.diversidade.inclusao.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.math.BigDecimal;
import java.util.List;

/**
 * Entidade que representa um departamento corporativo.
 */
@Data
@Entity
@Table(name = "TB_DEPARTAMENTOS")
public class Departamento {
    @Id
    @SequenceGenerator(name = "seq_departamento", sequenceName = "SEQ_DEPARTAMENTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_departamento")
    @Column(name = "ID_DEP")
    private Long id;

    @Column(name = "NOME_DEP", nullable = false)
    private String nome;

    @Column(name = "META_DIVERSIDADE", nullable = false)
    private BigDecimal metaDiversidade;

    @OneToMany(mappedBy = "departamento")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Vaga> vagas;

    @OneToMany(mappedBy = "departamento")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Colaborador> colaboradores;
}