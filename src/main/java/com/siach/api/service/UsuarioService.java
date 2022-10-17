package com.siach.api.service;

import com.siach.api.model.entity.TipoUsuario;
import com.siach.api.model.entity.Usuario;

public interface UsuarioService {

    Usuario findByEmailAndSenha(String email, String senha);

}
