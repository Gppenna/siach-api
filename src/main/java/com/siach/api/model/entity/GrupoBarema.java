package com.siach.api.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GRUPO_BAREMA")
public class GrupoBarema {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRUPO_BAREMA_SEQ")
    @SequenceGenerator(name = "GRUPO_BAREMA_SEQ", sequenceName = "GRUPO_BAREMA_SEQ", allocationSize = 1)
    @Column(name = "ID_GRUPO_BAREMA")
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "MINIMO_HORAS")
    private Long minimoHoras;

    @Column(name = "NUMERO")
    private Long numero;

    @ManyToOne
    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID_CURSO", insertable = false, updatable = false)
    private Curso curso;

    @Column(name = "ID_CURSO")
    private Long idCurso;

}
