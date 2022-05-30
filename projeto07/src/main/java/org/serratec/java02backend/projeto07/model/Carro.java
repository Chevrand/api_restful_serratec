package org.serratec.java02backend.projeto07.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="carro")
public class Carro {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="carro_cd_id")
	private Integer idCarro;
	
	@ManyToOne
	@JoinColumn(name="carro_cliente_proprietario", referencedColumnName="cliente_cd_id")
	private Cliente cliente;
	
	@Column(name="carro_tx_modelo")
	private String modelo;
	
	@Column(name="carro_tx_marca")
	private String marca;
	
	@Column(name="carro_tx_ano")
	private String ano;

	public Carro() {}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public Integer getIdCarro() {
		return idCarro;
	}
	
}