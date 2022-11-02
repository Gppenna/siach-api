package com.siach.api.service.impl;

import com.siach.api.model.dto.AtividadeComplementarRequestDTO;
import com.siach.api.model.dto.AtividadeComplementarResponseDTO;
import com.siach.api.model.entity.AtividadeComplementar;
import com.siach.api.repository.AtividadeComplementarRepository;
import com.siach.api.service.AtividadeComplementarService;
import com.siach.api.service.GrupoBaremaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AtividadeComplementarServiceImpl implements AtividadeComplementarService {

    private final AtividadeComplementarRepository atividadeComplementarRepository;

    @Autowired
    public AtividadeComplementarServiceImpl(AtividadeComplementarRepository atividadeComplementarRepository) {
        this.atividadeComplementarRepository = atividadeComplementarRepository;
    }

    @Override
    public AtividadeComplementar save(AtividadeComplementarRequestDTO atividadeComplementarRequestDTO) throws IOException {
        AtividadeComplementar atividadeComplementar = AtividadeComplementar.builder()
                .descricao(atividadeComplementarRequestDTO.getDescricao())
                .horas(atividadeComplementarRequestDTO.getHoras())
                .idAtividadeBarema(atividadeComplementarRequestDTO.getIdAtividadeBarema())
                .periodoFim( LocalDate.parse(atividadeComplementarRequestDTO.getPeriodoFim()) )
                .periodoInicio(LocalDate.parse(atividadeComplementarRequestDTO.getPeriodoInicio()))
                .imagem(atividadeComplementarRequestDTO.getImagem().getBytes())
                .titulo(atividadeComplementarRequestDTO.getTitulo())
                .build();
        return atividadeComplementarRepository.save(atividadeComplementar);
    }

    @Override
    public List<AtividadeComplementarResponseDTO> getAll() {
        List<AtividadeComplementar> atividadeComplementarList = atividadeComplementarRepository.findAll();
        List<AtividadeComplementarResponseDTO> atividadeComplementarResponseDTOList = new ArrayList<>();

        atividadeComplementarList.forEach(atividadeComplementar -> {
            AtividadeComplementarResponseDTO atividadeComplementarResponseDTO = AtividadeComplementarResponseDTO.builder()
                    .atividadeBarema(atividadeComplementar.getAtividadeBarema())
                    .grupoBarema(atividadeComplementar.getAtividadeBarema().getGrupoBarema())
                    .descricao(atividadeComplementar.getDescricao())
                    .imagem(atividadeComplementar.getImagem())
                    .periodoFim(atividadeComplementar.getPeriodoFim())
                    .periodoInicio(atividadeComplementar.getPeriodoInicio())
                    .horas(atividadeComplementar.getHoras())
                    .titulo(atividadeComplementar.getTitulo())
                    .build();
            atividadeComplementarResponseDTOList.add(atividadeComplementarResponseDTO);
        });
        return atividadeComplementarResponseDTOList;
    }

}
