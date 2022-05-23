package org.serratec.java02backend.projeto04v2.exception;

public class ContaException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;

	public ContaException(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	
}