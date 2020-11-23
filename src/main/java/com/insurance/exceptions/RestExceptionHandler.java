package com.insurance.exceptions;

import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.insurance.dto.ErrorResponse;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handelException(UserNotFoundException userException) {

		ErrorResponse response = new ErrorResponse();
		response.setStatusCode("insurance-001");
		response.setStatusMessage(userException.getMessage());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(PolicyNotFoundException.class)
	public ResponseEntity<ErrorResponse> handelException(PolicyNotFoundException userException) {

		ErrorResponse response = new ErrorResponse();
		response.setStatusCode("insurance-002");
		response.setStatusMessage(userException.getMessage());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoPolicyiesAvailableException.class)
	public ResponseEntity<ErrorResponse> handelException(NoPolicyiesAvailableException userException) {

		ErrorResponse response = new ErrorResponse();
		response.setStatusCode("insurance-003");
		response.setStatusMessage(userException.getMessage());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(TransactionException.class)
	public ResponseEntity<ErrorResponse> handelTranException(TransactionException userException) {

		ErrorResponse response = new ErrorResponse();
		response.setStatusCode("DEMO-002");
		response.setStatusMessage(userException.getMessage());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value=Exception.class)
	public ResponseEntity<ErrorResponse> handleGenericException(Exception userException) {
		ErrorResponse response = new ErrorResponse();
		response.setStatusCode("DEMO-001");
		response.setStatusMessage(userException.getMessage());
		return new ResponseEntity<ErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException  argInvalidException, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		ErrorResponse response = new ErrorResponse();
		response.setStatusCode("DEMO-005");
		String allFieldErrors = argInvalidException.getBindingResult().getFieldErrors().stream()
				.map(e -> e.getDefaultMessage()).collect(Collectors.joining(", "));
		response.setStatusMessage(allFieldErrors);
		return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
	}
}
