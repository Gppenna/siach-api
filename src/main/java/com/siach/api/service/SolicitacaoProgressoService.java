package com.siach.api.service;

import com.siach.api.model.dto.SolicitacaoRequestDTO;
import com.siach.api.model.dto.SolicitacaoResponseDTO;
import com.siach.api.model.entity.Solicitacao;
import com.siach.api.model.entity.SolicitacaoProgresso;

import java.io.IOException;
import java.util.List;

public interface SolicitacaoProgressoService {

    SolicitacaoProgresso save(SolicitacaoProgresso solicitacaoProgresso);

    List<SolicitacaoProgresso> findByIdSolicitacao(Long idSolicitacao);

}
