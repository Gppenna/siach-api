package com.siach.api.service.impl;

import com.siach.api.model.dto.GrupoBaremaRequestDTO;
import com.siach.api.model.entity.GrupoBarema;
import com.siach.api.repository.GrupoBaremaRepository;
import com.siach.api.service.GrupoBaremaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class GrupoBaremaServiceImpl implements GrupoBaremaService {

    private final GrupoBaremaRepository grupoBaremaRepository;

    @Autowired
    public GrupoBaremaServiceImpl(GrupoBaremaRepository grupoBaremaRepository) {
        this.grupoBaremaRepository = grupoBaremaRepository;
    }

    @Override
    public GrupoBarema getById(Long id) {
        return grupoBaremaRepository.findById(id).get();
    }

    @Override
    public GrupoBarema save(GrupoBaremaRequestDTO grupoBaremaRequestDTO) {
        GrupoBarema grupoBarema = GrupoBarema.builder()
                .idCurso(grupoBaremaRequestDTO.getIdCurso())
                .descricao(grupoBaremaRequestDTO.getDescricao())
                .minimoHoras(grupoBaremaRequestDTO.getMinimoHoras())
                .numero(grupoBaremaRequestDTO.getNumero())
                .build();
        return grupoBaremaRepository.save(grupoBarema);
    }

    @Override
    public List<GrupoBarema> getAll() {
        return grupoBaremaRepository.findAll();
    }



}
