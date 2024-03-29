package com.siach.api.service.impl;

import com.siach.api.model.dto.GrupoBaremaRequestDTO;
import com.siach.api.model.dto.GrupoBaremaResponseDTO;
import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;
import com.siach.api.repository.GrupoBaremaRepository;
import com.siach.api.service.AtividadeBaremaService;
import com.siach.api.service.GrupoBaremaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GrupoBaremaServiceImpl implements GrupoBaremaService {

    private final GrupoBaremaRepository grupoBaremaRepository;

    private final AtividadeBaremaService atividadeBaremaService;

    @Autowired
    public GrupoBaremaServiceImpl(GrupoBaremaRepository grupoBaremaRepository, AtividadeBaremaService atividadeBaremaService) {
        this.grupoBaremaRepository = grupoBaremaRepository;
        this.atividadeBaremaService = atividadeBaremaService;
    }

    @Override
    public GrupoBarema getById(Long id) {
        return grupoBaremaRepository.findById(id).get();
    }

    @Override
    public GrupoBarema save(GrupoBaremaRequestDTO grupoBaremaRequestDTO) {
        GrupoBarema grupoBarema = GrupoBarema.builder()
                .idGrupoBarema(grupoBaremaRequestDTO.getIdGrupoBarema())
                .idCurso(grupoBaremaRequestDTO.getIdCurso())
                .descricao(grupoBaremaRequestDTO.getDescricao())
                .minimoHoras(grupoBaremaRequestDTO.getMinimoHoras())
                .numero(grupoBaremaRequestDTO.getNumero())
                .build();
        return grupoBaremaRepository.save(grupoBarema);
    }

    @Override
    public List<GrupoBaremaResponseDTO> getAll(Long idCurso) {
        List<GrupoBarema> baremaList = grupoBaremaRepository.findAllByIdCursoOrderByDescricaoAsc(idCurso);
        List<GrupoBaremaResponseDTO> baremaDTOList = new ArrayList<>();
        baremaList.forEach(barema -> {
            List<AtividadeBarema> atividadeList = atividadeBaremaService.findByIdGrupoBarema(barema.getIdGrupoBarema());
            GrupoBaremaResponseDTO baremaDTO = GrupoBaremaResponseDTO.builder()
                    .idGrupoBarema(barema.getIdGrupoBarema())
                    .descricao(barema.getDescricao())
                    .minimoHoras(barema.getMinimoHoras())
                    .numero(barema.getNumero())
                    .atividadeBaremaList(atividadeList)
                    .build();
            baremaDTOList.add(baremaDTO);

        });
        return baremaDTOList;
    }





}
