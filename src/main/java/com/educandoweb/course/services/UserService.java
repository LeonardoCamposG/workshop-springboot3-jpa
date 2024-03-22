package com.educandoweb.course.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

//@Component Annotation que registra a classe como um component para o spring(permitindo o @Autowired do service).
// Podem ser usado também o @Service e @Repository para registrar os mesmos para o spring de forma especificada.

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> findAll(){
		return repository.findAll();
	}
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get(); //Retorna o objeto que esta dentro do Optional.
	}
	
	public User insert(User obj) {
		return repository.save(obj); // O save por padrão, retorna o obj salvo, por isso o return.
	}
	
	public void delete(Long id) {
		repository.deleteById(id);	// Metodo para deletar usuario por id.
	}
	
	public User update(Long id, User obj) {
		User entity = findById(id); // repository.getReferenceById(id);	// Esse metodo ele prepara um objeto monitorado pelo jpa, para depois fazer interação com o DB.
		updateData(entity, obj);
		return repository.save(entity);	
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}
