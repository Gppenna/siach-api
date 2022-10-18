package com.siach.api.model.dto;

import com.siach.api.model.entity.Curso;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestDTO {
    private String matricula;
    private String nome;
    private String email;
    private String senha;
    private Integer idCurso;
    private Integer status;
}
