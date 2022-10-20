package com.siach.api.controller;


import com.siach.api.model.dto.GrupoBaremaRequestDTO;
import com.siach.api.model.entity.GrupoBarema;
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
@RequestMapping("/barema")
public class GrupoBaremaController {

    @Autowired
    private final GrupoBaremaService grupoBaremaService;

    public GrupoBaremaController(GrupoBaremaService grupoBaremaService) {
        this.grupoBaremaService = grupoBaremaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GrupoBarema> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(grupoBaremaService.getById(id));
    }

    @GetMapping("/table")
    public ResponseEntity<List<GrupoBarema>> getAll() {
        return ResponseEntity.ok(grupoBaremaService.getAll());
    }

    @PostMapping("/criar")
    public ResponseEntity<GrupoBarema> criar(@RequestBody GrupoBaremaRequestDTO grupoBaremaRequestDTO) {
        return ResponseEntity.ok(grupoBaremaService.save(grupoBaremaRequestDTO));
    }


}
