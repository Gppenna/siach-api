package com.siach.api.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtividadeBaremaRequestDTO {
    private String descricao;
    private Long minimoHoras;
    private Long idGrupoBarema;
}

