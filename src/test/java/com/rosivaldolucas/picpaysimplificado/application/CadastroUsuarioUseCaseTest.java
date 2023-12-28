package com.rosivaldolucas.picpaysimplificado.application;

import com.rosivaldolucas.picpaysimplificado.application.usuario.usecase.CadastrarUsuarioUseCase;
import com.rosivaldolucas.picpaysimplificado.application.usuario.CadastroUsuarioInput;
import com.rosivaldolucas.picpaysimplificado.application.usuario.CadastroUsuarioOutput;
import com.rosivaldolucas.picpaysimplificado.domain.enums.TipoUsuario;
import com.rosivaldolucas.picpaysimplificado.infra.repositories.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class CadastroUsuarioUseCaseTest {

  @Mock
  private UsuarioRepository usuarioRepository;

  private CadastrarUsuarioUseCase cadastrarUsuarioUseCase;

  @BeforeEach
  public void setup() {
    cadastrarUsuarioUseCase = new CadastrarUsuarioUseCase(this.usuarioRepository);
  }

  @Test
  public void t01_deveCadastrarUsuarioComum() {
    final CadastroUsuarioInput input = CadastroUsuarioInput.criar("Rosivaldo", "01586493469", TipoUsuario.COMUM);

    final CadastroUsuarioOutput output = cadastrarUsuarioUseCase.execute(input);

    Assertions.assertEquals(1L, output.id());
  }

}
