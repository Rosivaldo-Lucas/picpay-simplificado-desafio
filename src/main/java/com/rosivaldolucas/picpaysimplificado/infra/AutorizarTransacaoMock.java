package com.rosivaldolucas.picpaysimplificado.infra;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rosivaldolucas.picpaysimplificado.application.StatusAutorizadorTransacao;
import com.rosivaldolucas.picpaysimplificado.application.AutorizarTransacao;
import com.rosivaldolucas.picpaysimplificado.infra.httpclient.ClienteHttp;
import org.springframework.stereotype.Component;

@Component
public class AutorizarTransacaoMock implements AutorizarTransacao {

  public static final String URI_AUTORIZADOR_MOCK = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";

  private final ClienteHttp clienteHttp;

  public AutorizarTransacaoMock(final ClienteHttp clienteHttp) {
    this.clienteHttp = clienteHttp;
  }

  @Override
  public StatusAutorizadorTransacao autorizar() {
    final String response = this.clienteHttp.get(URI_AUTORIZADOR_MOCK);

    final MensagemSevicoAutorizador mensagemSevicoAutorizador = MensagemSevicoAutorizador.parseResponseToMensagemSevicoAutorizador(response);

    return StatusAutorizadorTransacao.fromString(mensagemSevicoAutorizador.mensagem);
  }

  private record MensagemSevicoAutorizador(@JsonProperty("message") String mensagem) {

    public static MensagemSevicoAutorizador parseResponseToMensagemSevicoAutorizador(final String jsonResponse) {
      final ObjectMapper objectMapper = new ObjectMapper();

      try {
        return objectMapper.readValue(jsonResponse, MensagemSevicoAutorizador.class);
      } catch (final JsonProcessingException e) {
        throw new IllegalArgumentException("Erro ao obter o status de autorização.", e);
      }
    }
  }

}
