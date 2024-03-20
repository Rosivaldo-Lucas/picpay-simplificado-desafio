package com.rosivaldolucas.picpaysimplificado.domain.transacao;

import com.rosivaldolucas.picpaysimplificado.api.dto.NovaTransacaoInput;
import com.rosivaldolucas.picpaysimplificado.domain.transacao.autorizar.AutorizarTransacao;
import com.rosivaldolucas.picpaysimplificado.domain.usuario.Usuario;
import com.rosivaldolucas.picpaysimplificado.domain.usuario.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

  private final TransacaoRepository transacaoRepository;
  private final UsuarioService usuarioService;

  private final AutorizarTransacao autorizarTransacao;

  public TransacaoService(final TransacaoRepository transacaoRepository, final UsuarioService usuarioService, final AutorizarTransacao autorizarTransacao) {
    this.transacaoRepository = transacaoRepository;
    this.usuarioService = usuarioService;
    this.autorizarTransacao = autorizarTransacao;
  }

  public void criar(final NovaTransacaoInput input) {
    final Usuario usuarioPagador = this.usuarioService.buscar(input.pagador());
    final Usuario usuarioRecebedor = this.usuarioService.buscar(input.recebedor());

    usuarioPagador.podeRealizarPagamento();

    usuarioPagador.debitar(input.valor());
    usuarioRecebedor.creditar(input.valor());

    final Transacao transacao = Transacao.criarCom(input.valor(), usuarioPagador, usuarioRecebedor);
    final Transacao transacaoSalva = this.transacaoRepository.save(transacao);

    this.autorizarTransacao.autorizar(transacaoSalva);
  }

}
