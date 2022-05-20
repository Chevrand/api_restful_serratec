package org.serratec.java02backend.projeto03v2.controller;

import java.util.List;
import org.serratec.java02backend.projeto03v2.model.Cliente;
import org.serratec.java02backend.projeto03v2.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/lista")
	public List<Cliente> getCliente() {
		return clienteService.listaCliente();
	}
	
	@GetMapping("/id/{idCliente}")
	public Cliente getClienteById(@PathVariable Integer idCliente) {
		return clienteService.buscaCliente(idCliente);
	}
	
	@PostMapping("/adicionar")
	public void adicionar(@RequestBody Cliente clienteApi) {
		clienteService.adicionaCliente(clienteApi);
	}
	
	@PutMapping("/atualizar/{idCliente}")
	public void atualizar(@PathVariable Integer idCliente, @RequestBody Cliente clienteApi) {
		clienteService.atualizaCliente(idCliente, clienteApi);
	}
	
	@DeleteMapping("/deletar/{idCliente}")
	public void deletar(@PathVariable int idCliente) {
		clienteService.deletaCliente(idCliente);
	}

}
