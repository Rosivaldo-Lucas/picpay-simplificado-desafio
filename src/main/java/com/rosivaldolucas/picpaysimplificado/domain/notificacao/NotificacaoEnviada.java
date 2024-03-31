package com.rosivaldolucas.picpaysimplificado.domain.notificacao;

public record NotificacaoEnviada(
        String mensagem
) {

  public boolean isEnviada() {
    return this.mensagem.equals("Enviada");
  }

}
