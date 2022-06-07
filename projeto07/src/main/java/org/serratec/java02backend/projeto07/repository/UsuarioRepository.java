package org.serratec.java02backend.projeto07.repository;

import java.util.Optional;

import org.serratec.java02backend.projeto07.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	
	@Query(value="FROM Usuario u WHERE u.login = ?1")
	Optional<Usuario> buscarPorLogin(String login);

}