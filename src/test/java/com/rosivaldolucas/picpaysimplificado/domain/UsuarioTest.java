package com.rosivaldolucas.picpaysimplificado.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsuarioTest {

  @Test
  public void t01_deveCriarUsuarioComum() {
    final Usuario usuarioComum = UsuarioComum.criar("Rosivaldo", "01586493469", 100D);

    Assertions.assertNotNull(usuarioComum);
    Assertions.assertEquals(TipoUsuario.COMUM, usuarioComum.getTipo());
  }

  @Test
  public void t02_deveCriarUsuarioLogista() {
    final Usuario usuarioLogista = UsuarioLogista.criar("Rosivaldo", "01586493469", 100D);

    Assertions.assertNotNull(usuarioLogista);
    Assertions.assertEquals(TipoUsuario.LOGISTA, usuarioLogista.getTipo());
  }

  @Test
  public void t03_naoDeveCriarUsuarioComSaldoNegativo() {
    final IllegalArgumentException exception = Assertions
            .assertThrows(IllegalArgumentException.class, () -> UsuarioLogista.criar("Rosivaldo", "01586493469", -100D));

    Assertions.assertNotNull(exception);
    Assertions.assertEquals("Saldo usuário não pode ser nulo.", exception.getMessage());
  }

}
