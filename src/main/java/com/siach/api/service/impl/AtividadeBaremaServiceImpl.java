package com.siach.api.service.impl;

import com.siach.api.model.dto.AtividadeBaremaRequestDTO;
import com.siach.api.model.dto.GrupoBaremaRequestDTO;
import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;
import com.siach.api.repository.AtividadeBaremaRepository;
import com.siach.api.repository.GrupoBaremaRepository;
import com.siach.api.service.AtividadeBaremaService;
import com.siach.api.service.GrupoBaremaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class AtividadeBaremaServiceImpl implements AtividadeBaremaService {

    private final AtividadeBaremaRepository atividadeBaremaRepository;

    @Autowired
    public AtividadeBaremaServiceImpl(AtividadeBaremaRepository atividadeBaremaRepository) {
        this.atividadeBaremaRepository = atividadeBaremaRepository;
    }

    @Override
    public List<AtividadeBarema> findByIdGrupoBarema(Long idGrupoBarema) {
        return atividadeBaremaRepository.findByIdGrupoBarema(idGrupoBarema);
    }

    @Override
    public List<AtividadeBarema> findByIdIn(Set<Long> id) {
        return atividadeBaremaRepository.findByIdIn(id);
    }

    @Override
    public AtividadeBarema findById(Long id) {
        Optional<AtividadeBarema> atividadeBarema = atividadeBaremaRepository.findById(id);
        return atividadeBarema.get();
    }

    @Override
    public AtividadeBarema save(AtividadeBaremaRequestDTO atividadeBaremaRequestDTO) {
        AtividadeBarema atividadeBarema = AtividadeBarema.builder()
                .descricao(atividadeBaremaRequestDTO.getDescricao())
                .minimoHoras(atividadeBaremaRequestDTO.getMinimoHoras())
                .idGrupoBarema(atividadeBaremaRequestDTO.getIdGrupoBarema())
                .build();
        return atividadeBaremaRepository.save(atividadeBarema);
    }

    @Override
    public List<AtividadeBarema> getAll() {
        return atividadeBaremaRepository.findAll();
    }

    @Override
    public void deletar(Long id) {
        AtividadeBarema atividadeBarema = atividadeBaremaRepository.findById(id).get();
        atividadeBaremaRepository.delete(atividadeBarema);
    }

}
