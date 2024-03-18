package com.educandoweb.course.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.educandoweb.course.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity 					// Annotation para mapear o objeto relacional.
@Table(name = "tb_order")	// Especificando o nome para a tabela no DB(order é uma palavra reservada no H2).
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id 		// Annotation que especifica a PK da tabela(Colocada acima do atributo).
	@GeneratedValue(strategy = GenerationType.IDENTITY) 	// Dizendo que vai ser uma PK com auto increment.
	private Long id;
	
	// Annotation usada para formatar o Instant no json, passando o formato como parametro de String e a timezone a ser usada.
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	
	private Integer orderStatus;	// Usando Integer para melhor tratamendo no DB
		
	// Quando você faz uma requisição, no postman por exemplo, em um objeto de relação muitos para um
	// se a requisição for feita do lado do muitos, ele automaticamente traz no json o objeto de relação um também.
	
	@ManyToOne		// Annotation para especificar a coluna relacionamento entre as 2 classes no banco de dados(Muitos para um).
	@JoinColumn(name = "client_id")		// Annotation para dar um alias na foreing key.
	private User client;
	
	
	@OneToMany(mappedBy = "id.order", fetch = FetchType.EAGER)	// id.order para acessar a order entro do id da PK.
	private Set<OrderItem> items = new HashSet<>();
	
	public Order() {
	}

	public Order(Long id, Instant moment, OrderStatus orderStatus, User client) {
		super();
		this.id = id;
		this.moment = moment;
		setOrderStatus(orderStatus);
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

	public OrderStatus getOrderStatus() {
		return OrderStatus.valueOf(orderStatus);
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		if(orderStatus != null) {
			this.orderStatus = orderStatus.getCode();
		}
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}
	
	public Set<OrderItem> getItems(){
		return items;
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
