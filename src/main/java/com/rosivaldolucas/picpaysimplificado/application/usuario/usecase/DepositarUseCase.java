package com.rosivaldolucas.picpaysimplificado.application.usuario.usecase;

import com.rosivaldolucas.picpaysimplificado.application.usuario.DepositarInput;
import com.rosivaldolucas.picpaysimplificado.application.usuario.DepositarOutput;
import com.rosivaldolucas.picpaysimplificado.domain.entities.Usuario;
import com.rosivaldolucas.picpaysimplificado.infra.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class DepositarUseCase {

  private final UsuarioRepository usuarioRepository;

  public DepositarUseCase(final UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public DepositarOutput execute(final DepositarInput input) {
    final Long usuarioId = input.usuario();
    final Double valor = input.valor();

    final Usuario usuarioCadastrado = this.usuarioRepository.findById(usuarioId).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado."));

    usuarioCadastrado.depositar(valor);

    this.usuarioRepository.save(usuarioCadastrado);

    return new DepositarOutput();
  }

}
