package com.rosivaldolucas.picpaysimplificado.domain.notificacao;

import com.rosivaldolucas.picpaysimplificado.config.RabbitMQConfig;
import com.rosivaldolucas.picpaysimplificado.domain.transacao.Transacao;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificacaolProducer {

  private final RabbitTemplate rabbitTemplate;

  public NotificacaolProducer(final RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void enviar(final Transacao transacao) {
    String mensagem = transacao.getId() +
            "/n" +
            "pagador: " + transacao.getPagador().getNome() +
            "/n" +
            "recebedor: " + transacao.getRecebedor().getNome() +
            "/n" +
            "valor: " + transacao.getValor();

    this.rabbitTemplate.convertAndSend(RabbitMQConfig.EXG_TRANSACAO, RabbitMQConfig.RK_TRANSACAO_NOTIFICACAO, mensagem);
  }

}
