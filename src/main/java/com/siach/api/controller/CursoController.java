package com.siach.api.controller;


import com.siach.api.model.dto.SolicitacaoRequestDTO;
import com.siach.api.model.dto.SolicitacaoResponseDTO;
import com.siach.api.model.entity.Curso;
import com.siach.api.model.entity.Solicitacao;
import com.siach.api.service.CursoService;
import com.siach.api.service.SolicitacaoService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Getter
@Setter
@RestController
@CrossOrigin
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @PutMapping("/editar-ch")
    public ResponseEntity<Curso> editarCH(@RequestBody Curso curso) {
        return ResponseEntity.ok(cursoService.editarCH(curso));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Curso>> getAll() {
        return ResponseEntity.ok(cursoService.findAll());
    }


}
