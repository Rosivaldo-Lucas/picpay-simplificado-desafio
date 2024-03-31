package com.rosivaldolucas.picpaysimplificado.domain.notificacao;

import com.rosivaldolucas.picpaysimplificado.config.RabbitMQConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Component
public class NotificacaoEmailConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(NotificacaoEmailConsumer.class);

  private final RestClient restClient;

  public NotificacaoEmailConsumer(final RestClient.Builder builder) {
    this.restClient = builder
            .baseUrl("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6")
            .build();
  }

  @RabbitListener(queues = { RabbitMQConfig.QUEUE_TRANSACAO_NOTIFICACAO_EMAIL })
  public void emailConsumer(final String mensagem) {
    LOGGER.info("enviando email {}", mensagem);

    final ResponseEntity<NotificacaoEnviada> response = this.restClient.get().retrieve().toEntity(NotificacaoEnviada.class);

    if (response.getStatusCode().isError() || Objects.requireNonNull(response.getBody()).isEnviada()) {
      throw new RuntimeException("");
    }

    LOGGER.info("email enviado {}", mensagem);
  }

}
