package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 					// Annotation para mapear o objeto relacional.
@Table(name = "tb_order")	// Especificando o nome para a tabela no DB(order é uma palavra reservada no H2).
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 		// Annotation que especifica a PK da tabela(Colocada acima do atributo).
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	// Dizendo que vai ser uma PK com auto increment.
	private Long id;
	private Instant moment;
	
	@ManyToOne		// Annotation para especificar a coluna relacionamento entre as 2 classes no banco de dados(Muitos para um).
	@JoinColumn(name = "client_id")		// Annotation para dar um alias na foreing key.
	private User client;
	
	public Order() {
	}

	public Order(Long id, Instant moment, User client) {
		super();
		this.id = id;
		this.moment = moment;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	@Override
	public int hashCode() {
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}
}
