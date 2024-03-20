package com.rosivaldolucas.picpaysimplificado.api;

import com.rosivaldolucas.picpaysimplificado.api.dto.NovaTransacaoInput;
import com.rosivaldolucas.picpaysimplificado.domain.transacao.TransacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

  private final TransacaoService transacaoService;

  public TransacaoController(final TransacaoService transacaoService) {
    this.transacaoService = transacaoService;
  }

  @PostMapping
  public ResponseEntity<Void> criar(@RequestBody @Validated final NovaTransacaoInput input) {
    this.transacaoService.criar(input);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

}
