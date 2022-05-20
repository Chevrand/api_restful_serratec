package org.serratec.java2backend.projeto02.exception;

public class TodoException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	private Integer Id;

	public TodoException(Integer id) {
		Id = id;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

}
