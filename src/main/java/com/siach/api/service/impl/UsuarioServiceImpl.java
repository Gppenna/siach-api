package com.siach.api.service.impl;

import com.siach.api.model.entity.TipoUsuario;
import com.siach.api.model.entity.Usuario;
import com.siach.api.repository.GrupoBaremaRepository;
import com.siach.api.repository.UsuarioRepository;
import com.siach.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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



}
