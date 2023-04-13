package com.siach.api.service;

import com.siach.api.model.dto.PerfilResponseDTO;
import com.siach.api.model.entity.Parecer;

import java.util.List;

public interface ParecerService {

    List<Parecer> getParecerBySolicitacao(Long id);

}
