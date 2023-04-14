package com.siach.api.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "atividade_complementar")
public class AtividadeComplementar {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATIVIDADE_COMPLEMENTAR_SEQ")
    @SequenceGenerator(name = "ATIVIDADE_COMPLEMENTAR_SEQ", sequenceName = "ATIVIDADE_COMPLEMENTAR_SEQ", allocationSize = 1)
    @Column(name = "id_atividade_complementar")
    private Long idAtividadeComplementar;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "horas")
    private Long horas;

    @Column(name = "id_atividade_barema")
    private Long idAtividadeBarema;

    @ManyToOne
    @JoinColumn(name = "id_atividade_barema", referencedColumnName = "id_atividade_barema", insertable = false, updatable = false)
    private AtividadeBarema atividadeBarema;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "periodo_inicio")
    private LocalDate periodoInicio;

    @Column(name = "periodo_fim")
    private LocalDate periodoFim;

    @Basic(optional = false,fetch = FetchType.LAZY)
    @Column(name = "imagem")
    private byte[] imagem;
}
