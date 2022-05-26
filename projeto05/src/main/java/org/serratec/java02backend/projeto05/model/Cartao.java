package org.serratec.java02backend.projeto05.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cartao")
public class Cartao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cartao_id")
	private Integer idCartao;
	
	@Column(name = "cartao_num_limite")
	private Double limite;
	
	@Column(name = "cartao_tx_numero_cartao")
	private String numeroCartao;
	
	@Column(name = "cartao_tx_nome_titular")
	private String nomeTitular;
	
	@Column(name = "cartao_dt_validade")
	private LocalDateTime dataValidade;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", referencedColumnName = "cliente_cd_id")
	private Cliente Cliente;
	
//	@OneToOne
//	@JoinColumn(name = "cliente_id")
//	private CLiente cliente;
	
//	@ManyToMany
//	@JoinTable(name = "cliente_rel_aviso", joinColumns= {@JoinColumn(name="cartao_id")},
//	inverseJoinColumns = {@JoinColumn(name="aviso_id")})
//	private List<Aviso> listaAviso;

	public Cliente getCliente() {
		return Cliente;
	}

	public void setCliente(Cliente Cliente) {
		this.Cliente = Cliente;
	}

	public Cartao() {}

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

	public Integer getIdCartao() {
		return idCartao;
	}	

}