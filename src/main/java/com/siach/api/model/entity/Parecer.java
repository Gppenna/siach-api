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
@Table(name = "parecer")
public class Parecer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parecer_seq")
    @SequenceGenerator(name = "parecer_seq", sequenceName = "parecer_seq", allocationSize = 1)
    @Column(name = "id_parecer", nullable = false)
    private Long idParecer;

    @Column(name = "mensagem")
    private String mensagem;

    @Column(name = "id_solicitacao")
    private Long idSolicitacao;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;
}
