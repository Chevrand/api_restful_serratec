package org.serratec.java02backend.projeto07.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="servico")
public class Servico {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="servico_cd_id")
	private Integer idServico;
	
	@Column(name="servico_tx_descricao")
	private String descricao;
	
	@Column(name="servico_nu_valor")
	private Double valor;
	
	@Column(name="servico_data")
	private LocalDate data;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="servico_carro", referencedColumnName="carro_cd_id")
	private Carro carro;

	public Servico() {}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Integer getIdServico() {
		return idServico;
	}

}