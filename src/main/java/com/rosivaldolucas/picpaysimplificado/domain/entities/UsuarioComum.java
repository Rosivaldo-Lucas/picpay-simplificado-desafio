package com.rosivaldolucas.picpaysimplificado.domain.entities;

import com.rosivaldolucas.picpaysimplificado.domain.enums.TipoUsuario;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COMUM")
public class UsuarioComum extends Usuario {

  protected UsuarioComum() { }

  private UsuarioComum(final String nome, final String cpf) {
    super(nome, cpf, TipoUsuario.COMUM);
  }

  public static UsuarioComum criar(final String nome, final String cpf) {
    return new UsuarioComum(nome, cpf);
  }

}
