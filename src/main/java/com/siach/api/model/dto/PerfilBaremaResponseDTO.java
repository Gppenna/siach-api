package com.siach.api.model.dto;

import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilBaremaResponseDTO {
    private String descricao;
    private Long horasContabilizadas = 0L;
    private Long horasLimite;

}

