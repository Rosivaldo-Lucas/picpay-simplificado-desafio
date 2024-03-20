package com.rosivaldolucas.picpaysimplificado.infra;

import com.rosivaldolucas.picpaysimplificado.domain.transacao.AutorizarTransacao;
import com.rosivaldolucas.picpaysimplificado.domain.transacao.Transacao;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Objects;

@Service
public class AutorizarTransacaoServicoExterno implements AutorizarTransacao {

  private final RestClient restClient;

  public AutorizarTransacaoServicoExterno(final RestClient.Builder builder) {
    this.restClient = builder
            .baseUrl("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc")
            .build();
  }

  @Override
  public void autorizar(final Transacao transacao) {
    final ResponseEntity<AutorizacaoTransacao> response = this.restClient
            .get()
            .retrieve()
            .toEntity(AutorizacaoTransacao.class);

    if (response.getStatusCode().isError() || !Objects.requireNonNull(response.getBody()).isAutorizado()) {
      throw new IllegalArgumentException("");
    }
  }

}
