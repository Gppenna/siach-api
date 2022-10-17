package com.siach.api.repository;

import com.siach.api.model.entity.GrupoBarema;
import com.siach.api.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmailAndSenha(String email, String senha);


}
