package org.serratec.java02backend.projeto05.controller;

import java.util.List;
import org.serratec.java02backend.projeto05.dto.ClienteDto;
import org.serratec.java02backend.projeto05.exception.ClienteException;
import org.serratec.java02backend.projeto05.model.Cliente;
import org.serratec.java02backend.projeto05.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	public ResponseEntity<Void> salvar(@RequestBody ClienteDto clienteDto) {
		clienteService.salvar(clienteDto);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping("/{idCliente}")
	public ResponseEntity<ClienteDto> buscarPorId(@PathVariable Integer idCliente) throws ClienteException{
		return ResponseEntity.ok(clienteService.buscaPorId(idCliente));
	}
	
	@PutMapping("/{idCliente}")
	public ResponseEntity<Void> atualizar(@PathVariable Integer idCliente, @RequestBody ClienteDto clienteDto) {
		clienteService.atualizar(idCliente, clienteDto);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{idCliente}")
	public ResponseEntity<Void> deletar(@PathVariable Integer idCliente) {
		clienteService.delete(idCliente);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listarCliente() {
		return ResponseEntity.ok(clienteService.listarTodos());
	}

}