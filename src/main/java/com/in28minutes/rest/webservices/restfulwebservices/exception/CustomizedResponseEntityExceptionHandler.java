package com.in28minutes.rest.webservices.restfulwebservices.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in28minutes.rest.webservices.restfulwebservices.user.UserNotFoundException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllException(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = 
			new ErrorDetails(LocalDateTime.now(), 
				ex.getMessage(),
				request.getDescription(false));
		
			return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) {
		ErrorDetails errorDetails = 
			new ErrorDetails(LocalDateTime.now(), 
				ex.getMessage(),
				request.getDescription(false));		
		
			return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
		
	// type-1 : search for 1 error
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 
			"Total Errors:" + ex.getErrorCount() + " First Error:" + ex.getFieldError().getDefaultMessage(),
				request.getDescription(false));
						
		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
	}	
	
	// type-2 :  search for multipul error
//	@Override
//	protected ResponseEntity<Object> handleMethodArgumentNotValid(
//			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
//	    List<String> errorMessages = new ArrayList<>();
//	    
//	    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
//	        errorMessages.add(fieldError.getDefaultMessage());
//	    }
//	    
//	    ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), 
//	            "Total Errors: " + errorMessages.size() + ", Errors: " + errorMessages,
//	            request.getDescription(false));
//	            
//		
//		return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);
//	}	
	
}
