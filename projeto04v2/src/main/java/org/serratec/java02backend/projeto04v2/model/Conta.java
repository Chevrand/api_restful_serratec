package org.serratec.java02backend.projeto04v2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Conta")
public class Conta {
	
	@Id
	@Column(name="conta_cd_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idConta;
	
	@Column(name="conta_tx_titular")
	private String titular;
	
	@Column(name="conta_nu_saldo")
	private Double saldo;

	public Conta() {}

	public Integer getIdConta() {
		return idConta;
	}

	public void setIdConta(Integer idConta) {
		this.idConta = idConta;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
}