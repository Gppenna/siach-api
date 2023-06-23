package com.siach.api.service.impl;

import com.siach.api.enumeration.StatusInternoEnum;
import com.siach.api.model.dto.*;
import com.siach.api.model.entity.Solicitacao;
import com.siach.api.model.entity.SolicitacaoProgresso;
import com.siach.api.repository.SolicitacaoRepository;
import com.siach.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class SolicitacaoServiceImpl implements SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;

    private final UsuarioService usuarioService;
    private final ParecerService parecerService;
    private final SolicitacaoProgressoService solicitacaoProgressoService;

    @Autowired
    public SolicitacaoServiceImpl(
            SolicitacaoRepository solicitacaoRepository,
            UsuarioService usuarioService,
            ParecerService parecerService, SolicitacaoProgressoService solicitacaoProgressoService) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.usuarioService = usuarioService;
        this.parecerService = parecerService;
        this.solicitacaoProgressoService = solicitacaoProgressoService;
    }

    @Override
    public Solicitacao save(SolicitacaoRequestDTO solicitacaoRequestDTO) throws IOException {

        Solicitacao solicitacao = solicitacaoRepository.save(Solicitacao.builder()
                .idSolicitacao(solicitacaoRequestDTO.getIdSolicitacao())
                .horas(solicitacaoRequestDTO.getHoras())
                .idAtividadeBarema(solicitacaoRequestDTO.getIdAtividadeBarema())
                .comprovante(solicitacaoRequestDTO.getComprovante().getBytes())
                .titulo(solicitacaoRequestDTO.getTitulo())
                .idUsuario(usuarioService.findByEmail(solicitacaoRequestDTO.getEmail()).getIdUsuario())
                .statusInterno(Objects.equals(solicitacaoRequestDTO.getStatusInterno(), "E") ?
                        StatusInternoEnum.EXCEDENTE_ATIVO.getKey() :
                        StatusInternoEnum.ATIVO.getKey())
                .comprovanteNome(solicitacaoRequestDTO.getComprovanteNome())
                .build());
        List<SolicitacaoProgresso> solicitacaoProgressoList = solicitacaoProgressoService.findByIdSolicitacao(solicitacao.getIdSolicitacao());
        if(solicitacaoProgressoList == null) {
            SolicitacaoProgresso solicitacaoProgresso = SolicitacaoProgresso.builder()
                    .idStatus(1L)
                    .idSolicitacao(solicitacao.getIdSolicitacao())
                    .dataCadastro(LocalDate.now())
                    .build();
            SolicitacaoProgresso solicitacaoProgresso2 = SolicitacaoProgresso.builder()
                    .idStatus(2L)
                    .idSolicitacao(solicitacao.getIdSolicitacao())
                    .build();
            SolicitacaoProgresso solicitacaoProgresso3 = SolicitacaoProgresso.builder()
                    .idStatus(3L)
                    .idSolicitacao(solicitacao.getIdSolicitacao())
                    .build();
            solicitacaoProgressoService.save(solicitacaoProgresso);
            solicitacaoProgressoService.save(solicitacaoProgresso2);
            solicitacaoProgressoService.save(solicitacaoProgresso3);
        }

        return solicitacao;
    }

    @Override
    public List<SolicitacaoResponseDTO> getAll(Long idUsuario) {
        List<String> statusList = new ArrayList<>();
        statusList.add(StatusInternoEnum.ATIVO.getKey());
        statusList.add(StatusInternoEnum.EXCEDENTE_ATIVO.getKey());
        statusList.add(StatusInternoEnum.FINALIZADO.getKey());
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findByStatusInternoInAndIdUsuarioOrderByIdSolicitacaoDesc(statusList, idUsuario);
        List<SolicitacaoResponseDTO> solicitacaoResponseDTOList = new ArrayList<>();

        solicitacaoList.forEach(solicitacao -> {
            SolicitacaoResponseDTO solicitacaoResponseDTO = SolicitacaoResponseDTO.builder()
                    .idSolicitacao(solicitacao.getIdSolicitacao())
                    .atividadeBarema(solicitacao.getAtividadeBarema())
                    .grupoBarema(solicitacao.getAtividadeBarema().getGrupoBarema())
                    .comprovante(solicitacao.getComprovante())
                    .solicitacaoProgressoList(solicitacaoProgressoService.findByIdSolicitacao(solicitacao.getIdSolicitacao()))
                    .horas(solicitacao.getHoras())
                    .statusInterno(solicitacao.getStatusInterno())
                    .titulo(solicitacao.getTitulo())
                    .comprovanteNome(solicitacao.getComprovanteNome())
                    .build();
            solicitacaoResponseDTOList.add(solicitacaoResponseDTO);
        });
        return solicitacaoResponseDTOList;
    }

    @Override
    public SolicitacaoResponseDTO findById(Long id) {
        Solicitacao solicitacao = solicitacaoRepository.findById(id).get();

        return SolicitacaoResponseDTO.builder()
                .idSolicitacao(solicitacao.getIdSolicitacao())
                .atividadeBarema(solicitacao.getAtividadeBarema())
                .grupoBarema(solicitacao.getAtividadeBarema().getGrupoBarema())
                .comprovante(solicitacao.getComprovante())
                .solicitacaoProgressoList(solicitacaoProgressoService.findByIdSolicitacao(solicitacao.getIdSolicitacao()))
                .horas(solicitacao.getHoras())
                .statusInterno(solicitacao.getStatusInterno())
                .titulo(solicitacao.getTitulo())
                .comprovanteNome(solicitacao.getComprovanteNome())
                .parecerList(parecerService.getParecerBySolicitacao(id))
                .build();
    }



}
