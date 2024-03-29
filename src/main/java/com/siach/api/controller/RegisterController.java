package com.siach.api.controller;

import com.siach.api.model.dto.UsuarioRequestDTO;
import com.siach.api.model.entity.TipoUsuario;
import com.siach.api.model.entity.Usuario;
import com.siach.api.service.TipoUsuarioService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Getter
@Setter
@RestController
@CrossOrigin
public class RegisterController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @PostMapping("/register")
    public TipoUsuario registrar(@RequestBody UsuarioRequestDTO userDto) {
        Usuario user = new Usuario();

        user.setMatricula(userDto.getMatricula());
        user.setSenha(passwordEncoder.encode(userDto.getSenha()));
        user.setEmail(userDto.getEmail());
        user.setNome(userDto.getNome());
        user.setIdCurso(userDto.getIdCurso());
        user.setStatus(1L);

        return tipoUsuarioService.save(user);
    }

    @PostMapping("/adminRegister")
    public TipoUsuario registrarAdmin(@RequestBody UsuarioRequestDTO userDto) {
        Usuario user = new Usuario();

        user.setMatricula(userDto.getMatricula());
        user.setSenha(passwordEncoder.encode(userDto.getSenha()));
        user.setEmail(userDto.getEmail());
        user.setNome(userDto.getNome());
        user.setIdCurso(userDto.getIdCurso());
        user.setStatus(1L);

        return tipoUsuarioService.saveAdmin(user);
    }
}