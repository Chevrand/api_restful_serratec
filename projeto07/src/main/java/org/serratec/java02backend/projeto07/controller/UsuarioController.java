package org.serratec.java02backend.projeto07.controller;

import java.util.List;
import org.serratec.java02backend.projeto07.dto.UsuarioDto;
import org.serratec.java02backend.projeto07.exception.UsuarioException;
import org.serratec.java02backend.projeto07.security.JwtUtil;
import org.serratec.java02backend.projeto07.security.UsuarioAuthenticationRequest;
import org.serratec.java02backend.projeto07.security.UsuarioAuthenticationResponse;
import org.serratec.java02backend.projeto07.security.UsuarioDetalheService;
import org.serratec.java02backend.projeto07.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioDetalheService usuarioDetalheService;

	@Autowired
	JwtUtil jwtUtil;

	@PostMapping("/salvar")
	public ResponseEntity<String> save(@RequestBody UsuarioDto dto) {
		return ResponseEntity.ok(usuarioService.save(dto));
	}

	@GetMapping
	public ResponseEntity<List<UsuarioDto>> findAll() {
		return ResponseEntity.ok(usuarioService.findAll());
	}

	@GetMapping("/buscar-id/{idUsuario}")
	public ResponseEntity<UsuarioDto> findById(@PathVariable Integer idUsuario) throws UsuarioException {
		return ResponseEntity.ok(usuarioService.findById(idUsuario));
	}

	@GetMapping("/buscar-login")
	public ResponseEntity<UsuarioDto> findByLogin(@RequestParam String loginUsuario) throws UsuarioException {
		return ResponseEntity.ok(usuarioService.findByLogin(loginUsuario));
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> criarAutenticacao(@RequestBody UsuarioAuthenticationRequest usuario) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(usuario.getUsername(), usuario.getPassword()));
		} catch (Exception e) {
			throw new Exception("Senha incorreta", e);
		}
		UserDetails usuarioDetalhe = usuarioDetalheService.loadUserByUsername(usuario.getUsername());
		String token = jwtUtil.generateToken(usuarioDetalhe);
		return ResponseEntity.ok(new UsuarioAuthenticationResponse(token));
	}

}