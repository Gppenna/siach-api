package com.siach.api.service.impl;

import com.siach.api.model.dto.AtividadeComplementarRequestDTO;
import com.siach.api.model.dto.AtividadeComplementarResponseDTO;
import com.siach.api.model.entity.AtividadeComplementar;
import com.siach.api.model.filter.AtividadeComplementarFiltroDTO;
import com.siach.api.repository.AtividadeComplementarRepository;
import com.siach.api.service.AtividadeComplementarService;
import com.siach.api.service.GrupoBaremaService;
import com.siach.api.util.FiltroUtil;
import com.siach.api.util.MapperUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public Page<AtividadeComplementarResponseDTO> getAll(String query, Pageable pageable, Long idCurso) {
        AtividadeComplementarFiltroDTO atividadeComplementarFiltroDTO = FiltroUtil.getFilter(query, AtividadeComplementarFiltroDTO.class);
        atividadeComplementarFiltroDTO.setIdCurso(idCurso);
        Page<AtividadeComplementar> atividadeComplementarPage =
                atividadeComplementarRepository.findAllByFilter(atividadeComplementarFiltroDTO, pageable);
        List<AtividadeComplementar> atividadeComplementarList = atividadeComplementarPage.getContent();

        List<AtividadeComplementarResponseDTO> atividadeComplementarResponseDTOList = new ArrayList<>();

        for (AtividadeComplementar atividadeComplementar : atividadeComplementarList) {
            List<Object> objectList = new ArrayList<>();
            objectList.add(new AtividadeComplementarResponseDTO());
            objectList.add(atividadeComplementar);

            AtividadeComplementarResponseDTO atividadeComplementarResponseDTO =
                    (AtividadeComplementarResponseDTO) MapperUtils.mergeObjects(objectList);
            atividadeComplementarResponseDTO.setGrupoBarema(atividadeComplementar.getAtividadeBarema().getGrupoBarema());

            atividadeComplementarResponseDTOList.add((AtividadeComplementarResponseDTO) MapperUtils.mergeObjects(objectList));
        }

        return new PageImpl<>(atividadeComplementarResponseDTOList, pageable, atividadeComplementarPage.getTotalPages());
    }

}
