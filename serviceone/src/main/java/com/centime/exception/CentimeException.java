package com.centime.exception;

public class CentimeException extends Exception {

	private static final long serialVersionUID = -401001387786900523L;
	
	private Throwable excep;
	private String errorMessage;
	
	public Throwable getExcep() {
		return excep;
	}

	public void setExcep(Throwable excep) {
		this.excep = excep;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public CentimeException(Throwable excep, String errorMessage) {
		super();
		this.excep = excep;
		this.errorMessage = errorMessage;
	}
	
	public CentimeException(){
		super();
	}

}
