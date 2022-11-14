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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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
                .statusInterno(StatusInternoEnum.RASCUNHO.getKey())
                .comprovanteNome(solicitacaoRequestDTO.getComprovanteNome())
                .build();
        return solicitacaoRepository.save(solicitacao);
    }

    @Override
    public List<SolicitacaoResponseDTO> getAll() {
        List<String> statusList = new ArrayList<>();
        statusList.add(StatusInternoEnum.ATIVO.getKey());
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
                    .titulo(solicitacao.getTitulo())
                    .comprovanteNome(solicitacao.getComprovanteNome())
                    .build();
            solicitacaoResponseDTOList.add(solicitacaoResponseDTO);
        });
        return solicitacaoResponseDTOList;
    }

    @Override
    public List<PerfilResponseDTO> getAllFinalizado() {
        List<Solicitacao> solicitacaoList = solicitacaoRepository.findByStatusInterno(StatusInternoEnum.FINALIZADO.getKey());
        List<GrupoBaremaResponseDTO> grupoBaremaList = grupoBaremaService.getAll();
        List<SolicitacaoResponseDTO> solicitacaoResponseDTOList = new ArrayList<>();

        List<PerfilResponseDTO> perfilResponseDTOList = new ArrayList<>();

        grupoBaremaList.forEach(grupoBarema -> {
            HashMap<Long, PerfilBaremaResponseDTO> grupoBaremaMap = new HashMap<>();
            grupoBaremaMap.put(grupoBarema.getId(),PerfilBaremaResponseDTO.builder()
                    .descricao(grupoBarema.getDescricao())
                    .horasLimite(grupoBarema.getMinimoHoras()).build());

            PerfilResponseDTO perfilResponseDTO = PerfilResponseDTO.builder()
                    .perfilGrupo(grupoBaremaMap)
                    .perfilAtividadeList(getAtividadeList(grupoBarema))
                    .build();
            perfilResponseDTOList.add(perfilResponseDTO);
        });

        solicitacaoList.forEach(solicitacao -> {
            perfilResponseDTOList.forEach(perfilResponseDTO -> {
                if(perfilResponseDTO.getPerfilAtividadeList().get(solicitacao.getIdAtividadeBarema()) != null) {
                    PerfilBaremaResponseDTO atividade = perfilResponseDTO.getPerfilAtividadeList().get(solicitacao.getIdAtividadeBarema());
                    atividade.setHorasContabilizadas(atividade.getHorasContabilizadas() + solicitacao.getHoras());
                    if(atividade.getHorasContabilizadas() > atividade.getHorasLimite()) {
                        atividade.setHorasContabilizadas(atividade.getHorasLimite());
                    }
                    perfilResponseDTO.getPerfilAtividadeList().put(solicitacao.getIdAtividadeBarema(), atividade);
                }
            });
        });
        perfilResponseDTOList.forEach(perfilResponseDTO -> {
            AtomicReference<Long> horas = new AtomicReference<>(0L);
            perfilResponseDTO.getPerfilAtividadeList().forEach((key, value) -> horas.updateAndGet(v -> v + value.getHorasContabilizadas()));
            perfilResponseDTO.getPerfilGrupo().forEach((key, value) -> value.setHorasContabilizadas(horas.get()));
        });
        return perfilResponseDTOList;
    }

    HashMap<Long, PerfilBaremaResponseDTO> getAtividadeList(GrupoBaremaResponseDTO grupoBarema) {
        HashMap<Long, PerfilBaremaResponseDTO> atividadeBaremaMap = new HashMap<>();
        grupoBarema.getAtividadeBaremaList().forEach(atividadeBarema -> {
            PerfilBaremaResponseDTO perfilBaremaResponseDTO = new PerfilBaremaResponseDTO();
            perfilBaremaResponseDTO.setHorasLimite(atividadeBarema.getMinimoHoras());
            perfilBaremaResponseDTO.setDescricao(atividadeBarema.getDescricao());
            atividadeBaremaMap.put(atividadeBarema.getId(), perfilBaremaResponseDTO);
        });
        return atividadeBaremaMap;
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
