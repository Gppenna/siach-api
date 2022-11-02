package com.siach.api.model.entity;

import com.siach.api.enumeration.StatusInternoEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "solicitacao")
public class Solicitacao {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "solicitacao_seq")
    @SequenceGenerator(name = "solicitacao_seq", sequenceName = "solicitacao_seq", allocationSize = 1)
    @Column(name = "id_solicitacao")
    private Long id;

    @Column(name = "id_usuario")
    private Long idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario", insertable = false, updatable = false)
    private Usuario usuario;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "id_atividade_barema")
    private Long idAtividadeBarema;

    @ManyToOne
    @JoinColumn(name = "id_atividade_barema", referencedColumnName = "id_atividade_barema", insertable = false, updatable = false)
    private AtividadeBarema atividadeBarema;

    @Column(name = "horas")
    private Long horas;

    @Basic(optional = false,fetch = FetchType.LAZY)
    @Column(name = "comprovante")
    private byte[] comprovante;

    @Column(name = "status_interno")
    private String statusInterno;
}
