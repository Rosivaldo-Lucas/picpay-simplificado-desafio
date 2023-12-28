package com.rosivaldolucas.picpaysimplificado.application.transacao;

public enum StatusAutorizadorTransacao {

  AUTORIZADO("Autorizado"),
  NAO_AUTORIZADO("Nao_autorizado");

  private final String valor;

  StatusAutorizadorTransacao(final String valor) {
    this.valor = valor;
  }

  public static StatusAutorizadorTransacao fromString(final String valor) {
    for (final StatusAutorizadorTransacao status : StatusAutorizadorTransacao.values()) {
      if (status.valor.equalsIgnoreCase(valor)) {
        return status;
      }
    }
    throw new IllegalArgumentException("Status não reconhecido: " + valor);
  }

  public String getValor() {
    return valor;
  }

}
