package com.siach.api.controller;


import com.siach.api.enumeration.StatusInternoEnum;
import com.siach.api.model.dto.*;
import com.siach.api.model.entity.Solicitacao;
import com.siach.api.service.SolicitacaoService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
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

    @GetMapping("/table/{id}")
    public ResponseEntity<List<SolicitacaoResponseDTO>> getAll(@PathVariable("id") Long id) {
        return ResponseEntity.ok(solicitacaoService.getAll(id));
    }

    @GetMapping("detalhe/{id}")
    public ResponseEntity<SolicitacaoResponseDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(solicitacaoService.findById(id));
    }


}
