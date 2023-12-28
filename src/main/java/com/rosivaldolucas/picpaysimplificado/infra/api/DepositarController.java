package com.rosivaldolucas.picpaysimplificado.infra.api;

import com.rosivaldolucas.picpaysimplificado.application.usuario.DepositarInput;
import com.rosivaldolucas.picpaysimplificado.application.usuario.DepositarOutput;
import com.rosivaldolucas.picpaysimplificado.application.usuario.usecase.DepositarUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class DepositarController {

  private final DepositarUseCase depositarUseCase;

  public DepositarController(final DepositarUseCase depositarUseCase) {
    this.depositarUseCase = depositarUseCase;
  }

  @PostMapping("/{id}/depositar")
  public ResponseEntity<DepositarOutput> depositar(@PathVariable final Long id, @RequestBody final DepositarInput input) {
    final DepositarOutput depositoRealizado = this.depositarUseCase.execute(new DepositarInput(id, input.valor()));

    return ResponseEntity.status(HttpStatus.CREATED).body(depositoRealizado);
  }

}
