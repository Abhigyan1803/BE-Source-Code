package com.example.demo.payload;

import java.io.Serializable;

public class OqMatrixTermPayload implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private EdOqMatrixPayload term1;
	private EdOqMatrixPayload term2;
	private EdOqMatrixPayload term3;
	private EdOqMatrixPayload tech2;
	private EdOqMatrixPayload tech3;
	
	public EdOqMatrixPayload getTerm1() {
		return term1;
	}
	public void setTerm1(EdOqMatrixPayload term1) {
		this.term1 = term1;
	}
	public EdOqMatrixPayload getTerm2() {
		return term2;
	}
	public void setTerm2(EdOqMatrixPayload term2) {
		this.term2 = term2;
	}
	public EdOqMatrixPayload getTerm3() {
		return term3;
	}
	public void setTerm3(EdOqMatrixPayload term3) {
		this.term3 = term3;
	}
	public EdOqMatrixPayload getTech2() {
		return tech2;
	}
	public void setTech2(EdOqMatrixPayload tech2) {
		this.tech2 = tech2;
	}
	public EdOqMatrixPayload getTech3() {
		return tech3;
	}
	public void setTech3(EdOqMatrixPayload tech3) {
		this.tech3 = tech3;
	}
	
	
}
