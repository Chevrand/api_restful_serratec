package org.serratec.java02backend.projeto07.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.serratec.java02backend.projeto07.dto.ClienteDto;
import org.serratec.java02backend.projeto07.exception.ClienteException;
import org.serratec.java02backend.projeto07.model.Cliente;
import org.serratec.java02backend.projeto07.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public ClienteDto toDto(Cliente model) {
		ClienteDto dto = new ClienteDto();
		
		dto.setIdCliente(model.getIdCliente());
		dto.setNome(model.getNome());
		dto.setCpf(model.getCpf());
		dto.setNumeroTelefone(model.getNumeroTelefone());
		dto.setEmail(model.getEmail());
		
		return dto;
	}
	
	public Cliente toModel(ClienteDto dto) {
		Cliente model = new Cliente();
		
		model.setNome(dto.getNome());
		model.setCpf(dto.getCpf());
		model.setNumeroTelefone(dto.getNumeroTelefone());
		model.setEmail(dto.getEmail());
		
		return model;
	}
	
	public String save(ClienteDto dto) {
		clienteRepository.save(toModel(dto));
		
		return "O cliente foi cadastrado com sucesso!";
	}
	
	public List<ClienteDto> list() {
		List<ClienteDto> listaDto = new ArrayList<>();
		List<Cliente> listaModel = clienteRepository.findAll();
		
		for(Cliente model : listaModel) {
			ClienteDto dto = toDto(model);
			listaDto.add(dto);
		}
		
		return listaDto;
	}
	
	public ClienteDto findById(Integer idCliente) throws ClienteException {
		ClienteDto dto = new ClienteDto();
		Optional<Cliente> model = clienteRepository.findById(idCliente);
		
		if(model.isPresent()) {
			dto = toDto(model.get());
			return dto;
		}
		
		throw new ClienteException("Não foi possível encontrar o cliente informado!");
	}
	
	public String update(Integer idCliente, ClienteDto dto) throws ClienteException {
		Optional<Cliente> model = clienteRepository.findById(idCliente);
		Cliente clienteSalvar = new Cliente();
		
		if(model.isPresent()) {
			clienteSalvar = model.get();
			
			if(dto.getNome() != null) {
				clienteSalvar.setNome(dto.getNome());
			}
			if(dto.getCpf() != null) {
				clienteSalvar.setCpf(dto.getCpf());
			}
			if(dto.getNumeroTelefone() != null) {
				clienteSalvar.setNumeroTelefone(dto.getNumeroTelefone());
			}
			if(dto.getEmail() != null) {
				clienteSalvar.setEmail(dto.getEmail());
			}
			
			clienteRepository.save(clienteSalvar);
			return String.format("O cliente ID %d foi atualizado com sucesso!", idCliente);
		}
		
		throw new ClienteException("Não foi possível encontrar o cliente desejado!");
	}
	
	public String delete(Integer idCliente) throws ClienteException {
		Optional<Cliente> model = clienteRepository.findById(idCliente);
		
		if(model.isPresent()) {
			clienteRepository.deleteById(idCliente);
			return String.format("O cliente ID %d foi deletado com sucesso!", idCliente);
		}
		
		throw new ClienteException("Não foi possível encontrar o cliente desejado!");
	}

}