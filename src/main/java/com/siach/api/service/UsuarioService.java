package com.siach.api.service;

import com.siach.api.model.dto.UsuarioResponseDTO;
import com.siach.api.model.entity.TipoUsuario;
import com.siach.api.model.entity.Usuario;

public interface UsuarioService {

    Usuario findByEmailAndSenha(String email, String senha);
    Usuario findByEmail(String email);

    UsuarioResponseDTO getLogged();

}
