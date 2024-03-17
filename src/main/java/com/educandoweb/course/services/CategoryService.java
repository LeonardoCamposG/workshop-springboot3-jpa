package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.repositories.CategoryRepository;

//@Component Annotation que registra a classe como um component para o spring(permitindo o @Autowired do service).
// Podem ser usado também o @Service e @Repository para registrar os mesmos para o spring de forma especificada.

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get(); //Retorna o objeto que esta dentro do Optional.
	}
}
