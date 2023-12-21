package com.rosivaldolucas.picpaysimplificado.domain;

import com.rosivaldolucas.picpaysimplificado.domain.entities.Transacao;
import com.rosivaldolucas.picpaysimplificado.domain.entities.Usuario;
import com.rosivaldolucas.picpaysimplificado.domain.entities.UsuarioComum;
import com.rosivaldolucas.picpaysimplificado.domain.entities.UsuarioLogista;
import com.rosivaldolucas.picpaysimplificado.domain.enums.StatusTransacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TransacaoTest {

  @Test
  public void t01_deveCriarTransacaoComStatusPendente() {
    final Usuario usuarioComumPagador = UsuarioComum.criar("Rosivaldo", "01586493469", 150D);
    final Usuario usuarioLogistaRecebedor = UsuarioLogista.criar("Rosivaldo", "01586493469", 100D);

    final Transacao novaTransacao = Transacao.criar(usuarioComumPagador, usuarioLogistaRecebedor);

    Assertions.assertEquals(150D, usuarioComumPagador.getSaldo());
    Assertions.assertEquals(100D, usuarioLogistaRecebedor.getSaldo());
    Assertions.assertEquals(StatusTransacao.PENDENTE, novaTransacao.obterStatus());
  }

  @Test
  public void t02_deveTransferir100ReaisUsuarioComumParaUsuarioLogista() {
    final Usuario usuarioComumPagador = UsuarioComum.criar("Rosivaldo", "01586493469", 150D);
    final Usuario usuarioLogistaRecebedor = UsuarioLogista.criar("Rosivaldo", "01586493469", 100D);

    final Transacao novaTransacao = Transacao.criar(usuarioComumPagador, usuarioLogistaRecebedor);

    novaTransacao.transferir(100D);

    Assertions.assertEquals(50D, usuarioComumPagador.getSaldo());
    Assertions.assertEquals(200D, usuarioLogistaRecebedor.getSaldo());
    Assertions.assertEquals(StatusTransacao.TRANSFERIDO, novaTransacao.obterStatus());
  }

  @Test
  public void t03_naoDeveTransferirUsuarioLogistaParaUsuarioComum() {
    final Usuario usuarioLogistaPagador = UsuarioLogista.criar("Rosivaldo", "01586493469", 100D);
    final Usuario usuarioComumRecebedor = UsuarioComum.criar("Rosivaldo", "01586493469", 150D);

    final IllegalArgumentException exception = Assertions
            .assertThrows(IllegalArgumentException.class, () -> Transacao.criar(usuarioLogistaPagador, usuarioComumRecebedor));

    Assertions.assertNotNull(exception);
    Assertions.assertEquals("Usuário pagador não está habilitado para transferencias.", exception.getMessage());
  }

  @Test
  public void t04_deveTransferir100ReaisUsuarioComumParaUsuarioComum() {
    final Usuario usuarioComumPagador = UsuarioComum.criar("Rosivaldo", "01586493469", 150D);
    final Usuario usuarioComumRecebedor = UsuarioComum.criar("Lucas", "01586493469", 100D);

    final Transacao novaTransacao = Transacao.criar(usuarioComumPagador, usuarioComumRecebedor);

    novaTransacao.transferir(100D);

    Assertions.assertEquals(50D, usuarioComumPagador.getSaldo());
    Assertions.assertEquals(200D, usuarioComumRecebedor.getSaldo());
    Assertions.assertEquals(StatusTransacao.TRANSFERIDO, novaTransacao.obterStatus());
  }

}
