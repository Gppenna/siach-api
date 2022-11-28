package com.siach.api.repository;

import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AtividadeBaremaRepository extends JpaRepository<AtividadeBarema, Long> {

    List<AtividadeBarema> findByIdGrupoBarema(Long idGrupoBarema);

    List<AtividadeBarema> findByIdIn(Set<Long> id);


}
