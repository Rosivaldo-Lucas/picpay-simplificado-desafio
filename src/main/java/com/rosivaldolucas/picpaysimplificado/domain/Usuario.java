package com.rosivaldolucas.picpaysimplificado.domain;

import com.rosivaldolucas.picpaysimplificado.domain.enums.TipoUsuario;

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

  public boolean isValidoTransferir() {
    return this.tipo.equals(TipoUsuario.COMUM);
  }

  public void depositar(final Double valor) {
    this.saldo += valor;
  }

  public void retirar(final Double valor) {
    if (valor > this.saldo) throw new IllegalArgumentException("Usuário não tem saldo suficiente para retirar.");

    this.saldo -= valor;
  }

  public Double getSaldo() {
    return saldo;
  }

  public TipoUsuario getTipo() {
    return tipo;
  }

}
