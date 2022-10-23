package com.siach.api.controller;


import com.siach.api.model.dto.UsuarioResponseDTO;
import com.siach.api.model.entity.Usuario;
import com.siach.api.model.mapper.UsuarioMapper;
import com.siach.api.service.UsuarioService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Getter
@Setter
@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private final UsuarioService usuarioService;

    @Autowired
    private final UsuarioMapper usuarioMapper;

    public LoginController(UsuarioService usuarioService, UsuarioMapper usuarioMapper) {
        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @GetMapping("/login")
    public Principal user(Principal user) {
        return user;
    }

    @GetMapping("/user")
    public ResponseEntity<Usuario> getUserInfo(@RequestParam("name") String name) {
        return ResponseEntity.ok(usuarioService.findByEmail(name));

    }

}
