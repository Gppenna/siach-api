package com.siach.api.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "atividade_barema")
public class AtividadeBarema {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ATIVIDADE_BAREMA_SEQ")
    @SequenceGenerator(name = "ATIVIDADE_BAREMA_SEQ", sequenceName = "ATIVIDADE_BAREMA_SEQ", allocationSize = 1)
    @Column(name = "id_atividade_barema")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "minimo_horas")
    private Long minimoHoras;

    @Column(name = "id_grupo_barema")
    private Long idGrupoBarema;

}
