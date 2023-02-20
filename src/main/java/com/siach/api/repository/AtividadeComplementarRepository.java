package com.siach.api.repository;

import com.siach.api.model.entity.AtividadeBarema;
import com.siach.api.model.entity.AtividadeComplementar;
import com.siach.api.model.filter.AtividadeComplementarFiltroDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AtividadeComplementarRepository extends JpaRepository<AtividadeComplementar, Long> {
    @Query("SELECT ac FROM AtividadeComplementar ac JOIN ac.atividadeBarema ab " +
            "WHERE (:#{#atividadeComplementarFiltro.atividadeBaremaId} IS NULL OR ac.atividadeBarema.id = :#{#atividadeComplementarFiltro.atividadeBaremaId}) " +
            "AND (CURRENT_DATE BETWEEN ac.periodoInicio AND ac.periodoFim) " +
            "AND (ab.grupoBarema.idCurso = :#{#atividadeComplementarFiltro.idCurso}) " +
            "AND (:#{#atividadeComplementarFiltro.grupoBaremaId} IS NULL OR ab.grupoBarema.id = :#{#atividadeComplementarFiltro.grupoBaremaId}) " +
            "AND (:#{#atividadeComplementarFiltro.cargaHoraria} IS NULL OR ac.horas >= :#{#atividadeComplementarFiltro.cargaHoraria})")
    Page<AtividadeComplementar> findAllByFilter(@Param("atividadeComplementarFiltro") AtividadeComplementarFiltroDTO filter, Pageable pageable);
}
