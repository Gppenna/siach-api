package com.siach.api.util;

import com.siach.api.model.dto.PageDTO;
import org.springframework.data.domain.Page;

public final class PageableUtil {

    private PageableUtil() {}
    public static <T> PageDTO<T> getPageable(Page<T> responseDTOList) {
        return PageDTO.<T>builder().itens(responseDTOList.getContent())
                .currentPage(responseDTOList.getPageable().getPageNumber())
                .totalItens(responseDTOList.getTotalElements()).build();
    }
}
