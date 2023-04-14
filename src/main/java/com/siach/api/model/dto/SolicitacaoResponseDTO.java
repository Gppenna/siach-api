package com.siach.api.model.dto;

import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;
import com.siach.api.model.entity.Parecer;
import com.siach.api.model.entity.SolicitacaoProgresso;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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
    private String comprovanteNome;
    private UsuarioResponseDTO usuarioResponseDTO;
    private Long idSolicitacao;
    private List<SolicitacaoProgresso> solicitacaoProgressoList;
    private String statusInterno;
    private List<Parecer> parecerList;
}

