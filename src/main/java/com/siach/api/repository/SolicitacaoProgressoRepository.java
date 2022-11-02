package com.siach.api.repository;

import com.siach.api.model.entity.Solicitacao;
import com.siach.api.model.entity.SolicitacaoProgresso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoProgressoRepository extends JpaRepository<SolicitacaoProgresso, Long> {

    List<SolicitacaoProgresso> findByIdSolicitacao(Long idSolicitacao);
}
