package com.siach.api.controller;


import com.siach.api.model.entity.GrupoBarema;
import com.siach.api.service.GrupoBaremaService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@RestController
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

    /*@GetMapping("/criar")
    public ResponseEntity<GrupoBarema> criar(@ResponseBody GrupoBaremaRequestDTO grupoBaremaRequestDTO) {
        return ResponseEntity.ok(grupoBaremaService.getById(id));
    }*/


}
