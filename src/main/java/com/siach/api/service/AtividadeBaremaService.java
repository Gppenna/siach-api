package com.siach.api.service;

import com.siach.api.model.dto.AtividadeBaremaRequestDTO;
import com.siach.api.model.dto.GrupoBaremaRequestDTO;
import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;

import java.util.List;

public interface AtividadeBaremaService {

    List<AtividadeBarema> findByIdGrupoBarema(Long idGrupoBarema);

    AtividadeBarema save(AtividadeBaremaRequestDTO atividadeBaremaRequestDTO);

}
