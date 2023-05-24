package com.siach.api.repository;

import com.siach.api.model.entity.Parecer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParecerRepository extends JpaRepository<Parecer, Long> {

    List<Parecer> findByIdSolicitacao(Long idSolicitacao);

    List<Parecer> findAllByIdSolicitacao(Long idSolicitacao);


}
