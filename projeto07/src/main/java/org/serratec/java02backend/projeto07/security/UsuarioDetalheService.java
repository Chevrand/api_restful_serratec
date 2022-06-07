package org.serratec.java02backend.projeto07.security;

import java.util.ArrayList;
import java.util.Optional;
import org.serratec.java02backend.projeto07.model.Usuario;
import org.serratec.java02backend.projeto07.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetalheService implements UserDetailsService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario> usuario = usuarioRepository.buscarPorLogin(username);
		
		if(usuario.isPresent()) {
			Usuario u = usuario.get();
			return new User(u.getLogin(), u.getSenha(), new ArrayList<>());
		}
		
		throw new UsernameNotFoundException("Usuario incorreto!");
	}

}