package com.rosivaldolucas.picpaysimplificado.infra;

import com.rosivaldolucas.picpaysimplificado.domain.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao, Long> {

}
