package com.rosivaldolucas.picpaysimplificado.application;

import com.rosivaldolucas.picpaysimplificado.domain.Usuario;
import com.rosivaldolucas.picpaysimplificado.domain.Transacao;
import com.rosivaldolucas.picpaysimplificado.infra.TransacaoRepository;
import com.rosivaldolucas.picpaysimplificado.infra.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class TransacaoUseCase {

  private final TransacaoRepository transacaoRepository;
  private final UsuarioRepository usuarioRepository;

  public TransacaoUseCase(final TransacaoRepository transacaoRepository, final UsuarioRepository usuarioRepository) {
    this.transacaoRepository = transacaoRepository;
    this.usuarioRepository = usuarioRepository;
  }

  public TransacaoOutput execute(final TransacaoInput transacaoInput) {
    final Long pagador = transacaoInput.pagador();
    final Long recebedor = transacaoInput.recebedor();
    final Double valor = transacaoInput.valor();

    final Usuario usuarioPagador = this.usuarioRepository.findById(pagador).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
    final Usuario usuarioRecebedor = this.usuarioRepository.findById(recebedor).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

    final Transacao novaTransacao = Transacao.criar(usuarioPagador, usuarioRecebedor);

    novaTransacao.transferir(valor);

    this.transacaoRepository.save(novaTransacao);

    return new TransacaoOutput(novaTransacao.obterCodigo(), novaTransacao.obterStatus().name());
  }

}
