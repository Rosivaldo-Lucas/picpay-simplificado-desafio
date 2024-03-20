package com.rosivaldolucas.picpaysimplificado.domain.usuario;

import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;

  public UsuarioService(final UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public Usuario buscar(final Long id) {
    return this.usuarioRepository
            .findById(id)
            .orElseThrow(() -> new IllegalArgumentException(""));
  }

}
