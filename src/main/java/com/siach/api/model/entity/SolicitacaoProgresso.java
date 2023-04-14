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
@Table(name = "solicitacao_progresso")
public class SolicitacaoProgresso {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitacao_progresso_seq")
    @SequenceGenerator(name = "solicitacao_progresso_seq", sequenceName = "solicitacao_progresso_seq", allocationSize = 1)
    @Column(name = "id_solicitacao_progresso")
    private Long idSolicitacaoProgresso;

    @Column(name = "id_status")
    private Long idStatus;

    @ManyToOne
    @JoinColumn(name = "id_status", referencedColumnName = "id_status", insertable = false, updatable = false)
    private Status status;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @Column(name = "data_previsao")
    private LocalDate dataPrevisao;

    @Column(name = "id_solicitacao")
    private Long idSolicitacao;


}
