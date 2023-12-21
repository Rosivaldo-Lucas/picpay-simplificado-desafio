package com.rosivaldolucas.picpaysimplificado.domain.entities;

import com.rosivaldolucas.picpaysimplificado.domain.enums.TipoUsuario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COMUM")
public class UsuarioComum extends Usuario {

  protected UsuarioComum() { }

  private UsuarioComum(final String nome, final String cpf, final Double saldo) {
    super(nome, cpf, saldo, TipoUsuario.COMUM);
  }

  public static UsuarioComum criar(final String nome, final String cpf, final Double saldo) {
    return new UsuarioComum(nome, cpf, saldo);
  }

}
