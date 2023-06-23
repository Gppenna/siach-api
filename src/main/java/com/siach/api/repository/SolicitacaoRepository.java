package com.siach.api.repository;

import com.siach.api.enumeration.StatusInternoEnum;
import com.siach.api.model.entity.AtividadeComplementar;
import com.siach.api.model.entity.Solicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoRepository extends JpaRepository<Solicitacao, Long> {

    List<Solicitacao> findByStatusInternoIn(List<String> statusInterno);
    List<Solicitacao> findByStatusInterno(String statusInterno);
    List<Solicitacao> findByStatusInternoInAndIdUsuarioOrderByIdSolicitacaoDesc(List<String> statusInterno, Long idUsuario);

    List<Solicitacao> findByStatusInternoInAndIdAtividadeBaremaAndIdUsuario(List<String> statusInterno, Long idAtividadeBarema, Long idUsuario);

    List<Solicitacao> findByIdSolicitacaoIn(List<Long> idSolicitacao);

}
