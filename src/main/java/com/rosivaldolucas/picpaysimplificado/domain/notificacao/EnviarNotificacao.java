package com.rosivaldolucas.picpaysimplificado.domain.notificacao;

import com.rosivaldolucas.picpaysimplificado.domain.transacao.Transacao;

public interface EnviarNotificacao {

  void notificar(final Transacao transacao);

}
