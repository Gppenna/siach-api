package com.siach.api.service.impl;

import com.siach.api.model.dto.UsuarioResponseDTO;
import com.siach.api.model.entity.TipoUsuario;
import com.siach.api.model.entity.Usuario;
import com.siach.api.repository.GrupoBaremaRepository;
import com.siach.api.repository.UsuarioRepository;
import com.siach.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario findByEmailAndSenha(String email, String senha) {
        return usuarioRepository.findByEmailAndSenha(email, senha);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Override
    public UsuarioResponseDTO getLogged(Principal user) {
        if(user.getName() != null) {
            Usuario loggedUser = usuarioRepository.findByEmail(user.getName());
            return UsuarioResponseDTO.builder()
                    .id(loggedUser.getId())
                    .email(loggedUser.getEmail())
                    .curso(loggedUser.getCurso())
                    .matricula(loggedUser.getMatricula())
                    .nome(loggedUser.getNome())
                    .build();
        }
        return UsuarioResponseDTO.builder().build();
    }





}
