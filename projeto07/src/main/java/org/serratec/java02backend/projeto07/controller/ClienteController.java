package org.serratec.java02backend.projeto07.controller;

import java.util.List;
import org.serratec.java02backend.projeto07.dto.ClienteDto;
import org.serratec.java02backend.projeto07.exception.ClienteException;
import org.serratec.java02backend.projeto07.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<String> save(@RequestBody ClienteDto dto) {
		return ResponseEntity.ok(clienteService.save(dto));
	}
	
	@GetMapping
	public ResponseEntity<List<ClienteDto>> list() {
		return ResponseEntity.ok(clienteService.list());
	}
	
	@GetMapping("/{idCliente}")
	public ResponseEntity<ClienteDto> findById(@PathVariable Integer idCliente)
			throws ClienteException {
		return ResponseEntity.ok(clienteService.findById(idCliente));
		
	}
	
	@PutMapping("/{idCliente}")
	public ResponseEntity<String> update(@PathVariable Integer idCliente,
			@RequestBody ClienteDto dto) throws ClienteException {
		return ResponseEntity.ok(clienteService.update(idCliente, dto));
		
	}
	
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<String> delete(@PathVariable Integer idCliente)
			throws ClienteException {
		return ResponseEntity.ok(clienteService.delete(idCliente));
	}

}