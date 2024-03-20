package com.rosivaldolucas.picpaysimplificado.infra;

public record AutorizacaoTransacao(
        String message
) {

  public boolean isAutorizado() {
    return this.message.equals("Autorizado");
  }

}
