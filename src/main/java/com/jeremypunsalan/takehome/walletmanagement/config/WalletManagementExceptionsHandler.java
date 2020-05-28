package com.jeremypunsalan.takehome.walletmanagement.config;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.jeremypunsalan.takehome.walletmanagement.exception.ResourceNotFoundException;
import com.jeremypunsalan.takehome.walletmanagement.exception.TransactionException;
import com.jeremypunsalan.takehome.walletmanagement.exception.ValidationException;

import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class WalletManagementExceptionsHandler {
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<?> validationException(ValidationException ex, WebRequest request) {
		ExceptionDetails detail = new ExceptionDetails(new Date(), HttpStatus.BAD_REQUEST.value(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(detail, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
		ExceptionDetails detail = new ExceptionDetails(new Date(), HttpStatus.NOT_FOUND.value(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(detail, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<?> transactionException(TransactionException ex, WebRequest request) {
		ExceptionDetails detail = new ExceptionDetails(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(detail, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exception(Exception ex, WebRequest request) {
		ExceptionDetails detail = new ExceptionDetails(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), request.getDescription(true));
		return new ResponseEntity<>(detail, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
