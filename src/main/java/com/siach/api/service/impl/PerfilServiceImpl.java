package com.siach.api.service.impl;

import com.siach.api.enumeration.StatusInternoEnum;
import com.siach.api.factory.PerfilFactory;
import com.siach.api.model.dto.GrupoBaremaResponseDTO;
import com.siach.api.model.dto.PerfilResponseDTO;
import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;
import com.siach.api.model.entity.Solicitacao;
import com.siach.api.repository.SolicitacaoRepository;
import com.siach.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PerfilServiceImpl implements PerfilService {

    private final SolicitacaoRepository solicitacaoRepository;
    private final GrupoBaremaService grupoBaremaService;

    private final UsuarioService usuarioService;
    private final AtividadeBaremaService atividadeBaremaService;

    @Autowired
    public PerfilServiceImpl(
            SolicitacaoRepository solicitacaoRepository,
            GrupoBaremaService grupoBaremaService,
            UsuarioService usuarioService, AtividadeBaremaService atividadeBaremaService) {
        this.solicitacaoRepository = solicitacaoRepository;
        this.grupoBaremaService = grupoBaremaService;
        this.usuarioService = usuarioService;
        this.atividadeBaremaService = atividadeBaremaService;
    }

    @Override
    public List<PerfilResponseDTO> getAllPerfil(Long id) {
        List<String> statusInterno = new ArrayList<>();
        statusInterno.add(StatusInternoEnum.FINALIZADO.getKey());
        statusInterno.add(StatusInternoEnum.RASCUNHO.getKey());
        statusInterno.add(StatusInternoEnum.ATIVO.getKey());

        List<Solicitacao> solicitacaoList = solicitacaoRepository.findByStatusInternoInAndIdUsuario(statusInterno, id);

        List<GrupoBaremaResponseDTO> grupoBaremaList = grupoBaremaService.getAll(usuarioService.findByIdUsuario(id).getIdCurso());

        return PerfilFactory.getPerfilResponseDTO(solicitacaoList, grupoBaremaList);
    }

    @Override
    public List<PerfilResponseDTO> getPerfilById(Long id, Long idUsuario) {
        List<String> statusInterno = new ArrayList<>();
        statusInterno.add(StatusInternoEnum.FINALIZADO.getKey());
        statusInterno.add(StatusInternoEnum.RASCUNHO.getKey());
        statusInterno.add(StatusInternoEnum.ATIVO.getKey());

        List<Solicitacao> solicitacaoList = solicitacaoRepository.findByStatusInternoInAndIdAtividadeBaremaAndIdUsuario(statusInterno, id, idUsuario);
        AtividadeBarema atividadeBarema = atividadeBaremaService.findById(id);

        GrupoBarema grupoBaremaEntity = atividadeBarema.getGrupoBarema();
        List<AtividadeBarema> atividadeBaremaList = new ArrayList<>();
        atividadeBaremaList.add(atividadeBarema);

        GrupoBaremaResponseDTO grupoBarema = GrupoBaremaResponseDTO.builder()
                .idGrupoBarema(grupoBaremaEntity.getIdGrupoBarema())
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
