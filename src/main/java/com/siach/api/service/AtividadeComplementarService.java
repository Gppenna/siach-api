package com.siach.api.service;

import com.siach.api.model.dto.AtividadeBaremaRequestDTO;
import com.siach.api.model.dto.AtividadeComplementarRequestDTO;
import com.siach.api.model.dto.AtividadeComplementarResponseDTO;
import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.AtividadeComplementar;

import java.io.IOException;
import java.util.List;

public interface AtividadeComplementarService {

    AtividadeComplementar save(AtividadeComplementarRequestDTO atividadeComplementarRequestDTO) throws IOException;

    List<AtividadeComplementarResponseDTO> getAll();
}
