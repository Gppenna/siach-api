package com.siach.api.service.impl;

import com.siach.api.enumeration.StatusInternoEnum;
import com.siach.api.factory.PerfilFactory;
import com.siach.api.model.dto.*;
import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;
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
    private final GrupoBaremaService grupoBaremaService;
    private final AtividadeBaremaService atividadeBaremaService;
    private final SolicitacaoProgressoService solicitacaoProgressoService;

    @Autowired
    public SolicitacaoServiceImpl(
            SolicitacaoRepository solicitacaoRepository,
            UsuarioService usuarioService,
            GrupoBaremaService grupoBaremaService,
            AtividadeBaremaService atividadeBaremaService,
            SolicitacaoProgressoService solicitacaoProgressoService) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.usuarioService = usuarioService;
        this.grupoBaremaService = grupoBaremaService;
        this.atividadeBaremaService = atividadeBaremaService;
        this.solicitacaoProgressoService = solicitacaoProgressoService;
    }

    @Override
    public Solicitacao save(SolicitacaoRequestDTO solicitacaoRequestDTO) throws IOException {

        Solicitacao solicitacao = Solicitacao.builder()
                .id(solicitacaoRequestDTO.getId())
                .horas(solicitacaoRequestDTO.getHoras())
                .idAtividadeBarema(solicitacaoRequestDTO.getIdAtividadeBarema())
                .comprovante(solicitacaoRequestDTO.getComprovante().getBytes())
                .titulo(solicitacaoRequestDTO.getTitulo())
                .idUsuario(usuarioService.findByEmail(solicitacaoRequestDTO.getEmail()).getId())
                .statusInterno(Objects.equals(solicitacaoRequestDTO.getStatusInterno(), "E") ?
                        StatusInternoEnum.EXCEDENTE.getKey() :
                        StatusInternoEnum.RASCUNHO.getKey())
                .comprovanteNome(solicitacaoRequestDTO.getComprovanteNome())
                .build();
        return solicitacaoRepository.save(solicitacao);
    }

    @Override
    public List<SolicitacaoResponseDTO> getAll() {
        List<String> statusList = new ArrayList<>();
        statusList.add(StatusInternoEnum.ATIVO.getKey());
        statusList.add(StatusInternoEnum.EXCEDENTE_ATIVO.getKey());
        statusList.add(StatusInternoEnum.FINALIZADO.getKey());
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findByStatusInternoIn(statusList);
        List<SolicitacaoResponseDTO> solicitacaoResponseDTOList = new ArrayList<>();

        solicitacaoList.forEach(solicitacao -> {
            SolicitacaoResponseDTO solicitacaoResponseDTO = SolicitacaoResponseDTO.builder()
                    .id(solicitacao.getId())
                    .atividadeBarema(solicitacao.getAtividadeBarema())
                    .grupoBarema(solicitacao.getAtividadeBarema().getGrupoBarema())
                    .comprovante(solicitacao.getComprovante())
                    .solicitacaoProgressoList(solicitacaoProgressoService.findByIdSolicitacao(solicitacao.getId()))
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
                .id(solicitacao.getId())
                .atividadeBarema(solicitacao.getAtividadeBarema())
                .grupoBarema(solicitacao.getAtividadeBarema().getGrupoBarema())
                .comprovante(solicitacao.getComprovante())
                .solicitacaoProgressoList(solicitacaoProgressoService.findByIdSolicitacao(solicitacao.getId()))
                .horas(solicitacao.getHoras())
                .statusInterno(solicitacao.getStatusInterno())
                .titulo(solicitacao.getTitulo())
                .comprovanteNome(solicitacao.getComprovanteNome())
                .build();
    }

    @Override
    public List<SolicitacaoResponseDTO> getAllRascunho() {
        List<String> statusInternos = new ArrayList<>();
        statusInternos.add(StatusInternoEnum.RASCUNHO.getKey());
        statusInternos.add(StatusInternoEnum.EXCEDENTE.getKey());

        List<Solicitacao> solicitacaoList = solicitacaoRepository.findByStatusInternoIn(statusInternos);
        List<SolicitacaoResponseDTO> solicitacaoResponseDTOList = new ArrayList<>();

        solicitacaoList.forEach(solicitacao -> {
            SolicitacaoResponseDTO solicitacaoResponseDTO = SolicitacaoResponseDTO.builder()
                    .id(solicitacao.getId())
                    .atividadeBarema(solicitacao.getAtividadeBarema())
                    .grupoBarema(solicitacao.getAtividadeBarema().getGrupoBarema())
                    .comprovante(solicitacao.getComprovante())
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
    public List<Solicitacao> ativar(List<Long> ids) {
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findByIdIn(ids);

        solicitacaoList.forEach(solicitacao -> {
            if(!Objects.equals(solicitacao.getStatusInterno(), "E")) {
                solicitacao.setStatusInterno(StatusInternoEnum.ATIVO.getKey());
            }
            else {
                solicitacao.setStatusInterno(StatusInternoEnum.EXCEDENTE_ATIVO.getKey());
            }
            SolicitacaoProgresso solicitacaoProgresso = SolicitacaoProgresso.builder()
                    .idStatus(1L)
                    .idSolicitacao(solicitacao.getId())
                    .dataCadastro(LocalDate.now())
                    .build();
            SolicitacaoProgresso solicitacaoProgresso2 = SolicitacaoProgresso.builder()
                    .idStatus(2L)
                    .idSolicitacao(solicitacao.getId())
                    .build();
            SolicitacaoProgresso solicitacaoProgresso3 = SolicitacaoProgresso.builder()
                    .idStatus(3L)
                    .idSolicitacao(solicitacao.getId())
                    .build();
            solicitacaoProgressoService.save(solicitacaoProgresso);
            solicitacaoProgressoService.save(solicitacaoProgresso2);
            solicitacaoProgressoService.save(solicitacaoProgresso3);

        });
        solicitacaoRepository.saveAll(solicitacaoList);

        return solicitacaoList;
    }

}
