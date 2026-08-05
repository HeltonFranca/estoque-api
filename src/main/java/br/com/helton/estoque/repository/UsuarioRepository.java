package br.com.helton.estoque.repository;

import br.com.helton.estoque.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

Optional<Usuario> findByUsername(String username);

boolean existsByUsername(String username);

}
