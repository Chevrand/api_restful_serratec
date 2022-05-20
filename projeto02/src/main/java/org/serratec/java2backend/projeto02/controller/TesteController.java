package org.serratec.java2backend.projeto02.controller;

import java.util.List;

import org.serratec.java2backend.projeto02.model.Teste;
import org.serratec.java2backend.projeto02.service.TesteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {
	
	@Autowired
	TesteService testeService;
	
	@RequestMapping("/teste")
	public List<Teste> getTeste() {
		return testeService.listaTeste();
	}
}
