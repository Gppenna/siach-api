package com.siach.api.controller;


import com.siach.api.model.dto.PerfilResponseDTO;
import com.siach.api.service.PerfilService;
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
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private final PerfilService perfilService;

    public PerfilController(PerfilService perfilService) {
        this.perfilService = perfilService;
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<PerfilResponseDTO>> getAllPerfilSolicitacao(@PathVariable("id") Long id) {
        return ResponseEntity.ok(perfilService.getAllPerfil(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PerfilResponseDTO>> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(perfilService.getPerfilById(id));
    }


}
