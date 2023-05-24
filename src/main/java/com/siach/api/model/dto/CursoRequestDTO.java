package com.siach.api.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CursoRequestDTO {
    private Long idCurso;
    private String descricao;
    private Long minimoHorasCurso;
    private MultipartFile barema;
}

