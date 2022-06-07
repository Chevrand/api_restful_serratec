package org.serratec.java02backend.projeto07.service;

import java.util.List;
import java.util.stream.Collectors;
import org.serratec.java02backend.projeto07.dto.UsuarioDto;
import org.serratec.java02backend.projeto07.exception.UsuarioException;
import org.serratec.java02backend.projeto07.model.Usuario;
import org.serratec.java02backend.projeto07.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public UsuarioDto toDto(Usuario model, UsuarioDto dto) {		
		dto.setIdUsuario(model.getIdUsuario());
		dto.setLogin(model.getLogin());
		dto.setSenha(model.getSenha());
		
		return dto;
	}
	
	public Usuario toModel(UsuarioDto dto, Usuario model) {
		model.setLogin(dto.getLogin());
		model.setSenha(passwordEncoder.encode(dto.getSenha()));
		
		return model;
	}
	
	public String save(UsuarioDto dto) {
		Usuario model = new Usuario();
		usuarioRepository.save(toModel(dto, model));
		return String.format("Usuario ID %d cadastrado com sucesso!", model.getIdUsuario());
	}
	
	public UsuarioDto findById(Integer idUsuario) throws UsuarioException {
		return usuarioRepository.findById(idUsuario)
				.map(model -> toDto(model, new UsuarioDto()))
				.orElseThrow(() -> new UsuarioException("Não foi possível cadastrar o usuário!"));
	}
	
	public List<UsuarioDto> findAll() {
		return usuarioRepository.findAll()
				.stream()
				.map(model -> toDto(model, new UsuarioDto()))
				.collect(Collectors.toList());
	}
	
	public UsuarioDto findByLogin(String loginUsuario) throws UsuarioException {
		return usuarioRepository.findAll()
				.stream()
				.filter(usuario -> usuario.getLogin().equals(loginUsuario))
				.map(usuario -> toDto(usuario, new UsuarioDto()))
				.findFirst()
				.orElseThrow(() -> new UsuarioException("Não foi possível encontrar o usuário!"));
	}
	
}