package com.siach.api.controller;


import com.siach.api.model.dto.AtividadeComplementarRequestDTO;
import com.siach.api.model.dto.AtividadeComplementarResponseDTO;
import com.siach.api.model.dto.SolicitacaoRequestDTO;
import com.siach.api.model.dto.SolicitacaoResponseDTO;
import com.siach.api.model.entity.AtividadeComplementar;
import com.siach.api.model.entity.Solicitacao;
import com.siach.api.service.AtividadeComplementarService;
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
@RequestMapping("/solicitacao")
public class SolicitacaoController {

    @Autowired
    private final SolicitacaoService solicitacaoService;

    public SolicitacaoController(SolicitacaoService solicitacaoService) {
        this.solicitacaoService = solicitacaoService;
    }

    @PostMapping(value = "/criar",  consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Solicitacao> criar(@ModelAttribute SolicitacaoRequestDTO solicitacaoRequestDTO) throws IOException {
        return ResponseEntity.ok(solicitacaoService.save(solicitacaoRequestDTO));
    }

    @PutMapping("/ativar")
    public ResponseEntity<List<Solicitacao>> ativar(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(solicitacaoService.ativar(ids));
    }

    @GetMapping("/table")
    public ResponseEntity<List<SolicitacaoResponseDTO>> getAll() {
        return ResponseEntity.ok(solicitacaoService.getAll());
    }

    @GetMapping("/table/rascunho")
    public ResponseEntity<List<SolicitacaoResponseDTO>> getAllRascunho() {
        return ResponseEntity.ok(solicitacaoService.getAllRascunho());
    }


}