package com.rosivaldolucas.picpaysimplificado.domain.transacao.autorizar;

import com.rosivaldolucas.picpaysimplificado.domain.transacao.Transacao;

public interface AutorizarTransacao {

  void autorizar(final Transacao transacao);

}
