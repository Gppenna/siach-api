package com.siach.api.service;

import com.siach.api.model.entity.GrupoBarema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GrupoBaremaService {

    GrupoBarema getById(Long id);

}
