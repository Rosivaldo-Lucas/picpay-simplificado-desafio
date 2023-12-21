package com.rosivaldolucas.picpaysimplificado.infra.repositories;

import com.rosivaldolucas.picpaysimplificado.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
