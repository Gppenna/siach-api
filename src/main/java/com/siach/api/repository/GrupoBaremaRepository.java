package com.siach.api.repository;

import com.siach.api.model.entity.GrupoBarema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoBaremaRepository extends JpaRepository<GrupoBarema, Long> {

    GrupoBarema findById(Integer id);


}
