package com.example.demo.util;

import org.springframework.http.HttpStatus;

import com.example.demo.model.AuthToken;

public class ResponseMessage {
    
    
    
    public ResponseMessage(String message, HttpStatus status, Object object) {
//	
	this.message = message;
	this.status = status;
	this.object = object;
    }
    public ResponseMessage() {
	super();
    }
    public ResponseMessage(String message, HttpStatus status) {
    	super();
    	this.message = message;
    	this.status = status;
    	
        }
    public ResponseMessage(String message , HttpStatus status, Object userdata, AuthToken authToken) {
	// TODO Auto-generated constructor stub
	super();
	this.message = message;
	this.status = status;
	this.object = userdata;
	this.authToken=authToken;
    }
    
	private String message;
    private HttpStatus status;
    private Object object;
    
    private AuthToken authToken;
    
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public HttpStatus getStatus() {
        return status;
    }
    public void setStatus(HttpStatus status) {
        this.status = status;
    }
    public Object getObject() {
        return object;
    }
    public void setObject(Object object) {
        this.object = object;
    }
    public AuthToken getAuthToken() {
        return authToken;
    }
    public void setAuthToken(AuthToken authToken) {
        this.authToken = authToken;
    }
    
    
    
}
