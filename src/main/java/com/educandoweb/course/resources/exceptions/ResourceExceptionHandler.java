package com.educandoweb.course.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.educandoweb.course.services.exceptions.DatabaseException;
import com.educandoweb.course.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice	// Annotation que intercepta as exceções que acontecerem, para o obj exercer o tratamento.
public class ResourceExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)	//Annotation usada para interceptar a excessao.
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
		// O resourcenotfoundexception é excessao personalizada, e o obj request é a requisicao recebida como parametro.
		String error = "Resource not found";	// Erro a ser exibido na tela.
		HttpStatus status = HttpStatus.NOT_FOUND;	// Status da requisição HTTP.
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);	// Retorna uma requisição personalizada.
	}
	
	@ExceptionHandler(DatabaseException.class)	//Annotation usada para interceptar a excessao.
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
		// O resourcenotfoundexception é excessao personalizada, e o obj request é a requisicao recebida como parametro.
		String error = "Database error";	// Erro a ser exibido na tela.
		HttpStatus status = HttpStatus.BAD_REQUEST;	// Status da requisição HTTP.
		StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		return ResponseEntity.status(status).body(err);	// Retorna uma requisição personalizada.
	}
}
