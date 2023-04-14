package com.siach.api.model.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SolicitacaoRequestDTO {
    private Long idSolicitacao;
    private String email;
    private String titulo;
    private Long horas;
    private Long idAtividadeBarema;
    private MultipartFile comprovante;
    private String comprovanteNome;
    private String statusInterno;
}

