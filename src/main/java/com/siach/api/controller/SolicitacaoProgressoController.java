package com.siach.api.controller;


import com.siach.api.model.dto.SolicitacaoRequestDTO;
import com.siach.api.model.dto.SolicitacaoResponseDTO;
import com.siach.api.model.entity.Solicitacao;
import com.siach.api.model.entity.SolicitacaoProgresso;
import com.siach.api.service.SolicitacaoProgressoService;
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
@RequestMapping("/solicitacao-progresso")
public class SolicitacaoProgressoController {

    @Autowired
    private final SolicitacaoProgressoService solicitacaoProgressoService;

    public SolicitacaoProgressoController(SolicitacaoProgressoService solicitacaoProgressoService) {
        this.solicitacaoProgressoService = solicitacaoProgressoService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<List<SolicitacaoProgresso>> getAll(@PathVariable("id") Long id) {
        return ResponseEntity.ok(solicitacaoProgressoService.findByIdSolicitacao(id));
    }



}
