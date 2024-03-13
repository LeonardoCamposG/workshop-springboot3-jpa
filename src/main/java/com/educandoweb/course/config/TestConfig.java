package com.educandoweb.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.User;
import com.educandoweb.course.repositories.UserRepository;

@Configuration    // Informando o spring que essa é uma classe de configuração para um perfil em especifico.
@Profile("test")  // Informando o ambiente/perfil que a classe deverá funcionar.
public class TestConfig implements CommandLineRunner {

	// Annotation importantissíma. Faz o spring resolver a injeção de dependência de forma automática
	// associando uma instância do UserRepository ao TestConfig.
	@Autowired	
	private UserRepository userRepository; // Declarando dependência.

	
	// Tudo dentro desse método run, vai ser executado quando a aplicação for iniciada.
	@Override
	public void run(String... args) throws Exception { 
		// Id esta nulo porque vai ser auto incrementado no banco.
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		// Metodo saveAll recebe uma lista de objetos e salva ela no DB.
		userRepository.saveAll(Arrays.asList(u1, u2));
	}
}