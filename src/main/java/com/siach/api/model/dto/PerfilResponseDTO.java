package com.siach.api.model.dto;

import lombok.*;

import java.util.HashMap;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilResponseDTO {
    private HashMap<Long, PerfilBaremaResponseDTO> perfilAtividadeList;
    private HashMap<Long, PerfilBaremaResponseDTO> perfilGrupo;
}

