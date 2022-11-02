package com.siach.api.model.dto;

import com.siach.api.model.entity.Curso;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {
    private Long id;
    private String matricula;
    private Curso curso;
    private String nome;
    private String email;
}
