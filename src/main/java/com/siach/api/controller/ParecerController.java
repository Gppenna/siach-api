package com.siach.api.controller;


import com.siach.api.model.dto.AtividadeBaremaRequestDTO;
import com.siach.api.model.dto.ParecerRequestDTO;
import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.Parecer;
import com.siach.api.service.AtividadeBaremaService;
import com.siach.api.service.ParecerService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@Setter
@RestController
@CrossOrigin
@RequestMapping("/parecer")
public class ParecerController {

    @Autowired
    private final ParecerService parecerService;

    public ParecerController(ParecerService parecerService) {
        this.parecerService = parecerService;
    }

    @PostMapping(value = "/criar",  consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<Parecer> criar(@ModelAttribute ParecerRequestDTO parecerRequestDTO) {
        return ResponseEntity.ok(parecerService.save(parecerRequestDTO));
    }

    @GetMapping("/table/{id}")
    public ResponseEntity<List<Parecer>> getAll(@PathVariable("id") Long idSolicitacao) {
        return ResponseEntity.ok(parecerService.getAll(idSolicitacao));
    }


}
