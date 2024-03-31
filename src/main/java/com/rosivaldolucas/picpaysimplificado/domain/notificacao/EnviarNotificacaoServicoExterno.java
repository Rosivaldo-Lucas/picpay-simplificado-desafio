package com.rosivaldolucas.picpaysimplificado.domain.notificacao;

import com.rosivaldolucas.picpaysimplificado.domain.transacao.Transacao;
import org.springframework.stereotype.Service;

@Service
public class EnviarNotificacaoServicoExterno implements EnviarNotificacao {

  private final NotificacaolProducer notificacaolProducer;

  public EnviarNotificacaoServicoExterno(final NotificacaolProducer notificacaolProducer) {
    this.notificacaolProducer = notificacaolProducer;
  }

  @Override
  public void notificar(final Transacao transacao) {
    this.notificacaolProducer.enviar(transacao);
  }

}
