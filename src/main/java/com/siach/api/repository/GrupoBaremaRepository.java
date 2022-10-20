package com.siach.api.repository;

import com.siach.api.model.entity.GrupoBarema;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GrupoBaremaRepository extends JpaRepository<GrupoBarema, Long> {

    Optional<GrupoBarema> findById(Long id);


}
