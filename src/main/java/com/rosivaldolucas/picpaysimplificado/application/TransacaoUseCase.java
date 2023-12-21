package com.rosivaldolucas.picpaysimplificado.application;

import com.rosivaldolucas.picpaysimplificado.domain.Usuario;
import com.rosivaldolucas.picpaysimplificado.domain.Transacao;

public class TransacaoUseCase {

  public TransacaoOutput execute(final TransacaoInput transacaoInput) {
    final Usuario pagador = transacaoInput.pagador();
    final Usuario recebedor = transacaoInput.recebedor();
    final Double valor = transacaoInput.valor();

    final Transacao novaTransacao = Transacao.criar(pagador, recebedor);

    novaTransacao.transferir(valor);

    return new TransacaoOutput(novaTransacao.obterCodigo(), novaTransacao.obterStatus().name());
  }

}
