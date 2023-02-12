package com.siach.api.model.filter;

import com.siach.api.model.entity.Curso;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtividadeComplementarFiltroDTO {
    private Integer atividadeBaremaId;
    private Integer grupoBaremaId;
    private Integer cargaHoraria;
}
