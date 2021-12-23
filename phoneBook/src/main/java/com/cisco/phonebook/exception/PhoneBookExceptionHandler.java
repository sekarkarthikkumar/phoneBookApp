package com.cisco.phonebook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cisco.phonebook.model.ErrorResponse;


@ControllerAdvice
public class PhoneBookExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public final ResponseEntity<ErrorResponse> handleCustomValidationException(
			MethodArgumentNotValidException exception) {
		ErrorResponse errorResponse = new ErrorResponse("Validation error",
				exception.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(UserDetailNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleCustomValidationException(UserDetailNotFoundException exception) {
		ErrorResponse errorResponse = new ErrorResponse("Data not found error", exception.getLocalizedMessage());
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
		ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
