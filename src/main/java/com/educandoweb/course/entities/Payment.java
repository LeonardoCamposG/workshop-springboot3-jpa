package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity 					// Annotation para mapear o objeto relacional.
@Table(name = "tb_payment")	// Especificando o nome para a tabela no DB(user é uma palavra reservada no H2).
public class Payment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id 		// Annotation que especifica a PK da tabela(Colocada acima do atributo).
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	// Dizendo que vai ser uma PK com auto increment.
	private Long id;
	private Instant moment;
	
	@OneToOne	// Relação de um para um.
	@MapsId
	private Order order;
	
	public Payment() {
	}

	public Payment(Long id, Instant moment, Order order) {
		super();
		this.id = id;
		this.moment = moment;
		this.order = order;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
