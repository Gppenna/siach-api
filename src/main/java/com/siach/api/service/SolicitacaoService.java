package com.siach.api.service;

import com.siach.api.model.dto.*;
import com.siach.api.model.entity.Solicitacao;

import java.io.IOException;
import java.util.List;

public interface SolicitacaoService {

    Solicitacao save(SolicitacaoRequestDTO solicitacaoRequestDTO) throws IOException;

    List<SolicitacaoResponseDTO> getAll();

    SolicitacaoResponseDTO findById(Long id);

    List<Solicitacao> ativar(List<Long> ids);

    List<SolicitacaoResponseDTO> getAllRascunho();

}
