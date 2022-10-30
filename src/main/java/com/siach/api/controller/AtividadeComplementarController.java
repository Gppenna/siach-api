package com.siach.api.controller;


import com.siach.api.model.dto.AtividadeBaremaRequestDTO;
import com.siach.api.model.dto.AtividadeComplementarRequestDTO;
import com.siach.api.model.dto.AtividadeComplementarResponseDTO;
import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.AtividadeComplementar;
import com.siach.api.service.AtividadeBaremaService;
import com.siach.api.service.AtividadeComplementarService;
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
@RequestMapping("/atividade-complementar")
public class AtividadeComplementarController {

    @Autowired
    private final AtividadeComplementarService atividadeComplementarService;

    public AtividadeComplementarController(AtividadeComplementarService atividadeComplementarService) {
        this.atividadeComplementarService = atividadeComplementarService;
    }

    @PostMapping(value = "/criar",  consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<AtividadeComplementar> criar(@ModelAttribute AtividadeComplementarRequestDTO atividadeComplementarRequestDTO) throws IOException {
        return ResponseEntity.ok(atividadeComplementarService.save(atividadeComplementarRequestDTO));
    }

    @GetMapping("/table")
    public ResponseEntity<List<AtividadeComplementarResponseDTO>> getAll() {
        return ResponseEntity.ok(atividadeComplementarService.getAll());
    }


}
