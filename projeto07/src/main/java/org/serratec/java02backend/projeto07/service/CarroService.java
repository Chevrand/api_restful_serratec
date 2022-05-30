package org.serratec.java02backend.projeto07.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.serratec.java02backend.projeto07.dto.CarroDto;
import org.serratec.java02backend.projeto07.exception.CarroException;
import org.serratec.java02backend.projeto07.model.Carro;
import org.serratec.java02backend.projeto07.model.Cliente;
import org.serratec.java02backend.projeto07.repository.CarroRepository;
import org.serratec.java02backend.projeto07.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {
	
	@Autowired
	CarroRepository carroRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public CarroDto toDto(Carro model) {
		CarroDto dto = new CarroDto();
		
		dto.setIdCarro(model.getIdCarro());
		dto.setIdCliente(model.getCliente().getIdCliente());
		dto.setModelo(model.getModelo());
		dto.setMarca(model.getMarca());
		dto.setAno(model.getAno());
		
		return dto;
	}
	
	public Carro toModel(CarroDto dto) throws CarroException{
		Carro model = new Carro();
		Optional<Cliente> cliente = clienteRepository.findById(dto.getIdCliente());
		
		if(cliente.isEmpty()) {
			throw new CarroException(
					"Não foi possível cadastrar o carro pois o cliente informado não existe");
		}
		model.setCliente(cliente.get());
		model.setModelo(dto.getModelo());
		model.setMarca(dto.getMarca());
		model.setAno(dto.getAno());
		
		return model;
	}
	
	public String save(CarroDto dto) throws CarroException {
		carroRepository.save(toModel(dto));
		
		return "O carro foi cadastrado com sucesso!";
	}
	
	public List<CarroDto> list() {
		List<CarroDto> listaDto = new ArrayList<>();
		List<Carro> listaModel = carroRepository.findAll();
		
		for(Carro model : listaModel) {
			CarroDto dto = toDto(model);
			listaDto.add(dto);
		}
		
		return listaDto;
	}
	
	public CarroDto findById(Integer idCarro) throws CarroException {
		CarroDto dto = new CarroDto();
		Optional<Carro> model = carroRepository.findById(idCarro);
		
		if(model.isPresent()) {
			dto = toDto(model.get());
			return dto;
		}
		
		throw new CarroException("Não foi possível encontrar o carro informado!");
	}
	
	public String update(Integer idCarro, CarroDto dto) throws CarroException {
		Optional<Carro> model = carroRepository.findById(idCarro);
		Carro carroSalvar = new Carro();
		
		if(model.isPresent()) {
			carroSalvar = model.get();
			
			if(dto.getIdCliente() != null) {
				carroSalvar.setCliente(toModel(dto).getCliente());
			}
			if(dto.getModelo() != null) {
				carroSalvar.setModelo(dto.getModelo());
			}
			if(dto.getMarca() != null) {
				carroSalvar.setMarca(dto.getMarca());
			}
			if(dto.getAno() != null) {
				carroSalvar.setAno(dto.getAno());
			}
			
			carroRepository.save(carroSalvar);
			return String.format("O carro ID %d foi atualizado com sucesso!", idCarro);
		}
		
		throw new CarroException("Não foi possível encontrar o carro desejado!");
	}
	
	public String delete(Integer idCarro) throws CarroException {
		Optional<Carro> model = carroRepository.findById(idCarro);
		
		if(model.isPresent()) {
			carroRepository.deleteById(idCarro);
			return String.format("O carro ID %d foi deletado com sucesso!", idCarro);
		}
		
		throw new CarroException("Não foi possível encontrar o carro desejado!");
	}

}