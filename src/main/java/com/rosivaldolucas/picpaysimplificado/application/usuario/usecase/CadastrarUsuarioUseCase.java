package com.rosivaldolucas.picpaysimplificado.application.usuario.usecase;

import com.rosivaldolucas.picpaysimplificado.application.usuario.CadastroUsuarioInput;
import com.rosivaldolucas.picpaysimplificado.application.usuario.CadastroUsuarioOutput;
import com.rosivaldolucas.picpaysimplificado.domain.entities.Usuario;
import com.rosivaldolucas.picpaysimplificado.domain.entities.UsuarioComum;
import com.rosivaldolucas.picpaysimplificado.domain.entities.UsuarioLogista;
import com.rosivaldolucas.picpaysimplificado.domain.enums.TipoUsuario;
import com.rosivaldolucas.picpaysimplificado.infra.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastrarUsuarioUseCase {

  private final UsuarioRepository usuarioRepository;

  public CadastrarUsuarioUseCase(final UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public CadastroUsuarioOutput execute(final CadastroUsuarioInput cadastroUsuarioInput) {
    final String cpf = cadastroUsuarioInput.cpf();

    final Optional<Usuario> usuarioOptional = this.usuarioRepository.findByCpf(cpf);

    if (usuarioOptional.isPresent()) {
      throw new IllegalArgumentException("Usuário já cadastrado.");
    }

    final Usuario novoUsuario = this.criarUsuario(cadastroUsuarioInput);

    final Usuario novoUsuarioCadastrado = this.usuarioRepository.save(novoUsuario);

    return new CadastroUsuarioOutput(novoUsuarioCadastrado.getId());
  }

  private Usuario criarUsuario(final CadastroUsuarioInput input) {
    final String nome = input.nome();
    final String cpf = input.cpf();
    final TipoUsuario tipo = input.tipo();

    return tipo.equals(TipoUsuario.COMUM) ? UsuarioComum.criar(nome, cpf) : UsuarioLogista.criar(nome, cpf);
  }

}
