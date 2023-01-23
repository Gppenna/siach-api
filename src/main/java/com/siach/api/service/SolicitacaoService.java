package com.siach.api.service;

import com.siach.api.enumeration.StatusInternoEnum;
import com.siach.api.model.dto.*;
import com.siach.api.model.entity.AtividadeComplementar;
import com.siach.api.model.entity.Solicitacao;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface SolicitacaoService {

    Solicitacao save(SolicitacaoRequestDTO solicitacaoRequestDTO) throws IOException;

    List<SolicitacaoResponseDTO> getAll();

    List<PerfilResponseDTO> getAllByStatusInterno(List<String> statusInternoEnum);

    List<PerfilResponseDTO> getByIdStatusInterno(Long id, List<String> statusInternoEnum);

    SolicitacaoResponseDTO findById(Long id);

    List<Solicitacao> ativar(List<Long> ids);

    List<SolicitacaoResponseDTO> getAllRascunho();

}
