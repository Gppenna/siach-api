package com.siach.api.service;

import com.siach.api.model.dto.GrupoBaremaRequestDTO;
import com.siach.api.model.dto.GrupoBaremaResponseDTO;
import com.siach.api.model.entity.GrupoBarema;

import java.util.List;

public interface GrupoBaremaService {

    GrupoBarema getById(Long id);

    List<GrupoBaremaResponseDTO> getAll(Long idCurso);


    GrupoBarema save(GrupoBaremaRequestDTO grupoBaremaRequestDTO);

}
