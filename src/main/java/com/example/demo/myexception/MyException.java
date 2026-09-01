package com.example.demo.myexception;


public class MyException  extends  Exception {
	private static final long serialVersionUID = -470180507998010368L;

	public MyException() {
		super();
	}
	public MyException(final String message) {
		super(message);
	}
	public MyException(final String message,Throwable thr) {
		super(message,thr);
	}

	
}
