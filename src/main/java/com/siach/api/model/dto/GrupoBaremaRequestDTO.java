package com.siach.api.model.dto;

import com.siach.api.model.entity.Curso;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GrupoBaremaRequestDTO {
    private String descricao;
    private Integer minimoHoras;
    private Integer numero;
    private Curso curso;
}

