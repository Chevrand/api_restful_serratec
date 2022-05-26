package org.serratec.java02backend.projeto05.service;

import java.util.List;
import java.util.Optional;

import org.serratec.java02backend.projeto05.dto.ClienteDto;
import org.serratec.java02backend.projeto05.exception.ClienteException;
import org.serratec.java02backend.projeto05.model.Cliente;
import org.serratec.java02backend.projeto05.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public ClienteDto transformarParaDto(ClienteDto clienteDto, Cliente cliente) {
		clienteDto.setIdCliente(cliente.getIdCliente());
		clienteDto.setNome(cliente.getNome());
		clienteDto.setCpf(cliente.getCpf());
		clienteDto.setNumeroTelefone(cliente.getNumeroTelefone());
		clienteDto.setEmail(cliente.getEmail());
		clienteDto.setDataNascimento(cliente.getDataNascimento());
		
		return clienteDto;
	}
	
	public Cliente transformarParaModel(Cliente cliente, ClienteDto clienteDto) {
		cliente.setNome(clienteDto.getNome());
		cliente.setCpf(clienteDto.getCpf().replace(".", "").replace("-", ""));
		cliente.setNumeroTelefone(clienteDto.getNumeroTelefone());
		cliente.setEmail(clienteDto.getEmail());
		cliente.setDataNascimento(clienteDto.getDataNascimento());
		
		return cliente;
	}
	
	public void salvar(ClienteDto clienteDto) {
		Cliente clienteSalvar = new Cliente();
		transformarParaModel(clienteSalvar, clienteDto);
		clienteRepository.save(clienteSalvar);
	}
	
	public ClienteDto buscaPorId(Integer idCliente) throws ClienteException {
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		
		ClienteDto clienteDto = new ClienteDto();
		
		if(cliente.isPresent()) {
			clienteDto = transformarParaDto(clienteDto, cliente.get());
			return clienteDto;
		}
		
		throw new ClienteException("Cliente não encontrado!");
	}
	
	public void atualizar(Integer idCliente, ClienteDto clienteDto) {
		Optional<Cliente> cliente = clienteRepository.findById(idCliente);
		Cliente clienteNoBanco = new Cliente();
		
		if(cliente.isPresent()) {
			clienteNoBanco = cliente.get();
			
			if (clienteDto.getNome() != null) {
				clienteNoBanco.setNome(clienteDto.getNome());
			}
			if (clienteDto.getCpf() != null) {
				clienteNoBanco.setCpf(clienteDto.getCpf());
			}
			if (clienteDto.getNumeroTelefone() != null) {
				clienteNoBanco.setNumeroTelefone(clienteDto.getNumeroTelefone());
			}
			if (clienteDto.getEmail() != null) {
				clienteNoBanco.setEmail(clienteDto.getEmail());
			}
			if (clienteDto.getDataNascimento() != null) {
				clienteNoBanco.setDataNascimento(clienteDto.getDataNascimento());
			}
			clienteRepository.save(clienteNoBanco);
		}
	}
	
	public void delete(Integer idCliente) {
		clienteRepository.deleteById(idCliente);
	}
	
	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
	}

}
