package com.rosivaldolucas.picpaysimplificado.domain;

import com.rosivaldolucas.picpaysimplificado.domain.enums.TipoUsuario;

public class UsuarioComum extends Usuario {

  private UsuarioComum(final String nome, final String cpf, final Double saldo) {
    super(nome, cpf, saldo, TipoUsuario.COMUM);
  }

  public static UsuarioComum criar(final String nome, final String cpf, final Double saldo) {
    return new UsuarioComum(nome, cpf, saldo);
  }

}
