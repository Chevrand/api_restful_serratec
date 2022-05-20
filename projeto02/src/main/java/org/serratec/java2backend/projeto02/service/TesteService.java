package org.serratec.java2backend.projeto02.service;

import java.util.Arrays;
import java.util.List;
import org.serratec.java2backend.projeto02.model.Teste;
import org.springframework.stereotype.Service;

@Service
public class TesteService {
	
	private List<Teste> testes = Arrays.asList(
			new Teste(1, "Model", "Testar Model"),
			new Teste(2, "Service", "Testar Service"),
			new Teste(3, "Controller", "Testar Controller"));
	
	public List<Teste> listaTeste(){
		return this.testes;
	}

}
