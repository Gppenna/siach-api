package com.siach.api.service.impl;

import com.siach.api.enumeration.StatusInternoEnum;
import com.siach.api.model.dto.SolicitacaoRequestDTO;
import com.siach.api.model.dto.SolicitacaoResponseDTO;
import com.siach.api.model.entity.Solicitacao;
import com.siach.api.repository.SolicitacaoRepository;
import com.siach.api.service.SolicitacaoService;
import com.siach.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolicitacaoServiceImpl implements SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;

    private final UsuarioService usuarioService;

    @Autowired
    public SolicitacaoServiceImpl(SolicitacaoRepository solicitacaoRepository, UsuarioService usuarioService) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.usuarioService = usuarioService;
    }

    @Override
    public Solicitacao save(SolicitacaoRequestDTO solicitacaoRequestDTO) throws IOException {

        Solicitacao solicitacao = Solicitacao.builder()
                .horas(solicitacaoRequestDTO.getHoras())
                .idAtividadeBarema(solicitacaoRequestDTO.getIdAtividadeBarema())
                .comprovante(solicitacaoRequestDTO.getComprovante().getBytes())
                .titulo(solicitacaoRequestDTO.getTitulo())
                .idUsuario(usuarioService.getLogged().getId())
                .statusInterno(StatusInternoEnum.RASCUNHO.getKey())
                .build();
        return solicitacaoRepository.save(solicitacao);
    }

    @Override
    public List<SolicitacaoResponseDTO> getAll() {
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findByStatusInterno(StatusInternoEnum.ATIVO.getKey());
        List<SolicitacaoResponseDTO> solicitacaoResponseDTOList = new ArrayList<>();

        solicitacaoList.forEach(solicitacao -> {
            SolicitacaoResponseDTO solicitacaoResponseDTO = SolicitacaoResponseDTO.builder()
                    .atividadeBarema(solicitacao.getAtividadeBarema())
                    .grupoBarema(solicitacao.getAtividadeBarema().getGrupoBarema())
                    .comprovante(solicitacao.getComprovante())
                    .horas(solicitacao.getHoras())
                    .titulo(solicitacao.getTitulo())
                    .build();
            solicitacaoResponseDTOList.add(solicitacaoResponseDTO);
        });
        return solicitacaoResponseDTOList;
    }

    @Override
    public List<SolicitacaoResponseDTO> getAllRascunho() {
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findByStatusInterno(StatusInternoEnum.RASCUNHO.getKey());
        List<SolicitacaoResponseDTO> solicitacaoResponseDTOList = new ArrayList<>();

        solicitacaoList.forEach(solicitacao -> {
            SolicitacaoResponseDTO solicitacaoResponseDTO = SolicitacaoResponseDTO.builder()
                    .atividadeBarema(solicitacao.getAtividadeBarema())
                    .grupoBarema(solicitacao.getAtividadeBarema().getGrupoBarema())
                    .comprovante(solicitacao.getComprovante())
                    .horas(solicitacao.getHoras())
                    .titulo(solicitacao.getTitulo())
                    .build();
            solicitacaoResponseDTOList.add(solicitacaoResponseDTO);
        });
        return solicitacaoResponseDTOList;
    }

}
