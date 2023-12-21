package com.rosivaldolucas.picpaysimplificado.domain;

import com.rosivaldolucas.picpaysimplificado.domain.enums.TipoUsuario;
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

  @Test
  public void t04_deveDepositar100ReaisSaldoUsuario() {
    final Usuario usuario = UsuarioLogista.criar("Rosivaldo", "01586493469", 100D);

    usuario.depositar(100D);

    Assertions.assertNotNull(usuario);
    Assertions.assertEquals(200D, usuario.getSaldo());
  }

  @Test
  public void t05_deveRetirar100ReaisSaldoUsuario() {
    final Usuario usuario = UsuarioLogista.criar("Rosivaldo", "01586493469", 100D);

    usuario.retirar(100D);

    Assertions.assertNotNull(usuario);
    Assertions.assertEquals(0, usuario.getSaldo());
  }

  @Test
  public void t06_naoDeveRetirarQuandoValorMaiorQueSaldoUsuario() {
    final Usuario usuario = UsuarioLogista.criar("Rosivaldo", "01586493469", 100D);

    final IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> usuario.retirar(500D));

    Assertions.assertNotNull(exception);
    Assertions.assertEquals("Usuário não tem saldo suficiente para retirar.", exception.getMessage());
    Assertions.assertEquals(100D, usuario.getSaldo());
  }

}
