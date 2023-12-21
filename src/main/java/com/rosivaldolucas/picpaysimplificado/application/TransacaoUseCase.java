package com.rosivaldolucas.picpaysimplificado.application;

import com.rosivaldolucas.picpaysimplificado.domain.Usuario;
import com.rosivaldolucas.picpaysimplificado.domain.Transacao;
import com.rosivaldolucas.picpaysimplificado.infra.repositories.TransacaoRepository;
import com.rosivaldolucas.picpaysimplificado.infra.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class TransacaoUseCase {

  private final TransacaoRepository transacaoRepository;
  private final UsuarioRepository usuarioRepository;
  private final AutorizarTransacao autorizarTransacao;

  public TransacaoUseCase(final TransacaoRepository transacaoRepository, final UsuarioRepository usuarioRepository, final AutorizarTransacao autorizarTransacao) {
    this.transacaoRepository = transacaoRepository;
    this.usuarioRepository = usuarioRepository;
    this.autorizarTransacao = autorizarTransacao;
  }

  public TransacaoOutput execute(final TransacaoInput transacaoInput) {
    final Long pagador = transacaoInput.pagador();
    final Long recebedor = transacaoInput.recebedor();
    final Double valor = transacaoInput.valor();

    final Usuario usuarioPagador = this.usuarioRepository.findById(pagador).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));
    final Usuario usuarioRecebedor = this.usuarioRepository.findById(recebedor).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

    final Transacao novaTransacao = Transacao.criar(usuarioPagador, usuarioRecebedor);

    final StatusAutorizadorTransacao status = this.autorizarTransacao.autorizar();

    if (!status.equals(StatusAutorizadorTransacao.AUTORIZADO)) {
      throw new IllegalArgumentException("Transação não autorizada.");
    }

    novaTransacao.transferir(valor);

    this.transacaoRepository.save(novaTransacao);

    return new TransacaoOutput(novaTransacao.obterCodigo(), novaTransacao.obterStatus().name());
  }

}
