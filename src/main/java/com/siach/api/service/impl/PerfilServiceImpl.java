package com.siach.api.service.impl;

import com.siach.api.enumeration.StatusInternoEnum;
import com.siach.api.factory.PerfilFactory;
import com.siach.api.model.dto.GrupoBaremaResponseDTO;
import com.siach.api.model.dto.PerfilResponseDTO;
import com.siach.api.model.dto.SolicitacaoRequestDTO;
import com.siach.api.model.dto.SolicitacaoResponseDTO;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PerfilServiceImpl implements PerfilService {

    private final SolicitacaoRepository solicitacaoRepository;
    private final GrupoBaremaService grupoBaremaService;
    private final AtividadeBaremaService atividadeBaremaService;

    @Autowired
    public PerfilServiceImpl(
            SolicitacaoRepository solicitacaoRepository,
            GrupoBaremaService grupoBaremaService,
            AtividadeBaremaService atividadeBaremaService) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.grupoBaremaService = grupoBaremaService;
        this.atividadeBaremaService = atividadeBaremaService;
    }

    @Override
    public List<PerfilResponseDTO> getAllPerfil() {
        List<String> statusInterno = new ArrayList<>();
        statusInterno.add(StatusInternoEnum.FINALIZADO.getKey());
        statusInterno.add(StatusInternoEnum.RASCUNHO.getKey());
        statusInterno.add(StatusInternoEnum.ATIVO.getKey());

        List<Solicitacao> solicitacaoList = solicitacaoRepository.findByStatusInternoIn(statusInterno);

        List<GrupoBaremaResponseDTO> grupoBaremaList = grupoBaremaService.getAll();

        return PerfilFactory.getPerfilResponseDTO(solicitacaoList, grupoBaremaList);
    }

    @Override
    public List<PerfilResponseDTO> getPerfilById(Long id) {
        List<String> statusInterno = new ArrayList<>();
        statusInterno.add(StatusInternoEnum.FINALIZADO.getKey());
        statusInterno.add(StatusInternoEnum.RASCUNHO.getKey());
        statusInterno.add(StatusInternoEnum.ATIVO.getKey());

        List<Solicitacao> solicitacaoList = solicitacaoRepository.findByStatusInternoInAndIdAtividadeBarema(statusInterno, id);
        AtividadeBarema atividadeBarema = atividadeBaremaService.findById(id);

        GrupoBarema grupoBaremaEntity = atividadeBarema.getGrupoBarema();
        List<AtividadeBarema> atividadeBaremaList = new ArrayList<>();
        atividadeBaremaList.add(atividadeBarema);

        GrupoBaremaResponseDTO grupoBarema = GrupoBaremaResponseDTO.builder()
                .id(grupoBaremaEntity.getId())
                .descricao(grupoBaremaEntity.getDescricao())
                .minimoHoras(grupoBaremaEntity.getMinimoHoras())
                .numero(grupoBaremaEntity.getNumero())
                .atividadeBaremaList(atividadeBaremaList)
                .build();
        List<GrupoBaremaResponseDTO> grupoBaremaList = new ArrayList<>();
        grupoBaremaList.add(grupoBarema);

        return PerfilFactory.getPerfilResponseDTO(solicitacaoList, grupoBaremaList);
    }

}
