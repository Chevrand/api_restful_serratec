package org.serratec.java02backend.projeto05.controller;

import java.io.IOException;
import java.util.List;
import org.serratec.java02backend.projeto05.dto.CartaoDto;
import org.serratec.java02backend.projeto05.exception.CartaoException;
import org.serratec.java02backend.projeto05.service.CartaoService;
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
@RequestMapping("/cartao")
public class CartaoController {
	
	@Autowired
	CartaoService cartaoService;
	
	@PostMapping
	public ResponseEntity<String> criarCartao(@RequestBody CartaoDto cartaoDto) {
		return ResponseEntity.ok(cartaoService.salvar(cartaoDto));
	}
	
	@PostMapping("/salvar-lista")
	public ResponseEntity<String> salvarVariosCartoes(@RequestBody List<CartaoDto> cartoesNovos) {
		return ResponseEntity.ok(cartaoService.salvarVariosCartoes(cartoesNovos));
	}
	
	@PostMapping("/leitor")
	public ResponseEntity<Void> criarCartaoTxt() throws IOException {
		cartaoService.leitor();
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<CartaoDto>> listarCartoes() {
		return ResponseEntity.ok(cartaoService.listarCartoes());
	}
	
	@GetMapping("/{idCartao}")
	public ResponseEntity<CartaoDto> buscarCartaoById(@PathVariable Integer idCartao) throws CartaoException{
		return ResponseEntity.ok(cartaoService.buscarCartaoById(idCartao));
	}
	
	@PutMapping("/{idCartao}")
	public ResponseEntity<String> atualizarCartao(@PathVariable Integer idCartao,
			@RequestBody CartaoDto cartaoDto) throws CartaoException {
		return ResponseEntity.ok(cartaoService.atualizarCartao(idCartao, cartaoDto));
	}
	
	@DeleteMapping("/{idCartao}")
	public ResponseEntity<String> deletarCartao(@PathVariable Integer idCartao) throws CartaoException {
		return ResponseEntity.ok(cartaoService.deletarCartao(idCartao));
	}
		
}