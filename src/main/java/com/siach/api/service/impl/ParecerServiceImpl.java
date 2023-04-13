package com.siach.api.service.impl;

import com.siach.api.enumeration.StatusInternoEnum;
import com.siach.api.factory.PerfilFactory;
import com.siach.api.model.dto.GrupoBaremaResponseDTO;
import com.siach.api.model.dto.PerfilResponseDTO;
import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;
import com.siach.api.model.entity.Parecer;
import com.siach.api.model.entity.Solicitacao;
import com.siach.api.repository.ParecerRepository;
import com.siach.api.service.AtividadeBaremaService;
import com.siach.api.service.GrupoBaremaService;
import com.siach.api.service.ParecerService;
import com.siach.api.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParecerServiceImpl implements ParecerService {

    private final ParecerRepository parecerRepository;
    @Autowired
    public ParecerServiceImpl(
            ParecerRepository parecerRepository) {
        this.parecerRepository = parecerRepository;
    }

    @Override
    public List<Parecer> getParecerBySolicitacao(Long id) {
        return parecerRepository.findByIdSolicitacao(id);
    }
}
