package com.guardians.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.guardians.dto.EmployeeResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<EmployeeResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex){
		EmployeeResponse response = new EmployeeResponse();
		response.setMessage(ex.getMessage());
		response.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND); 
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<EmployeeResponse> handleGeneralException(Exception ex){
		EmployeeResponse response = new EmployeeResponse();
		response.setMessage("An Error Occured: "+ex.getMessage());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR) ; 
	}
	
	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<EmployeeResponse> handleNoResourceFoundException(NoResourceFoundException ex){
		EmployeeResponse response = new EmployeeResponse();
		response.setMessage("Page not found");
		response.setStatus(HttpStatus.NOT_FOUND.value());
		
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND) ; 
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<EmployeeResponse> handleNoResourceFoundException(HttpRequestMethodNotSupportedException ex){
		EmployeeResponse response = new EmployeeResponse();
		response.setMessage("Page not found :: check URL "+ex.getMessage());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR) ; 
	}
	
}
