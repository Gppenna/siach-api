package com.siach.api.service;

import com.siach.api.model.dto.PerfilResponseDTO;

import java.util.List;

public interface PerfilService {

    List<PerfilResponseDTO> getAllPerfil();

    List<PerfilResponseDTO> getPerfilById(Long id);

}
