package com.example.springtodoapp.exceptionhandlers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.mediatype.vnderrors.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.springtodoapp.exceptions.ConflictException;
import com.example.springtodoapp.exceptions.InvalidRequestBodyException;
import com.example.springtodoapp.exceptions.TodoNotFoundException;
import com.example.springtodoapp.exceptions.UserNotFoundException;
import com.example.springtodoapp.models.ErrorBody;


@ControllerAdvice
public class RestControllerAdvice {
	
	private static final Logger log = LoggerFactory.getLogger(RestControllerAdvice.class);
	
	@ExceptionHandler(value = TodoNotFoundException.class)
	@RequestMapping(produces = "application/vnd.error+json")
	protected ResponseEntity<VndErrors> todoNotFoundExceptionHandler(TodoNotFoundException ex){
		log.error(ex.getMessage());
		return error(ex,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = UserNotFoundException.class)
	@RequestMapping(produces = "application/vnd.error+json")
	protected ResponseEntity<VndErrors> userNotFoundExceptionHandler(UserNotFoundException ex){
		log.error(ex.getMessage());
		return error(ex,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = ConflictException.class)
	@RequestMapping(produces = "application/vnd.error+json")
	protected ResponseEntity<VndErrors> conflictExceptionHandler(ConflictException ex){
		log.error(ex.getMessage());
		return error(ex, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = Exception.class)
	@RequestMapping(produces = "application/vnd.error+json")
	protected ResponseEntity<VndErrors> globalErrorExceptionHandler(Exception ex){
		log.error("Internal server error caught: {}",ex.getMessage());
		return error(ex,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(value = {InvalidRequestBodyException.class, HttpMessageNotReadableException.class,IllegalArgumentException.class})
	protected ResponseEntity<ErrorBody> invalidRequestBodyExceptionHandler(Exception ex){
		String msg = "Request body invalid";
		ErrorBody error = new ErrorBody(
				HttpStatus.BAD_REQUEST.value(),
			    ex instanceof HttpMessageNotReadableException ? msg : ex.getMessage(),
			    ex.getClass().getSimpleName()
			  );
		return new ResponseEntity<ErrorBody>(error,HttpStatus.BAD_REQUEST);	
	}
	

	private ResponseEntity<VndErrors> error(final Exception exception, 
													final HttpStatus httpStatus) {
			final String logRef = String.valueOf(httpStatus.value());
			final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
			return new ResponseEntity<>(new VndErrors(logRef, message), httpStatus);
	}

}
