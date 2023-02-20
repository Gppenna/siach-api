package com.siach.api.service;

import com.siach.api.model.dto.AtividadeBaremaRequestDTO;
import com.siach.api.model.dto.GrupoBaremaRequestDTO;
import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;

import java.util.List;
import java.util.Set;

public interface AtividadeBaremaService {

    List<AtividadeBarema> findByIdGrupoBarema(Long idGrupoBarema);

    List<AtividadeBarema> findByIdIn(Set<Long> id);

    AtividadeBarema findById(Long id);

    AtividadeBarema save(AtividadeBaremaRequestDTO atividadeBaremaRequestDTO);

    List<AtividadeBarema> getAll(Long idCurso);

    void deletar(Long id);
}
