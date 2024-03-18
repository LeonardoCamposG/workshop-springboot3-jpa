package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.OrderItem;
import com.educandoweb.course.entities.pk.OrderItemPK;


public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK>{

	//não necessita implementar a interface, pois o spring jpa ja foi herdado para essa interface.
}
