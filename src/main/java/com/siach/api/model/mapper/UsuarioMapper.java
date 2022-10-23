package com.siach.api.model.mapper;

import com.siach.api.model.dto.UsuarioResponseDTO;
import com.siach.api.model.entity.Usuario;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    UsuarioResponseDTO modelToDTO(Usuario usuario);
}
