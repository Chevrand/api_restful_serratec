package org.serratec.java02backend.projeto05.dto;

import java.time.LocalDateTime;

public class CartaoDto{
	
	private Integer idCartao;
	private Double limite;
	private String numeroCartao;
	private String nomeTitular;
	private LocalDateTime dataValidade;
	private Integer idCliente;

	public Integer getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}

	public CartaoDto( ) {}

	public Integer getIdCartao() {
		return idCartao;
	}

	public void setIdCartao(Integer idCartao) {
		this.idCartao = idCartao;
	}

	public Double getLimite() {
		return limite;
	}

	public void setLimite(Double limite) {
		this.limite = limite;
	}

	public String getNumeroCartao() {
		return numeroCartao;
	}

	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}

	public String getNomeTitular() {
		return nomeTitular;
	}

	public void setNomeTitular(String nomeTitular) {
		this.nomeTitular = nomeTitular;
	}

	public LocalDateTime getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDateTime dataValidade) {
		this.dataValidade = dataValidade;
	}	
	
}