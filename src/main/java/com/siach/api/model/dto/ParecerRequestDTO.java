package com.siach.api.model.dto;

import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParecerRequestDTO {
    private Long idSolicitacao;
    private Long idUsuario;
    private String mensagem;
}

