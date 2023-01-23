package com.siach.api.controller;


import com.siach.api.enumeration.StatusInternoEnum;
import com.siach.api.model.dto.*;
import com.siach.api.model.entity.AtividadeComplementar;
import com.siach.api.model.entity.Solicitacao;
import com.siach.api.model.entity.SolicitacaoProgresso;
import com.siach.api.service.AtividadeComplementarService;
import com.siach.api.service.SolicitacaoService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;
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

    @PutMapping("/ativar")
    public ResponseEntity<List<Solicitacao>> ativar(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(solicitacaoService.ativar(ids));
    }

    @GetMapping("/table")
    public ResponseEntity<List<SolicitacaoResponseDTO>> getAll() {
        return ResponseEntity.ok(solicitacaoService.getAll());
    }

    @GetMapping("/perfil/finalizado")
    public ResponseEntity<List<PerfilResponseDTO>> getAllFinalizadoSolicitacao() {
        List<String> statusInternoEnumList = new ArrayList<>();
        statusInternoEnumList.add(StatusInternoEnum.FINALIZADO.getKey());
        return ResponseEntity.ok(solicitacaoService.getAllByStatusInterno(statusInternoEnumList));
    }

    @GetMapping("/perfil/rascunho")
    public ResponseEntity<List<PerfilResponseDTO>> getAllRascunhoSolicitacao() {
        List<String> statusInternoEnumList = new ArrayList<>();
        statusInternoEnumList.add(StatusInternoEnum.RASCUNHO.getKey());
        statusInternoEnumList.add(StatusInternoEnum.ATIVO.getKey());
        return ResponseEntity.ok(solicitacaoService.getAllByStatusInterno(statusInternoEnumList));
    }

    @GetMapping("/perfil/finalizado/{id}")
    public ResponseEntity<List<PerfilResponseDTO>> getByIdFinalizado(@PathVariable("id") Long id) {
        List<String> statusInternoEnumList = new ArrayList<>();
        statusInternoEnumList.add(StatusInternoEnum.FINALIZADO.getKey());
        return ResponseEntity.ok(solicitacaoService.getByIdStatusInterno(id, statusInternoEnumList));
    }

    @GetMapping("/perfil/rascunho/{id}")
    public ResponseEntity<List<PerfilResponseDTO>> getByIdRascunho(@PathVariable("id") Long id) {
        List<String> statusInternoEnumList = new ArrayList<>();
        statusInternoEnumList.add(StatusInternoEnum.RASCUNHO.getKey());
        statusInternoEnumList.add(StatusInternoEnum.ATIVO.getKey());
        return ResponseEntity.ok(solicitacaoService.getByIdStatusInterno(id, statusInternoEnumList));
    }

    @GetMapping("/table/rascunho")
    public ResponseEntity<List<SolicitacaoResponseDTO>> getAllRascunho() {
        return ResponseEntity.ok(solicitacaoService.getAllRascunho());
    }

    @GetMapping("detalhe/{id}")
    public ResponseEntity<SolicitacaoResponseDTO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(solicitacaoService.findById(id));
    }


}
