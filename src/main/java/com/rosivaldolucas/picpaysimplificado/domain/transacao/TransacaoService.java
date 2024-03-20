package com.rosivaldolucas.picpaysimplificado.domain.transacao;

import com.rosivaldolucas.picpaysimplificado.api.dto.NovaTransacaoInput;
import com.rosivaldolucas.picpaysimplificado.domain.transacao.autorizar.AutorizarTransacao;
import com.rosivaldolucas.picpaysimplificado.domain.usuario.Usuario;
import com.rosivaldolucas.picpaysimplificado.domain.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

  private final TransacaoRepository transacaoRepository;
  private final UsuarioRepository usuarioRepository;

  private final AutorizarTransacao autorizarTransacao;

  public TransacaoService(final TransacaoRepository transacaoRepository, final UsuarioRepository usuarioRepository, final AutorizarTransacao autorizarTransacao) {
    this.transacaoRepository = transacaoRepository;
    this.usuarioRepository = usuarioRepository;
    this.autorizarTransacao = autorizarTransacao;
  }

  public void criar(final NovaTransacaoInput input) {
    // validar usuario e validar saldo usuario pagador
    // usuario pagador deve ser do tipo COMUM
    final Usuario usuarioPagador = this.usuarioRepository.findById(input.pagador()).orElseThrow(() -> new IllegalArgumentException(""));
    final Usuario usuarioRecebedor = this.usuarioRepository.findById(input.recebedor()).orElseThrow(() -> new IllegalArgumentException(""));

    if (usuarioPagador.isLogista()) {
      throw new IllegalArgumentException("");
    }

    if (usuarioPagador.podeDebitarValor(input.valor())) {
      throw new IllegalArgumentException("");
    }

//    usuarioPagador.debitar(input.valor());
//    usuarioRecebedor.creditar(input.valor());

    // criar transacao
    final Transacao transacao = Transacao.criarCom(input.valor(), usuarioPagador, usuarioRecebedor);
    final Transacao transacaoSalva = this.transacaoRepository.save(transacao);

    // verificar autorizador externo
    this.autorizarTransacao.autorizar(transacaoSalva);

    // enviar email ou sms
  }

}
