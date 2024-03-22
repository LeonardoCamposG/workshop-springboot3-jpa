package com.educandoweb.course.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public ResourceNotFoundException(Object id) {	// Recebe o id que foi entrado como parametro, e retorna uma exceção de não encontrado a partir dele.
		super("Resource not found. Id " + id);
	}
}
