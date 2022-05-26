package org.serratec.java02backend.projeto05.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.serratec.java02backend.projeto05.dto.CartaoDto;
import org.serratec.java02backend.projeto05.exception.CartaoException;
import org.serratec.java02backend.projeto05.model.Cartao;
import org.serratec.java02backend.projeto05.repository.CartaoRepository;
import org.serratec.java02backend.projeto05.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartaoService {
	
	@Autowired
	CartaoRepository cartaoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	public CartaoDto toDto(Cartao cartao, CartaoDto cartaoDto) {
		cartaoDto.setIdCartao(cartao.getIdCartao());
		cartaoDto.setLimite(cartao.getLimite());
		cartaoDto.setNumeroCartao(cartao.getNumeroCartao());
		cartaoDto.setNomeTitular(cartao.getNomeTitular());
		cartaoDto.setDataValidade(cartao.getDataValidade());
		
		if(cartao.getCliente() != null) {
			cartaoDto.setIdCliente(cartao.getCliente().getIdCliente());			
		}
		
		return cartaoDto;		
	}
	
	public Cartao toModel(CartaoDto cartaoDto, Cartao cartao) {
		cartao.setLimite(cartaoDto.getLimite());
		cartao.setNumeroCartao(cartaoDto.getNumeroCartao());
		cartao.setNomeTitular(cartaoDto.getNomeTitular());
		cartao.setDataValidade(cartaoDto.getDataValidade());
		
		if(cartaoDto.getIdCliente() != null) {
			cartao.setCliente(clienteRepository.findById(cartaoDto.getIdCliente()).get());
		}
		
		return cartao;		
	}
	
	public String salvar(CartaoDto cartaoDto) {
		Cartao cartao = new Cartao();
		
		toModel(cartaoDto, cartao);		
		cartaoRepository.save(cartao);
		
		return String.format("Novo cartão com ID %d criado com sucesso!", cartao.getIdCartao());
	}
	
	public String salvarVariosCartoes(List<CartaoDto> cartoesDto) {
		List<Cartao> cartoesNovos = new ArrayList<>();
		
		for(CartaoDto cartaoDto : cartoesDto) {
			Cartao cartao = new Cartao();
			toModel(cartaoDto, cartao);
			cartoesNovos.add(cartao);
		}
		
		cartaoRepository.saveAll(cartoesNovos);
		return "Os cartões foram criados com sucesso";
	}
	
	public void leitor() throws IOException {
		BufferedReader buffReader = new BufferedReader(new FileReader("C:\\Users\\wande\\Desktop\\cartao.txt"));
		String linha = buffReader.readLine();
		
		while(linha != null) {
			String [] dados = linha.split(";");
			Cartao cartao = new Cartao();
			
			cartao.setLimite(Double.parseDouble(dados[0]));
			cartao.setNumeroCartao(dados[1]);
			cartao.setNomeTitular(dados[2]);
			cartao.setDataValidade(LocalDateTime.parse(dados[3]));
			cartao.setCliente(clienteRepository.findById(Integer.parseInt(dados[4])).get());
			
			cartaoRepository.save(cartao);
			linha = buffReader.readLine();
		}
		buffReader.close();
	}
	
	public List<CartaoDto> listarCartoes() {
		List<Cartao> listaCartao = cartaoRepository.findAll();
		List<CartaoDto> listaCartaoDto = new ArrayList<>();
		
		for(Cartao cartao : listaCartao) {
			CartaoDto cartaoDto = new CartaoDto();
			toDto(cartao, cartaoDto);
			listaCartaoDto.add(cartaoDto);
		}
		
		return listaCartaoDto;
	}
	
	public CartaoDto buscarCartaoById(Integer idCartao) throws CartaoException {
		Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
		CartaoDto cartaoDto = new CartaoDto();
		
		if(cartao.isEmpty()) {
			throw new CartaoException("Não foi possível encontar o cartão informado");
		}
		
		cartaoDto = toDto(cartao.get(), cartaoDto);
		
		return cartaoDto;		
	}
	
	public String atualizarCartao(Integer idCartao, CartaoDto cartaoDto) throws CartaoException {
		Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
		Cartao cartaoSalvo = new Cartao();
		
		if(cartao.isPresent()) {
			cartaoSalvo = cartao.get();
			
			if(cartaoDto.getLimite() != null) {
				cartaoSalvo.setLimite(cartaoDto.getLimite());
			}
			if(cartaoDto.getNumeroCartao() != null) {
				cartaoSalvo.setNumeroCartao(cartaoDto.getNumeroCartao());
			}
			if(cartaoDto.getNomeTitular() != null) {
				cartaoSalvo.setNomeTitular(cartaoDto.getNomeTitular());
			}
			if(cartaoDto.getDataValidade() != null) {
				cartaoSalvo.setDataValidade(cartaoDto.getDataValidade());
			}
			
			cartaoRepository.save(cartaoSalvo);
			return String.format("As alterações no cartão ID %d foram salvas com sucesso!", cartaoSalvo.getIdCartao());
		}
		
		throw new CartaoException("Não foi possível encontar o cartão informado");
	}
	
	public String deletarCartao(Integer idCartao) throws CartaoException{
		Optional<Cartao> cartao = cartaoRepository.findById(idCartao);
		
		if(cartao.isEmpty()) {
			throw new CartaoException("O cartão informado não existe!");
		}
		
		cartaoRepository.deleteById(idCartao);		
		return String.format("O cartão de ID %d foi deletado com sucesso!", idCartao);
	}

}