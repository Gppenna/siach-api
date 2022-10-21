package com.siach.api.service.impl;

import com.siach.api.model.entity.TipoUsuario;
import com.siach.api.model.entity.Usuario;
import com.siach.api.repository.TipoUsuarioRepository;
import com.siach.api.repository.UsuarioRepository;
import com.siach.api.service.TipoUsuarioService;
import com.siach.api.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoUsuarioServiceImpl implements TipoUsuarioService {

    private final TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public TipoUsuarioServiceImpl(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Override
    public TipoUsuario save(Usuario usuario) {
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setAcesso(2L);
        tipoUsuario.setUsuario(usuario);
        return tipoUsuarioRepository.save(tipoUsuario);
    }

    @Override
    public TipoUsuario saveAdmin(Usuario usuario) {
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setAcesso(1L);
        tipoUsuario.setUsuario(usuario);
        return tipoUsuarioRepository.save(tipoUsuario);
    }


}
