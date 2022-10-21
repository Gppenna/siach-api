package com.siach.api.service;

import com.siach.api.model.entity.TipoUsuario;
import com.siach.api.model.entity.Usuario;

public interface TipoUsuarioService {
    TipoUsuario save(Usuario usuario);

    TipoUsuario saveAdmin(Usuario usuario);
}
