package com.siach.api.service;

import com.siach.api.model.dto.PerfilResponseDTO;

import java.util.List;

public interface PerfilService {

    List<PerfilResponseDTO> getAllPerfil(Long id);

    List<PerfilResponseDTO> getPerfilById(Long id);

}
