package com.rosivaldolucas.picpaysimplificado.domain;

public class UsuarioLogista extends Usuario {

  private UsuarioLogista(final String nome, final String cpf, final Double saldo) {
    super(nome, cpf, saldo, TipoUsuario.LOGISTA);
  }

  public static UsuarioLogista criar(final String nome, final String cpf, final Double saldo) {
    return new UsuarioLogista(nome, cpf, saldo);
  }

}
