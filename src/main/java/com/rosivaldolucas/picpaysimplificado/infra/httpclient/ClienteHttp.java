package com.rosivaldolucas.picpaysimplificado.infra.httpclient;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Component
public class ClienteHttp {

  public String get(final String uri) {
    final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    final HttpRequest request;

    try {
      request = HttpRequest.newBuilder()
              .uri(new URI(uri))
              .GET()
              .timeout(Duration.ofSeconds(10))
              .build();
    } catch (final URISyntaxException e) {
      throw new IllegalArgumentException("err");
    }

    return client
            .sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .join();
  }

}
