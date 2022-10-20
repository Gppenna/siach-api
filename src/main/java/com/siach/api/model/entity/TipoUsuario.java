package com.siach.api.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TIPO_USUARIO")
public class TipoUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "\"TIPO_USUARIO_SEQ\"")
    @SequenceGenerator(name = "\"TIPO_USUARIO_SEQ\"", sequenceName = "\"TIPO_USUARIO_SEQ\"", allocationSize = 1)
    @Column(name = "ID_TIPO_USUARIO")
    private Long id;

    @Column(name = "ACESSO")
    private Long acesso;

    @ManyToOne(cascade=CascadeType.PERSIST)
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    private Usuario usuario;
}

