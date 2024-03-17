package com.educandoweb.course.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.educandoweb.course.entities.Category;
import com.educandoweb.course.services.CategoryService;

@RestController    						 // Recurso web controlado por um controlador REST.
@RequestMapping(value = "/categories")   // Caminho para a entidade controlada pelo resource.
public class CategoryResource {

	@Autowired
	private CategoryService service;
	
	@GetMapping // Indicando que esse método responde a requisição do tipo GET do http
	public ResponseEntity<List<Category>> findAll(){			// Método que retorna resposta de requisição web.
		List<Category> list = service.findAll();
		
		// Category u = new Category(1L, "Maria", "maria@gmail.com", "999999", "12345"); 
		// No java precisa passar a letra L para o tipo long.
		return ResponseEntity.ok().body(list); // 
		// .ok(retorna a resposta com sucesso da solicitação)
		// .body(retorna o corpo da resposta, no caso o entity)
	}
	
	@GetMapping(value = "/{id}") // Isso indica que a requisição GET vai aceitar um ID dentro da URL.
	public ResponseEntity<Category> findById(@PathVariable Long id){
		// Essa annotation @PathVariable, e para o spring conseguir passar o valor recebido na url para o parametro.
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
