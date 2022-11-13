package com.siach.api.model.dto;

import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;
import com.siach.api.model.entity.SolicitacaoProgresso;
import lombok.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PerfilResponseDTO {
    private HashMap<Long, PerfilBaremaResponseDTO> perfilAtividadeList;
    private HashMap<Long, PerfilBaremaResponseDTO> perfilGrupo;
}

