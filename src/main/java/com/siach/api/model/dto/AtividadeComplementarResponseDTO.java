package com.siach.api.model.dto;

import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtividadeComplementarResponseDTO {
    private String titulo;
    private String descricao;
    private LocalDate periodoInicio;
    private LocalDate periodoFim;
    private Long horas;
    private AtividadeBarema atividadeBarema;
    private GrupoBarema grupoBarema;
    private byte[] imagem;
}

