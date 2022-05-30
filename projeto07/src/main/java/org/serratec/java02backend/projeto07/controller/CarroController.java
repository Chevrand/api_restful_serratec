package org.serratec.java02backend.projeto07.controller;

import java.util.List;
import org.serratec.java02backend.projeto07.dto.CarroDto;
import org.serratec.java02backend.projeto07.exception.CarroException;
import org.serratec.java02backend.projeto07.service.CarroService;
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
@RequestMapping("/carro")
public class CarroController {
	
	@Autowired
	CarroService carroService;
	
	@PostMapping
	public ResponseEntity<String> save(@RequestBody CarroDto dto) throws CarroException{
		return ResponseEntity.ok(carroService.save(dto));
	}
	
	@GetMapping
	public ResponseEntity<List<CarroDto>> list() {
		return ResponseEntity.ok(carroService.list());
	}
	
	@GetMapping("/{idCarro}")
	public ResponseEntity<CarroDto> findById(@PathVariable Integer idCarro)
			throws CarroException {
		return ResponseEntity.ok(carroService.findById(idCarro));
	}
	
	@PutMapping("/{idCarro}")
	public ResponseEntity<String> update(@PathVariable Integer idCarro,
			@RequestBody CarroDto dto) throws CarroException {
		return ResponseEntity.ok(carroService.update(idCarro, dto));
	}
	
	@DeleteMapping("/{idCarro}")
	public ResponseEntity<String> delete(@PathVariable Integer idCarro)
			throws CarroException {
		return ResponseEntity.ok(carroService.delete(idCarro));
	}

}