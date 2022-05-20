package org.serratec.java2backend.projeto02.model;

public class Teste {
	
	private Integer id;
	private String titulo;
	private String descricao;
	
	public Teste() {}

	public Teste(Integer id, String titulo, String descricao) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}