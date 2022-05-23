package org.serratec.java02backend.projeto04v2.service;

import java.util.List;
import java.util.Optional;

import org.serratec.java02backend.projeto04v2.exception.ContaException;
import org.serratec.java02backend.projeto04v2.model.Conta;
import org.serratec.java02backend.projeto04v2.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaService {
	
	@Autowired
	ContaRepository contaRepository;
	
	public List<Conta> listaConta() {
		return contaRepository.findAll();
	}
	
	public Conta buscaContaPorId(Integer idConta) {
		Conta contaDesejada = new Conta();
		Optional<Conta> conta = contaRepository.findById(idConta);
		
		if(conta.isPresent()) {
			contaDesejada = conta.get();
		}
		
		return contaDesejada;
	}
	
	public void salvar(Conta conta) {
		contaRepository.save(conta);
	}
	
	public void atualizaConta(Integer idConta, Conta contaApi) {
		Conta contaDesejada = buscaContaPorId(idConta);
		
		if(contaApi.getTitular() != null) {
			contaDesejada.setTitular(contaApi.getTitular());
		}
		
		contaRepository.save(contaDesejada);
	}
	
	public void transfereValorConta (Integer idConta, String operacao, Double valor) throws ContaException {
		Conta contaDesejada = buscaContaPorId(idConta);
		
		switch(operacao) {
		case "credito":
			contaDesejada.setSaldo(contaDesejada.getSaldo() + valor);
			break;
		case "debito":
			if(contaDesejada.getSaldo() < valor) {
				throw new ContaException(idConta);
			}else {
				contaDesejada.setSaldo(contaDesejada.getSaldo() - valor);
			}
		}
		
		contaRepository.save(contaDesejada);
	}
	
	public void deletaConta(Integer idConta) {
		contaRepository.deleteById(idConta);
	}

}