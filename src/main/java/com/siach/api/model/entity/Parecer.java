package com.siach.api.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "parecer")
public class Parecer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parecer", nullable = false)
    private Long idParecer;

    @Column(name = "parecer_colegiado")
    private String parecerColegiado;

    @Column(name = "parecer_aluno")
    private String parecerAluno;

    @Column(name = "id_solicitacao")
    private Long idSolicitacao;
}
