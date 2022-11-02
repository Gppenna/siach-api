package com.siach.api.model.dto;

import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoResponseDTO {
    private String titulo;
    private Long horas;
    private AtividadeBarema atividadeBarema;
    private GrupoBarema grupoBarema;
    private byte[] comprovante;
    private UsuarioResponseDTO usuarioResponseDTO;
}

