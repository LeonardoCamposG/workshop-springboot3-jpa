package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.Category;


public interface CategoryRepository extends JpaRepository<Category, Long>{

	//não necessita implementar a interface, pois o spring jpa ja foi herdado para essa interface.
}
