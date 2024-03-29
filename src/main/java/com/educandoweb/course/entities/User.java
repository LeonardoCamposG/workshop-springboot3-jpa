package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

//
@Entity 					// Annotation para mapear o objeto relacional.
@Table(name = "tb_user")	// Especificando o nome para a tabela no DB(user é uma palavra reservada no H2).
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 		// Annotation que especifica a PK da tabela(Colocada acima do atributo).
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	// Dizendo que vai ser uma PK com auto increment.
	private Long 	id;
	private String	name;
	private String	email;
	private String	phone;
	private	String	password;
	
	// Quando você faz uma requisição, no postman por exemplo, em um objeto de relação um para muitos
	// se a requisição for feita do lado do um, ele não carrega o lado do muitos por padrão(lazy loading).
	
	@JsonIgnore		// Annotation para usar a biblioteca jackson, possibilitando consulta no DB na hora de serializar o JSON.
	@OneToMany(mappedBy = "client")		// Annotation especificando o atributo que esta criando o relacionamento no DB na outra classe.
	private List<Order> orders = new ArrayList<>();
	
	public User() {
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {  // Em comparação de entidades, é comun comparar por id.
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
}
