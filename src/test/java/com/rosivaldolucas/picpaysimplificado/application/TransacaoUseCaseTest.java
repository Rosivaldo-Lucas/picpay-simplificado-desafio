package com.rosivaldolucas.picpaysimplificado.application;

import com.rosivaldolucas.picpaysimplificado.domain.Transacao;
import com.rosivaldolucas.picpaysimplificado.domain.Usuario;
import com.rosivaldolucas.picpaysimplificado.domain.UsuarioComum;
import com.rosivaldolucas.picpaysimplificado.domain.UsuarioLogista;
import com.rosivaldolucas.picpaysimplificado.infra.TransacaoRepository;
import com.rosivaldolucas.picpaysimplificado.infra.UsuarioRepository;
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

  private TransacaoUseCase transacaoUseCase;

  @BeforeEach
  public void setup() {
    this.transacaoUseCase = new TransacaoUseCase(this.transacaoRepository, this.usuarioRepository);
  }

  @Test
  public void t01_deveRealizarTransferenciaDe100ReaisUsuarioComumParaUsuarioLogista() {
    final Usuario usuarioComumPagador = UsuarioComum.criar("Rosivaldo", "01586493469", 150D);
    final Usuario usuarioLogistaRecebedor = UsuarioLogista.criar("Lucas Logista", "01586493469", 500D);
    final double valorTransacao = 100D;

    final TransacaoInput transacaoInput = TransacaoInput.criar(1L, 2L, valorTransacao);

    Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.of(usuarioComumPagador));
    Mockito.when(this.usuarioRepository.findById(2L)).thenReturn(Optional.of(usuarioLogistaRecebedor));
    Mockito.when(this.transacaoRepository.save(Mockito.any(Transacao.class))).thenReturn(Mockito.any(Transacao.class));

    final TransacaoOutput transacaoOutput = transacaoUseCase.execute(transacaoInput);

    Assertions.assertEquals("202300000001", transacaoOutput.codigo());
    Assertions.assertEquals("TRANSFERIDO", transacaoOutput.status());
    Assertions.assertEquals(50D, usuarioComumPagador.getSaldo());
    Assertions.assertEquals(600D, usuarioLogistaRecebedor.getSaldo());
  }

}
