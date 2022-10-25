package com.siach.api.controller;


import com.siach.api.model.dto.AtividadeBaremaRequestDTO;
import com.siach.api.model.dto.GrupoBaremaRequestDTO;
import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;
import com.siach.api.service.AtividadeBaremaService;
import com.siach.api.service.GrupoBaremaService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@Setter
@RestController
@CrossOrigin
@RequestMapping("/atividade-barema")
public class AtividadeBaremaController {

    @Autowired
    private final AtividadeBaremaService atividadeBaremaService;

    public AtividadeBaremaController(AtividadeBaremaService atividadeBaremaService) {
        this.atividadeBaremaService = atividadeBaremaService;
    }

    @PostMapping("/criar")
    public ResponseEntity<AtividadeBarema> criar(@RequestBody AtividadeBaremaRequestDTO atividadeBaremaRequestDTO) {
        return ResponseEntity.ok(atividadeBaremaService.save(atividadeBaremaRequestDTO));
    }


}
