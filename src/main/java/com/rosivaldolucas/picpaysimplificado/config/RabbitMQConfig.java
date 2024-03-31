package com.rosivaldolucas.picpaysimplificado.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  public static final String EXG_TRANSACAO = "transacao.direct";
  public static final String QUEUE_TRANSACAO_NOTIFICACAO_EMAIL = "transacao.notificacao.email";
  public static final String QUEUE_TRANSACAO_NOTIFICACAO_SMS = "transacao.notificacao.sms";
  public static final String RK_TRANSACAO_NOTIFICACAO = "transacao.notificacao";

  @Bean
  public DirectExchange directExchangeTransacao() {
    return new DirectExchange(EXG_TRANSACAO, false, false);
  }

  @Bean
  public Queue queueTransacaoNotificacaoEmail() {
    return new Queue(QUEUE_TRANSACAO_NOTIFICACAO_EMAIL, false, false, false);
  }

  @Bean
  public Queue queueTransacaoNotificacaoSms() {
    return new Queue(QUEUE_TRANSACAO_NOTIFICACAO_SMS, false, false, false);
  }

  @Bean
  public Binding bindingTransacaoNotificacaoEmail() {
    return BindingBuilder
            .bind(this.queueTransacaoNotificacaoEmail())
            .to(this.directExchangeTransacao())
            .with(RK_TRANSACAO_NOTIFICACAO);
  }

  @Bean
  public Binding bindingTransacaoNotificacaoSms() {
    return BindingBuilder
            .bind(this.queueTransacaoNotificacaoSms())
            .to(this.directExchangeTransacao())
            .with(RK_TRANSACAO_NOTIFICACAO);
  }

}
