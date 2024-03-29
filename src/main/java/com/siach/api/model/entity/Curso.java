package com.siach.api.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_curso")
    private Long idCurso;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "minimo_horas_curso")
    private Long minimoHorasCurso;

    @Basic(optional = false,fetch = FetchType.LAZY)
    @Column(name = "barema")
    private byte[] barema;

}
