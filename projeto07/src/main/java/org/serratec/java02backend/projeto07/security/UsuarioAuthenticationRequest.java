package org.serratec.java02backend.projeto07.security;

import java.io.Serializable;

public class UsuarioAuthenticationRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	
	public UsuarioAuthenticationRequest() {}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}