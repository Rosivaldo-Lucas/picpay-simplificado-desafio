package com.rosivaldolucas.picpaysimplificado.infra.api;

import com.rosivaldolucas.picpaysimplificado.application.transacao.TransacaoInput;
import com.rosivaldolucas.picpaysimplificado.application.transacao.TransacaoOutput;
import com.rosivaldolucas.picpaysimplificado.application.transacao.TransacaoUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController {

  private final TransacaoUseCase transacaoUseCase;

  public TransacaoController(final TransacaoUseCase transacaoUseCase) {
    this.transacaoUseCase = transacaoUseCase;
  }

  @PostMapping
  public ResponseEntity<?> transferir(@RequestBody final TransacaoInput transacaoInput) {
    final TransacaoOutput transacaoOutput = this.transacaoUseCase.execute(transacaoInput);

    return ResponseEntity.status(HttpStatus.CREATED).body(transacaoOutput);
  }

}
