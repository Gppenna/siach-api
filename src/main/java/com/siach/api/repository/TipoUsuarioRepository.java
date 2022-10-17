package com.siach.api.repository;

import com.siach.api.model.entity.TipoUsuario;
import com.siach.api.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long> {

}
