package com.rosivaldolucas.picpaysimplificado.application;

import com.rosivaldolucas.picpaysimplificado.domain.Usuario;
import com.rosivaldolucas.picpaysimplificado.domain.UsuarioComum;
import com.rosivaldolucas.picpaysimplificado.domain.UsuarioLogista;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransacaoUseCaseTest {

  @Test
  public void t01_deveRealizarTransferenciaDe100ReaisUsuarioComumParaUsuarioLogista() {
    final Usuario usuarioComumPagador = UsuarioComum.criar("Rosivaldo", "01586493469", 150D);
    final Usuario usuarioLogistaRecebedor = UsuarioLogista.criar("Lucas Logista", "01586493469", 500D);
    final double valorTransacao = 100D;

    final TransacaoInput transacaoInput = TransacaoInput.criar(usuarioComumPagador, usuarioLogistaRecebedor, valorTransacao);

    final TransacaoUseCase transacaoUseCase = new TransacaoUseCase();

    final TransacaoOutput transacaoOutput = transacaoUseCase.execute(transacaoInput);

    Assertions.assertEquals("202300000001", transacaoOutput.codigo());
    Assertions.assertEquals("TRANSFERIDO", transacaoOutput.status());
    Assertions.assertEquals(50D, usuarioComumPagador.getSaldo());
    Assertions.assertEquals(600D, usuarioLogistaRecebedor.getSaldo());
  }

}
