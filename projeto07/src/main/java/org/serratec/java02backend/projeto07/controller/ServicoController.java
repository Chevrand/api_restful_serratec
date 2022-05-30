package org.serratec.java02backend.projeto07.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.serratec.java02backend.projeto07.dto.ServicoDto;
import org.serratec.java02backend.projeto07.exception.EmailException;
import org.serratec.java02backend.projeto07.exception.ServicoException;
import org.serratec.java02backend.projeto07.model.Servico;
import org.serratec.java02backend.projeto07.service.ServicoService;
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
@RequestMapping("/servico")
public class ServicoController {
	
	@Autowired
	ServicoService servicoService;
	
	@PostMapping
	public ResponseEntity<String> save(@RequestBody ServicoDto dto) throws ServicoException, MessagingException, EmailException {
		return ResponseEntity.ok(servicoService.save(dto));
	}
	
	@GetMapping
	public ResponseEntity<List<ServicoDto>> list() {
		return ResponseEntity.ok(servicoService.list());
	}
	
	@GetMapping("/list-5-last")
	public ResponseEntity<List<Servico>> list5Last() {
		return ResponseEntity.ok(servicoService.listFiveLast());
	}
	
	@GetMapping("/{idServico}")
	public ResponseEntity<ServicoDto> findById(@PathVariable Integer idServico)
			throws ServicoException {
		return ResponseEntity.ok(servicoService.findById(idServico));
	}
	
	@PutMapping("/{idServico}")
	public ResponseEntity<String> update(@PathVariable Integer idServico,
			@RequestBody ServicoDto dto) throws ServicoException {
		return ResponseEntity.ok(servicoService.update(idServico, dto));
	}
	
	@DeleteMapping("/{idServico}")
	public ResponseEntity<String> delete(@PathVariable Integer idServico)
			throws ServicoException {
		return ResponseEntity.ok(servicoService.delete(idServico));
	}

}