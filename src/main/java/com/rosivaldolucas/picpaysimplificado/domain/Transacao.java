package com.rosivaldolucas.picpaysimplificado.domain;

import com.rosivaldolucas.picpaysimplificado.domain.enums.StatusTransacao;

import java.time.LocalDate;

public class Transacao {

  private String codigo;
  private Usuario pagador;
  private Usuario recebedor;
  private Double valor;
  private StatusTransacao status;

  private Transacao(final Usuario pagador, final Usuario recebedor) {
    if (!pagador.isValidoTransferir()) throw new IllegalArgumentException("Usuário pagador não está habilitado para transferencias.");

    this.pagador = pagador;
    this.recebedor = recebedor;
    this.status = StatusTransacao.PENDENTE;
  }

  public static Transacao criar(final Usuario pagador, final Usuario recebedor) {
    return new Transacao(pagador, recebedor);
  }

  public void transferir(final Double valor) {
    this.pagador.retirar(valor);
    this.recebedor.depositar(valor);

    this.valor = valor;
    this.status = StatusTransacao.TRANSFERIDO;
    this.codigo = String.format("%d%08d", LocalDate.now().getYear(), 1);
  }

  public String obterCodigo() {
    return this.codigo;
  }

  public StatusTransacao obterStatus() {
    return this.status;
  }

}
