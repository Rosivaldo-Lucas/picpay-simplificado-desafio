package com.rosivaldolucas.picpaysimplificado.infra;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rosivaldolucas.picpaysimplificado.application.AutorizarTransacao;
import com.rosivaldolucas.picpaysimplificado.application.StatusAutorizadorTransacao;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class AutorizarTransacaoMock implements AutorizarTransacao {

  public static final String URI_AUTORIZADOR_MOCK = "https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc";

  @Override
  public StatusAutorizadorTransacao autorizar() {
    final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    final HttpRequest request;

    try {
      request = HttpRequest.newBuilder()
              .uri(new URI(URI_AUTORIZADOR_MOCK))
              .GET()
              .timeout(Duration.ofSeconds(10))
              .build();
    } catch (final URISyntaxException e) {
      throw new IllegalArgumentException("err");
    }

    final String response = client
            .sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .join();

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
