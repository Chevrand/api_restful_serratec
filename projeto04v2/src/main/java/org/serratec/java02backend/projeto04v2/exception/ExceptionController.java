package org.serratec.java02backend.projeto04v2.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(ContaException.class)
	public ResponseEntity<String> tratarSaldoContaInsufciente(ContaException exception) {
		String msg = String.format("Não há saldo suficiente na conta ID %d para completar a operação!", exception.getId());
		return ResponseEntity.notFound()
				.header("Detalhes", msg)
				.header("Tipo", "SALDO INSUFICIENTE")
				.header("Id da Conta", exception.getId().toString())
				.build();
	}

}