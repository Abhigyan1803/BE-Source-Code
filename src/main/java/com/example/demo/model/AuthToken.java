package com.example.demo.model;

import java.io.Serializable;

public class AuthToken  implements  Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 3053643731558975049L;
    private String token;

	

	public AuthToken(String token) {
		super();
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
    
}