package com.siach.api.service.impl;

import com.siach.api.enumeration.StatusInternoEnum;
import com.siach.api.model.dto.SolicitacaoRequestDTO;
import com.siach.api.model.dto.SolicitacaoResponseDTO;
import com.siach.api.model.entity.Curso;
import com.siach.api.model.entity.Solicitacao;
import com.siach.api.model.entity.SolicitacaoProgresso;
import com.siach.api.repository.CursoRepository;
import com.siach.api.repository.SolicitacaoRepository;
import com.siach.api.service.CursoService;
import com.siach.api.service.SolicitacaoProgressoService;
import com.siach.api.service.SolicitacaoService;
import com.siach.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoServiceImpl(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Override
    public Curso editarCH(Curso curso) {
        return cursoRepository.save(curso);
    }





}
