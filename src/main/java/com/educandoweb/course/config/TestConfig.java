package com.educandoweb.course.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.educandoweb.course.entities.Order;
import com.educandoweb.course.entities.User;
import com.educandoweb.course.entities.enums.OrderStatus;
import com.educandoweb.course.repositories.OrderRepository;
import com.educandoweb.course.repositories.UserRepository;

@Configuration    // Informando o spring que essa é uma classe de configuração para um perfil em especifico.
@Profile("test")  // Informando o ambiente/perfil que a classe deverá funcionar.
public class TestConfig implements CommandLineRunner {

	// Annotation importantissíma. Faz o spring resolver a injeção de dependência de forma automática
	// associando uma instância do UserRepository ao TestConfig.
	@Autowired	
	private UserRepository userRepository; // Declarando dependência.
	
	@Autowired
	private OrderRepository orderRepository; // Declarando dependência.
	
	// Tudo dentro desse método run, vai ser executado quando a aplicação for iniciada.
	@Override
	public void run(String... args) throws Exception { 
		// Id esta nulo porque vai ser auto incrementado no banco.
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456"); 
		
		Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
		Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
		Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1); 
		
		// Metodo saveAll recebe uma lista de objetos e salva ela no DB.
		userRepository.saveAll(Arrays.asList(u1, u2));
		orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}
}
