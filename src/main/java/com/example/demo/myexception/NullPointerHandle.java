package com.example.demo.myexception;

public class NullPointerHandle extends Exception {
	private static final long serialVersionUID = -470180507998010368L;

	public NullPointerHandle() {
		super();
	}

	public NullPointerHandle(final String message) {
		super(message);
	}

	public NullPointerHandle(final String message, Throwable thr) {
		super(message, thr);
	}

}
