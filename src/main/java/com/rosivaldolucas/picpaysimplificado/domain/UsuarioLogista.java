package com.rosivaldolucas.picpaysimplificado.domain;

import com.rosivaldolucas.picpaysimplificado.domain.enums.TipoUsuario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("LOGISTA")
public class UsuarioLogista extends Usuario {

  protected UsuarioLogista() { }

  private UsuarioLogista(final String nome, final String cpf, final Double saldo) {
    super(nome, cpf, saldo, TipoUsuario.LOGISTA);
  }

  public static UsuarioLogista criar(final String nome, final String cpf, final Double saldo) {
    return new UsuarioLogista(nome, cpf, saldo);
  }

}
