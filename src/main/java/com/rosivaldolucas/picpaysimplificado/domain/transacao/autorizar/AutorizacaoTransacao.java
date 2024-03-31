package com.rosivaldolucas.picpaysimplificado.domain.transacao.autorizar;

public record AutorizacaoTransacao(
        String message
) {

  public boolean isAutorizado() {
    return this.message.equals("Autorizado");
  }

}
