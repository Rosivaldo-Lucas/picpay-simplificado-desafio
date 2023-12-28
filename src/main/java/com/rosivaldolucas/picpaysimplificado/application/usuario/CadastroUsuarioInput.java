package com.rosivaldolucas.picpaysimplificado.application.usuario;

import com.rosivaldolucas.picpaysimplificado.domain.enums.TipoUsuario;

public record CadastroUsuarioInput(
        String nome,
        String cpf,
        TipoUsuario tipo
) {

  public static CadastroUsuarioInput criar(final String nome, final String cpf, final TipoUsuario tipo) {
    return new CadastroUsuarioInput(nome, cpf, tipo);
  }

}
