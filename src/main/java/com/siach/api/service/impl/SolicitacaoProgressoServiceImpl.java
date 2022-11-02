package com.siach.api.service.impl;

import com.siach.api.enumeration.StatusInternoEnum;
import com.siach.api.model.dto.SolicitacaoRequestDTO;
import com.siach.api.model.dto.SolicitacaoResponseDTO;
import com.siach.api.model.entity.Solicitacao;
import com.siach.api.model.entity.SolicitacaoProgresso;
import com.siach.api.repository.SolicitacaoProgressoRepository;
import com.siach.api.repository.SolicitacaoRepository;
import com.siach.api.service.SolicitacaoProgressoService;
import com.siach.api.service.SolicitacaoService;
import com.siach.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolicitacaoProgressoServiceImpl implements SolicitacaoProgressoService {

    private final SolicitacaoProgressoRepository solicitacaoProgressoRepository;


    @Autowired
    public SolicitacaoProgressoServiceImpl(SolicitacaoProgressoRepository solicitacaoProgressoRepository) {
        this.solicitacaoProgressoRepository = solicitacaoProgressoRepository;
    }

    @Override
    public SolicitacaoProgresso save(SolicitacaoProgresso solicitacaoProgresso) {
        return solicitacaoProgressoRepository.save(solicitacaoProgresso);
    }

    @Override
    public List<SolicitacaoProgresso> findByIdSolicitacao(Long idSolicitacao) {
        return solicitacaoProgressoRepository.findByIdSolicitacao(idSolicitacao);
    }
}
