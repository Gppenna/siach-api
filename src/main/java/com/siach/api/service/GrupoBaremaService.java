package com.siach.api.service;

import com.siach.api.model.dto.GrupoBaremaRequestDTO;
import com.siach.api.model.entity.GrupoBarema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GrupoBaremaService {

    GrupoBarema getById(Long id);

    List<GrupoBarema> getAll();

    GrupoBarema save(GrupoBaremaRequestDTO grupoBaremaRequestDTO);

}
