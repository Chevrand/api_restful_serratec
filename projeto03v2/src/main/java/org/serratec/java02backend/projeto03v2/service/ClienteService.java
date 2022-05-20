package org.serratec.java02backend.projeto03v2.service;

import java.util.ArrayList;
import java.util.List;

import org.serratec.java02backend.projeto03v2.model.Cliente;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	List<Cliente> lista = new ArrayList<>();
	
	public List<Cliente> listaCliente() {
		return this.lista;
	}
	
	public Cliente buscaCliente(Integer idCliente) {
		Cliente clienteDesejado = new Cliente();
		
		for(Cliente cliente : lista) {
			if(cliente.getId().equals(idCliente)) {
				clienteDesejado = cliente;
			}
		}
		
		return clienteDesejado;
	}
	
	public void adicionaCliente(Cliente clienteApi) {
		lista.add(clienteApi);
	}
	
	public void atualizaCliente(Integer idCliente, Cliente clienteApi) {
		Cliente clienteDesejado = new Cliente();
		
		for(Cliente cliente : lista) {
			if(cliente.getId().equals(idCliente)) {
				clienteDesejado = cliente;
				
				clienteDesejado.setId(clienteApi.getId());
				clienteDesejado.setNome(clienteApi.getNome());
				clienteDesejado.setCidade(clienteApi.getCidade());
				clienteDesejado.setEstado(clienteApi.getEstado());
			}
		}
	}
	
	public void deletaCliente(int idCliente) {		
		for(Cliente cliente : lista) {
			if(cliente.getId().equals(idCliente)) {
				idCliente = lista.indexOf(cliente);
			}
		}
		
		lista.remove(idCliente);
	}

}
