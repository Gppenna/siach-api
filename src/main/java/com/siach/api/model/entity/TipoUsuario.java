package com.siach.api.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tipo_usuario")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_usuario_seq")
    @SequenceGenerator(name = "tipo_usuario_seq", sequenceName = "tipo_usuario_seq", allocationSize = 1)
    @Column(name = "id_tipo_usuario")
    private Long id;

    @Column(name = "acesso")
    private Long acesso;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
}

