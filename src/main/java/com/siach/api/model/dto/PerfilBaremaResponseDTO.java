package com.siach.api.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilBaremaResponseDTO {
    private String descricao;
    private Long horasContabilizadas = 0L;
    private Long horasContabilizadasRascunho = 0L;
    private Long horasLimite;
}

