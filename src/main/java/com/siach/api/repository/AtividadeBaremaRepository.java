package com.siach.api.repository;

import com.siach.api.model.entity.AtividadeBarema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface AtividadeBaremaRepository extends JpaRepository<AtividadeBarema, Long> {

    List<AtividadeBarema> findByIdGrupoBarema(Long idGrupoBarema);

    List<AtividadeBarema> findByIdAtividadeBaremaIn(Set<Long> idAtividadeBarema);

    @Query("SELECT ab FROM AtividadeBarema ab JOIN ab.grupoBarema gb " +
            "WHERE ( gb.idCurso = ?1 ) " +
            "Order by ab.descricao"
    )
    List<AtividadeBarema> findAllByIdCursoOrderByDescricaoDesc(Long idCurso);

}
