package com.jeremypunsalan.takehome.walletmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class TransactionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TransactionException(String message) {
		super(message);
	}

	
	
}
