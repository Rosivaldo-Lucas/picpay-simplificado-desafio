package com.rosivaldolucas.picpaysimplificado.application;

import com.rosivaldolucas.picpaysimplificado.domain.Usuario;

public record TransacaoInput(
        Usuario pagador,
        Usuario recebedor,
        Double valor
) {

  public static TransacaoInput criar(final Usuario pagador, final Usuario recebedor, final Double valor) {
    return new TransacaoInput(pagador, recebedor, valor);
  }

}
