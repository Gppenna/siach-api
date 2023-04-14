package com.siach.api.service.impl;

import com.siach.api.model.dto.UsuarioResponseDTO;
import com.siach.api.model.entity.Usuario;
import com.siach.api.repository.UsuarioRepository;
import com.siach.api.service.UsuarioService;
import com.siach.api.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public UsuarioResponseDTO findByEmail(String email) {
        Usuario loggedUser = usuarioRepository.findByEmail(email);
        if(loggedUser != null) {
            return (UsuarioResponseDTO) MapperUtils.mergeObjects(List.of(MapperUtils.instanciateObject(new UsuarioResponseDTO()), loggedUser));
        }
        return UsuarioResponseDTO.builder().build();

    }

    @Override
    public Usuario findByIdUsuario(Long idUsuario) {
        return usuarioRepository.findByIdUsuario(idUsuario);
    }

    @Override
    public void temaToggle(String email) {
        Usuario loggedUser = usuarioRepository.findByEmail(email);
        if(loggedUser.getTemaEscuro() == null) {
            loggedUser.setTemaEscuro(true);
        }
        else {
            loggedUser.setTemaEscuro(!loggedUser.getTemaEscuro());
        }

        usuarioRepository.save(loggedUser);
    }

}
