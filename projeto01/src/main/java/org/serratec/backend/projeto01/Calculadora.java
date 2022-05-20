package org.serratec.backend.projeto01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Calculadora {
	
	public Double valor;
	
	@GetMapping("/calculadora")
	public Double Calcular(@RequestParam Double x, @RequestParam Double y, @RequestParam String operacao) {
		
		switch(operacao) {
			case "soma":
				valor = x + y;
				break;
			case "subtracao":
				valor = x - y;
				break;
			case "multiplicacao":
				valor = x * y;
				break;
			case "divisao":
				valor = x / y;
		}
		return valor;
	}
}