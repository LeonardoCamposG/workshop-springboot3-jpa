package com.educandoweb.course.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.services.UserService;

@RestController    					// Recurso web controlado por um controlador REST.
@RequestMapping(value = "/users")   // Caminho para a entidade controlada pelo resource.
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping // Indicando que esse método responde a requisição do tipo GET do http
	public ResponseEntity<List<User>> findAll(){			// Método que retorna resposta de requisição web.
		List<User> list = service.findAll();
		
		// User u = new User(1L, "Maria", "maria@gmail.com", "999999", "12345"); 
		// No java precisa passar a letra L para o tipo long.
		return ResponseEntity.ok().body(list); // 
		// .ok(retorna a resposta com sucesso da solicitação)
		// .body(retorna o corpo da resposta, no caso o entity)
	}
	
	@GetMapping(value = "/{id}") // Isso indica que a requisição GET vai aceitar um ID dentro da URL.
	public ResponseEntity<User> findById(@PathVariable Long id){
		// Essa annotation @PathVariable, e para o spring conseguir passar o valor recebido na url para o parametro.
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping // Annotation do spring usada para salvar dados no DB.
	public ResponseEntity<User> insert(@RequestBody User obj){	// Ela faz um pré processamento no compilador, definindo que esse método vai receber o metodo POST do HTTP.
		// Annotation RequestBody é para garantir que o json da requisição seja deseserializado para um obj do tipo User.
		// Esse método retorna o obj inserido.
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.
				fromCurrentRequest()
				.path("/{id}").	// Tem que passar o caminho da url resultante do obj, nesse caso é só o id.
				buildAndExpand(obj.getId()) // Ai preciso passar o id do obj que esta sendo inserido
				.toUri();	// Convertendo para uri.
		return ResponseEntity.created(uri).body(obj);	// Foi alterado para o request retornar 201(novo obj inserido).
	}
	
	@DeleteMapping(value = "/{id}")	//	Annotation do spring usada para fazer a requisição delete do HTTP, recebendo id como parametro de caminho.
	public ResponseEntity<Void> delete(@PathVariable Long id){
		// PathVariable é usado para o Long id ser reconhecido como um ID no banco.
		service.delete(id);
		return ResponseEntity.
				noContent().build();	// Vai retornar uma resposta vazia, e o codigo http com resposta vazia é o 204.
	}
}
