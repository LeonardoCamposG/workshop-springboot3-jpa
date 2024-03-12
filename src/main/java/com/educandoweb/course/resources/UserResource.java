package com.educandoweb.course.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.User;

@RestController    					// Recurso web controlado por um controlador REST.
@RequestMapping(value = "/users")   // Caminho para a entidade controlada pelo resource.
public class UserResource {

	@GetMapping // Indicando que esse método responde a requisição do tipo GET do http
	public ResponseEntity<User> findAll(){			// Método que retorna resposta de requisição web.
		User u = new User(1L, "Maria", "maria@gmail.com", "999999", "12345"); 
		// No java precisa passar a letra L para o tipo long.
		return ResponseEntity.ok().body(u); // 
		// .ok(retorna a resposta com sucesso da solicitação)
		// .body(retorna o corpo da resposta, no caso o entity)
	}
}
