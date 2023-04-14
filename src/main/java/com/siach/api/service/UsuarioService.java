package com.siach.api.service;

import com.siach.api.model.dto.UsuarioResponseDTO;
import com.siach.api.model.entity.Usuario;

public interface UsuarioService {

    Usuario findByEmailAndSenha(String email, String senha);
    UsuarioResponseDTO findByEmail(String email);

    Usuario findByIdUsuario(Long idUsuario);
    void temaToggle(String email);
}
