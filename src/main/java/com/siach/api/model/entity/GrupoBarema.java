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
@Table(name = "grupo_barema")
public class GrupoBarema {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "grupo_barema_seq")
    @SequenceGenerator(name = "grupo_barema_seq", sequenceName = "grupo_barema_seq", allocationSize = 1)
    @Column(name = "id_grupo_barema")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "minimo_horas")
    private Long minimoHoras;

    @Column(name = "numero")
    private Long numero;

    @Column(name = "id_curso")
    private Long idCurso;

}
