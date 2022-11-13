package com.siach.api.service;

import com.siach.api.model.dto.AtividadeComplementarRequestDTO;
import com.siach.api.model.dto.AtividadeComplementarResponseDTO;
import com.siach.api.model.dto.SolicitacaoRequestDTO;
import com.siach.api.model.dto.SolicitacaoResponseDTO;
import com.siach.api.model.entity.AtividadeComplementar;
import com.siach.api.model.entity.Solicitacao;

import java.io.IOException;
import java.util.List;

public interface SolicitacaoService {

    Solicitacao save(SolicitacaoRequestDTO solicitacaoRequestDTO) throws IOException;

    List<SolicitacaoResponseDTO> getAll();

    List<SolicitacaoResponseDTO> getAllFinalizado();

    SolicitacaoResponseDTO findById(Long id);

    List<Solicitacao> ativar(List<Long> ids);

    List<SolicitacaoResponseDTO> getAllRascunho();


}
