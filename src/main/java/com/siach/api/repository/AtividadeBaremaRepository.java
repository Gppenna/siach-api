package com.siach.api.repository;

import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.GrupoBarema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface AtividadeBaremaRepository extends JpaRepository<AtividadeBarema, Long> {

    List<AtividadeBarema> findByIdGrupoBarema(Long idGrupoBarema);

    List<AtividadeBarema> findByIdIn(Set<Long> id);

    @Query("SELECT ab FROM AtividadeBarema ab JOIN ab.grupoBarema gb " +
            "WHERE ( gb.idCurso = ?1 )")
    List<AtividadeBarema> findAllByIdCurso(Long idCurso);

}
