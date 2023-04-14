package com.siach.api.factory;



import com.siach.api.enumeration.StatusInternoEnum;
import com.siach.api.model.dto.GrupoBaremaResponseDTO;
import com.siach.api.model.dto.PerfilBaremaResponseDTO;
import com.siach.api.model.dto.PerfilResponseDTO;
import com.siach.api.model.entity.Solicitacao;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public final class PerfilFactory {


    private PerfilFactory(){}


    public static List<PerfilResponseDTO> getPerfilResponseDTO(List<Solicitacao> solicitacaoList, List<GrupoBaremaResponseDTO> grupoBaremaList){
        List<PerfilResponseDTO> perfilResponseDTOList = new ArrayList<>();

        grupoBaremaList.forEach(grupoBarema -> {
            HashMap<Long, PerfilBaremaResponseDTO> grupoBaremaMap = new HashMap<>();
            grupoBaremaMap.put(grupoBarema.getIdGrupoBarema(), PerfilBaremaResponseDTO.builder()
                    .descricao(grupoBarema.getDescricao())
                    .horasLimite(grupoBarema.getMinimoHoras()).build());

            PerfilResponseDTO perfilResponseDTO = PerfilResponseDTO.builder()
                    .perfilGrupo(grupoBaremaMap)
                    .perfilAtividadeList(getAtividadeList(grupoBarema))
                    .build();
            perfilResponseDTOList.add(perfilResponseDTO);
        });

        solicitacaoList.forEach(solicitacao -> perfilResponseDTOList.forEach(perfilResponseDTO -> {
            if(perfilResponseDTO.getPerfilAtividadeList().get(solicitacao.getIdAtividadeBarema()) != null) {
                PerfilBaremaResponseDTO atividade = perfilResponseDTO.getPerfilAtividadeList().get(solicitacao.getIdAtividadeBarema());
                if(StatusInternoEnum.getEnumByKey(solicitacao.getStatusInterno()) == StatusInternoEnum.FINALIZADO) {
                    atividade.setHorasContabilizadas(atividade.getHorasContabilizadas() + solicitacao.getHoras());
                }
                else {
                    atividade.setHorasContabilizadasRascunho(atividade.getHorasContabilizadasRascunho() + solicitacao.getHoras());
                }
                if(atividade.getHorasContabilizadas() > atividade.getHorasLimite()) {
                    atividade.setHorasContabilizadas(atividade.getHorasLimite());
                }
                if(atividade.getHorasContabilizadasRascunho() > atividade.getHorasLimite()) {
                    atividade.setHorasContabilizadasRascunho(atividade.getHorasLimite());
                }
                perfilResponseDTO.getPerfilAtividadeList().put(solicitacao.getIdAtividadeBarema(), atividade);
            }
        }));
        perfilResponseDTOList.forEach(perfilResponseDTO -> {
            AtomicReference<Long> horas = new AtomicReference<>(0L);
            AtomicReference<Long> horasRascunho = new AtomicReference<>(0L);
            perfilResponseDTO.getPerfilAtividadeList().forEach((key, value) -> {
                horas.updateAndGet(v -> v + value.getHorasContabilizadas());
                horasRascunho.updateAndGet(v -> v + value.getHorasContabilizadasRascunho());

            });
            perfilResponseDTO.getPerfilGrupo().forEach((key, value) -> {
                value.setHorasContabilizadas(horas.get());
                value.setHorasContabilizadasRascunho(horasRascunho.get());
            });
        });
        return perfilResponseDTOList;
    }

    static HashMap<Long, PerfilBaremaResponseDTO> getAtividadeList(GrupoBaremaResponseDTO grupoBarema) {
        HashMap<Long, PerfilBaremaResponseDTO> atividadeBaremaMap = new HashMap<>();
        grupoBarema.getAtividadeBaremaList().forEach(atividadeBarema -> {
            PerfilBaremaResponseDTO perfilBaremaResponseDTO = new PerfilBaremaResponseDTO();
            perfilBaremaResponseDTO.setHorasLimite(atividadeBarema.getMinimoHoras());
            perfilBaremaResponseDTO.setDescricao(atividadeBarema.getDescricao());
            atividadeBaremaMap.put(atividadeBarema.getIdAtividadeBarema(), perfilBaremaResponseDTO);
        });
        return atividadeBaremaMap;
    }

}
