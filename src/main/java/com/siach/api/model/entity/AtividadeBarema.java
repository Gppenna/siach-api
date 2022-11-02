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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "atividade_barema_seq")
    @SequenceGenerator(name = "atividade_barema_seq", sequenceName = "atividade_barema_seq", allocationSize = 1)
    @Column(name = "id_atividade_barema")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "minimo_horas")
    private Long minimoHoras;

    @Column(name = "id_grupo_barema")
    private Long idGrupoBarema;

    @ManyToOne
    @JoinColumn(name = "id_grupo_barema", referencedColumnName = "id_grupo_barema", insertable = false, updatable = false)
    private GrupoBarema grupoBarema;

}
