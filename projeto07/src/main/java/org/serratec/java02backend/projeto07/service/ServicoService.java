package org.serratec.java02backend.projeto07.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import org.serratec.java02backend.projeto07.dto.RelatorioDto;
import org.serratec.java02backend.projeto07.dto.ServicoDto;
import org.serratec.java02backend.projeto07.dto.ServicoRelatorioDto;
import org.serratec.java02backend.projeto07.exception.EmailException;
import org.serratec.java02backend.projeto07.exception.ServicoException;
import org.serratec.java02backend.projeto07.model.Carro;
import org.serratec.java02backend.projeto07.model.Servico;
import org.serratec.java02backend.projeto07.repository.CarroRepository;
import org.serratec.java02backend.projeto07.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoService {
	
	@Autowired
	ServicoRepository servicoRepository;
	
	@Autowired
	CarroRepository carroRepository;
	
	@Autowired
	EmailService emailService;
	
	public ServicoDto toDto(Servico model) {
		ServicoDto dto = new ServicoDto();
		
		dto.setIdServico(model.getIdServico());
		dto.setDescricao(model.getDescricao());
		dto.setValor(model.getValor());
		dto.setData(model.getData());
		dto.setIdCarro(model.getCarro().getIdCarro());
		
		return dto;
	}
	
	public ServicoRelatorioDto toRelatorioDto(Servico model) {
		ServicoRelatorioDto dto = new ServicoRelatorioDto();
		
		dto.setIdServico(model.getIdServico());
		dto.setNomeCliente(model
				.getCarro()
				.getCliente()
				.getNome());
		dto.setModeloCarro(model
				.getCarro()
				.getModelo());
		dto.setDescricao(model.getDescricao());
		dto.setValor(model.getValor());
		
		return dto;
	}
	
	public Servico toModel(ServicoDto dto) throws ServicoException {
		Servico model = new Servico();
		Optional<Carro> carro = carroRepository.findById(dto.getIdCarro());
		
		if(carro.isEmpty()) {
			throw new ServicoException(
					"Não foi possível cadastrar o serviço pois o carro informado não existe");
		}
		
		model.setDescricao(dto.getDescricao());
		model.setValor(dto.getValor());
		model.setData(dto.getData());
		model.setCarro(carro.get());
		
		return model;
	}
	
	public String save(ServicoDto dto) throws ServicoException, MessagingException, EmailException {
		servicoRepository.save(toModel(dto));
		emailService.enviarEmail(dto);
		
		return "O servico foi cadastrado com sucesso!";
	}
	
	public List<ServicoDto> list() {
		List<ServicoDto> listaDto = new ArrayList<>();
		List<Servico> listaModel = servicoRepository.findAll();
		
		for(Servico model : listaModel) {
			ServicoDto dto = toDto(model);
			listaDto.add(dto);
		}
		
		return listaDto;
	}
	
	public List<ServicoRelatorioDto> listFiveLast() {
		List<ServicoRelatorioDto> listaDto = new ArrayList<>();
		List<Servico> listaModel = servicoRepository.findTop5ByOrderByIdServicoDesc();
		
		for(Servico model : listaModel) {
			ServicoRelatorioDto dto = toRelatorioDto(model);
			listaDto.add(dto);
		}
		
		return listaDto;
	}
	
	public ServicoDto findById(Integer idServico) throws ServicoException {
		ServicoDto dto = new ServicoDto();
		Optional<Servico> model = servicoRepository.findById(idServico);
		
		if(model.isPresent()) {
			dto = toDto(model.get());
			return dto;
		}
		
		throw new ServicoException("Não foi possível encontrar o servico informado!");
	}
	
	public String update(Integer idServico, ServicoDto dto) throws ServicoException {
		Optional<Servico> model = servicoRepository.findById(idServico);
		Servico servicoSalvar = new Servico();
		
		if(model.isPresent()) {
			servicoSalvar = model.get();
			
			if(dto.getDescricao() != null) {
				servicoSalvar.setDescricao(dto.getDescricao());
			}
			if(dto.getValor() != null) {
				servicoSalvar.setValor(dto.getValor());
			}
			if(dto.getData() != null) {
				servicoSalvar.setData(dto.getData());
			}
			if(dto.getIdCarro() != null) {
				servicoSalvar.setCarro(toModel(dto).getCarro());
			}
			
			servicoRepository.save(servicoSalvar);
			return String.format("O servico ID %d foi atualizado com sucesso!", idServico);
		}
		
		throw new ServicoException("Não foi possível encontrar o serviço desejado!");
	}
	
	public String delete(Integer idServico) throws ServicoException {
		Optional<Servico> model = servicoRepository.findById(idServico);
		
		if(model.isPresent()) {
			servicoRepository.deleteById(idServico);
			return String.format("O serviço ID %d foi deletado com sucesso!", idServico);
		}
		
		throw new ServicoException("Não foi possível encontrar o servico desejado!");
	}
	
	public List<RelatorioDto> relatorio() {
		return servicoRepository.relatorio();
	}

}