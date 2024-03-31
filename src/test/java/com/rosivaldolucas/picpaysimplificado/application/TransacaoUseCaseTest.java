<<<<<<< HEAD
//package com.rosivaldolucas.picpaysimplificado.application;
//
//import com.rosivaldolucas.picpaysimplificado.domain.entities.Transacao;
//import com.rosivaldolucas.picpaysimplificado.domain.entities.Usuario;
//import com.rosivaldolucas.picpaysimplificado.domain.entities.UsuarioComum;
//import com.rosivaldolucas.picpaysimplificado.domain.entities.UsuarioLogista;
//import com.rosivaldolucas.picpaysimplificado.infra.repositories.TransacaoRepository;
//import com.rosivaldolucas.picpaysimplificado.infra.repositories.UsuarioRepository;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//@ExtendWith(MockitoExtension.class)
//public class TransacaoUseCaseTest {
//
//  @Mock
//  private TransacaoRepository transacaoRepository;
//  @Mock
//  private UsuarioRepository usuarioRepository;
//  @Mock
//  private AutorizarTransacao autorizarTransacao;
//
//  private TransacaoUseCase transacaoUseCase;
//
//  @BeforeEach
//  public void setup() {
//    this.transacaoUseCase = new TransacaoUseCase(this.transacaoRepository, this.usuarioRepository, this.autorizarTransacao);
//  }
//
//  @Test
//  public void t01_deveRealizarTransferenciaDe100ReaisUsuarioComumParaUsuarioLogista() {
//    final Usuario usuarioComumPagador = UsuarioComum.criar("Rosivaldo", "01586493469", 150D);
//    final Usuario usuarioLogistaRecebedor = UsuarioLogista.criar("Lucas Logista", "01586493469", 500D);
//    final double valorTransacao = 100D;
//
//    final TransacaoInput transacaoInput = TransacaoInput.criar(1L, 2L, valorTransacao);
//
//    Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioComumPagador));
//    Mockito.when(this.usuarioRepository.findById(2L)).thenReturn(Optional.of(usuarioLogistaRecebedor));
//    Mockito.when(this.autorizarTransacao.autorizar()).thenReturn(StatusAutorizadorTransacao.AUTORIZADO);
//    Mockito.when(this.transacaoRepository.save(Mockito.any(Transacao.class))).thenReturn(Mockito.any(Transacao.class));
//
//    final TransacaoOutput transacaoOutput = transacaoUseCase.execute(transacaoInput);
//
//    Assertions.assertNotNull(transacaoOutput.codigo());
//    Assertions.assertEquals("TRANSFERIDO", transacaoOutput.status());
//    Assertions.assertEquals(50D, usuarioComumPagador.getSaldo());
//    Assertions.assertEquals(600D, usuarioLogistaRecebedor.getSaldo());
//  }
//
//}
=======
package com.rosivaldolucas.picpaysimplificado.application;

import com.rosivaldolucas.picpaysimplificado.application.transacao.StatusAutorizadorTransacao;
import com.rosivaldolucas.picpaysimplificado.application.transacao.TransacaoInput;
import com.rosivaldolucas.picpaysimplificado.application.transacao.TransacaoOutput;
import com.rosivaldolucas.picpaysimplificado.application.transacao.TransacaoUseCase;
import com.rosivaldolucas.picpaysimplificado.domain.entities.Transacao;
import com.rosivaldolucas.picpaysimplificado.domain.entities.Usuario;
import com.rosivaldolucas.picpaysimplificado.domain.entities.UsuarioComum;
import com.rosivaldolucas.picpaysimplificado.domain.entities.UsuarioLogista;
import com.rosivaldolucas.picpaysimplificado.infra.repositories.TransacaoRepository;
import com.rosivaldolucas.picpaysimplificado.infra.repositories.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TransacaoUseCaseTest {

  @Mock
  private TransacaoRepository transacaoRepository;
  @Mock
  private UsuarioRepository usuarioRepository;
  @Mock
  private AutorizarTransacao autorizarTransacao;

  private TransacaoUseCase transacaoUseCase;

  @BeforeEach
  public void setup() {
    this.transacaoUseCase = new TransacaoUseCase(this.transacaoRepository, this.usuarioRepository, this.autorizarTransacao);
  }

  @Test
  public void t01_deveRealizarTransferenciaDe100ReaisUsuarioComumParaUsuarioLogista() {
    final Usuario usuarioComumPagador = UsuarioComum.criar("Rosivaldo", "01586493469");
    final Usuario usuarioLogistaRecebedor = UsuarioLogista.criar("Lucas Logista", "01586493469");
    final double valorTransacao = 100D;

    usuarioComumPagador.depositar(150D);
    usuarioLogistaRecebedor.depositar(500D);

    final TransacaoInput transacaoInput = TransacaoInput.criar(1L, 2L, valorTransacao);

    Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioComumPagador));
    Mockito.when(this.usuarioRepository.findById(2L)).thenReturn(Optional.of(usuarioLogistaRecebedor));
    Mockito.when(this.autorizarTransacao.autorizar()).thenReturn(StatusAutorizadorTransacao.AUTORIZADO);
    Mockito.when(this.transacaoRepository.save(Mockito.any(Transacao.class))).thenReturn(Mockito.any(Transacao.class));

    final TransacaoOutput transacaoOutput = transacaoUseCase.execute(transacaoInput);

    Assertions.assertNotNull(transacaoOutput.codigo());
    Assertions.assertEquals("TRANSFERIDO", transacaoOutput.status());
    Assertions.assertEquals(50D, usuarioComumPagador.getSaldo());
    Assertions.assertEquals(600D, usuarioLogistaRecebedor.getSaldo());
  }

}
>>>>>>> develop
