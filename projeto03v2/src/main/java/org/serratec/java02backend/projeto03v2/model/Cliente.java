package org.serratec.java02backend.projeto03v2.model;

public class Cliente {
	
	private Integer id;
	private String nome;
	private String cidade;
	private String estado;
	
	public Cliente() {}

	public Cliente(Integer id, String nome, String cidade, String estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.cidade = cidade;
		this.estado = estado;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}