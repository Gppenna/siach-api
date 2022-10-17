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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_GRUPO_BAREMA")
    private Long id;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "MINIMO_HORAS")
    private Integer minimoHoras;

    @Column(name = "NUMERO")
    private Integer numero;

    @ManyToOne
    @JoinColumn(name = "ID_CURSO", referencedColumnName = "ID_CURSO")
    private Curso curso;

}
