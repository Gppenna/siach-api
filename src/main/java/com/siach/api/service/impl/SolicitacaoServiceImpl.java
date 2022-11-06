package com.siach.api.service.impl;

import com.siach.api.enumeration.StatusInternoEnum;
import com.siach.api.model.dto.SolicitacaoRequestDTO;
import com.siach.api.model.dto.SolicitacaoResponseDTO;
import com.siach.api.model.entity.Solicitacao;
import com.siach.api.model.entity.SolicitacaoProgresso;
import com.siach.api.repository.SolicitacaoRepository;
import com.siach.api.service.SolicitacaoProgressoService;
import com.siach.api.service.SolicitacaoService;
import com.siach.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SolicitacaoServiceImpl implements SolicitacaoService {

    private final SolicitacaoRepository solicitacaoRepository;

    private final UsuarioService usuarioService;

    private final SolicitacaoProgressoService solicitacaoProgressoService;

    @Autowired
    public SolicitacaoServiceImpl(SolicitacaoRepository solicitacaoRepository, UsuarioService usuarioService, SolicitacaoProgressoService solicitacaoProgressoService) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.usuarioService = usuarioService;
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
                .idUsuario(usuarioService.getLogged().getId())
                .statusInterno(StatusInternoEnum.RASCUNHO.getKey())
                .comprovanteNome(solicitacaoRequestDTO.getComprovanteNome())
                .build();
        return solicitacaoRepository.save(solicitacao);
    }

    @Override
    public List<SolicitacaoResponseDTO> getAll() {
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findByStatusInterno(StatusInternoEnum.ATIVO.getKey());
        List<SolicitacaoResponseDTO> solicitacaoResponseDTOList = new ArrayList<>();

        solicitacaoList.forEach(solicitacao -> {
            SolicitacaoResponseDTO solicitacaoResponseDTO = SolicitacaoResponseDTO.builder()
                    .id(solicitacao.getId())
                    .atividadeBarema(solicitacao.getAtividadeBarema())
                    .grupoBarema(solicitacao.getAtividadeBarema().getGrupoBarema())
                    .comprovante(solicitacao.getComprovante())
                    .solicitacaoProgressoList(solicitacaoProgressoService.findByIdSolicitacao(solicitacao.getId()))
                    .horas(solicitacao.getHoras())
                    .titulo(solicitacao.getTitulo())
                    .comprovanteNome(solicitacao.getComprovanteNome())
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
                    .id(solicitacao.getId())
                    .atividadeBarema(solicitacao.getAtividadeBarema())
                    .grupoBarema(solicitacao.getAtividadeBarema().getGrupoBarema())
                    .comprovante(solicitacao.getComprovante())
                    .horas(solicitacao.getHoras())
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
            solicitacao.setStatusInterno(StatusInternoEnum.ATIVO.getKey());
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
                    .titulo(solicitacao.getTitulo())
                    .comprovanteNome(solicitacao.getComprovanteNome())
                    .build();
    }





}
