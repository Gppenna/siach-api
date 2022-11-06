package com.siach.api.repository;

import com.siach.api.model.entity.Curso;
import com.siach.api.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {


}
