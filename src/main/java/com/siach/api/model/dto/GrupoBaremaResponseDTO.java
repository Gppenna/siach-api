package com.siach.api.model.dto;

import com.siach.api.model.entity.AtividadeBarema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GrupoBaremaResponseDTO {
    private Long idGrupoBarema;
    private String descricao;
    private Long minimoHoras;
    private Long numero;
    private List<AtividadeBarema> atividadeBaremaList;
}

