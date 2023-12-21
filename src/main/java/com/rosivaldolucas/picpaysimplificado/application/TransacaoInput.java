package com.rosivaldolucas.picpaysimplificado.application;

public record TransacaoInput(
        Long pagador,
        Long recebedor,
        Double valor
) {

  public static TransacaoInput criar(final Long pagador, final Long recebedor, final Double valor) {
    return new TransacaoInput(pagador, recebedor, valor);
  }

}
