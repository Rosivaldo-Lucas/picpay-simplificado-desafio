package com.rosivaldolucas.picpaysimplificado.domain;

public abstract class Usuario {

  private String nome;
  private String cpf;
  private Double saldo;
  private TipoUsuario tipo;

  protected Usuario(final String nome, final String cpf, final Double saldo, final TipoUsuario tipo) {
    if (saldo < 0) throw new IllegalArgumentException("Saldo usuário não pode ser nulo.");

    this.nome = nome;
    this.cpf = cpf;
    this.saldo = saldo;
    this.tipo = tipo;
  }

  public void incrementarSaldo(final Double valor) {
    this.saldo += valor;
  }

  public void decrementarSaldo(final Double valor) {
    this.saldo -= valor;
  }

  public TipoUsuario getTipo() {
    return tipo;
  }

}
