package com.siach.api.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtividadeComplementarRequestDTO {
    private String titulo;
    private String descricao;
    private String periodoInicio;
    private String periodoFim;
    private Long horas;
    private Long idAtividadeBarema;
    private MultipartFile imagem;
}

