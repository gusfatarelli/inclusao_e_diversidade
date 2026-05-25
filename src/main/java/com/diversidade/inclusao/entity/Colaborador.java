package com.diversidade.inclusao.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import java.util.List;

/**
 * Entidade que representa um colaborador efetivo da empresa.
 */
@Data
@Entity
@Table(name = "TB_COLABORADORES")
public class Colaborador {
    @Id
    @SequenceGenerator(name = "seq_colab", sequenceName = "SEQ_COLAB", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_colab")
    @Column(name = "ID_COLAB")
    private Long id;

    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "GENERO")
    private String genero;

    @Column(name = "ETNIA")
    private String etnia;

    @Column(name = "PCD", length = 1, nullable = false)
    private String pcd;

    @ManyToOne
    @JoinColumn(name = "FK_DEP")
    private Departamento departamento;

    @OneToMany(mappedBy = "colaborador")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<TreinamentoLog> treinamentos;
}