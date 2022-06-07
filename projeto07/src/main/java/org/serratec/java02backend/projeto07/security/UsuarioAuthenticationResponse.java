package org.serratec.java02backend.projeto07.security;

public class UsuarioAuthenticationResponse {
	
	private final String token;	

	public UsuarioAuthenticationResponse(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}	

}