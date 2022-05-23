package org.serratec.java02backend.projeto04v2.controller;

import java.util.List;

import org.serratec.java02backend.projeto04v2.exception.ContaException;
import org.serratec.java02backend.projeto04v2.model.Conta;
import org.serratec.java02backend.projeto04v2.service.ContaService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/conta")
public class ContaController {
	
	@Autowired
	ContaService contaService;
	
	@GetMapping
	public ResponseEntity<List<Conta>> listarContas() {
		return ResponseEntity.ok(contaService.listaConta());
	}
	
	@GetMapping("/{idConta}")
	public ResponseEntity<Conta> buscarContaPorId(@PathVariable Integer idConta) {
		return ResponseEntity.ok(contaService.buscaContaPorId(idConta));
	}
	
	@PostMapping
	public ResponseEntity<Void> salvarConta(@RequestBody Conta contaApi) {
		contaService.salvar(contaApi);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping("/{idConta}")
	public ResponseEntity<Void> atualizarConta(@PathVariable Integer idConta, @RequestBody Conta contaApi) {
		contaService.atualizaConta(idConta, contaApi);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/{idConta}")
	public ResponseEntity<Void> transferirValorConta(
			@PathVariable Integer idConta,
			@RequestParam String operacao, @RequestParam Double valor) throws ContaException {
		contaService.transfereValorConta(idConta, operacao, valor);
		return new ResponseEntity <>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("{idConta}")
	public ResponseEntity<Void> deletarConta(@PathVariable Integer idConta) {
		contaService.deletaConta(idConta);
		return new ResponseEntity <>(HttpStatus.ACCEPTED);
	}
}