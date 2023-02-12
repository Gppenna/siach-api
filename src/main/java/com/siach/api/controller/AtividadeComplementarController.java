package com.siach.api.controller;


import com.siach.api.model.dto.AtividadeBaremaRequestDTO;
import com.siach.api.model.dto.AtividadeComplementarRequestDTO;
import com.siach.api.model.dto.AtividadeComplementarResponseDTO;
import com.siach.api.model.dto.PageDTO;
import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.AtividadeComplementar;
import com.siach.api.service.AtividadeBaremaService;
import com.siach.api.service.AtividadeComplementarService;
import com.siach.api.util.PageableUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Getter
@Setter
@RestController
@CrossOrigin
@RequestMapping("/atividade-complementar")
public class AtividadeComplementarController {

    @Autowired
    private final AtividadeComplementarService atividadeComplementarService;

    public AtividadeComplementarController(AtividadeComplementarService atividadeComplementarService) {
        this.atividadeComplementarService = atividadeComplementarService;
    }

    @PostMapping(value = "/criar",  consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<AtividadeComplementar> criar(@ModelAttribute AtividadeComplementarRequestDTO atividadeComplementarRequestDTO) throws IOException {
        return ResponseEntity.ok(atividadeComplementarService.save(atividadeComplementarRequestDTO));
    }

    @GetMapping("/table")
    public ResponseEntity<PageDTO<AtividadeComplementarResponseDTO>> getAll(@RequestParam("page") Integer page,
                                                                            @RequestParam("limit") Integer limit,

                                                                            @RequestParam(value = "_q", required = false, defaultValue = "") String query) {
        Sort sort = Sort.by(Sort.Direction.DESC, "periodoInicio");
        Pageable pageable = PageRequest.of(page, limit, sort);
        return ResponseEntity.ok(PageableUtil.getPageable(atividadeComplementarService.getAll(query, pageable)));
    }


}
