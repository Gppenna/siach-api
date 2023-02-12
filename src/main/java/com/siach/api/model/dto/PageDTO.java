package com.siach.api.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PageDTO<T> {
    private Integer currentPage;
    private Long totalItens;
    private List<T> itens;
}
