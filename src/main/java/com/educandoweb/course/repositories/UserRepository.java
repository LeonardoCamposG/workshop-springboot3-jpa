package com.educandoweb.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.educandoweb.course.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	//não necessita implementar a interface, pois o spring jpa ja tem uma implementação para essa interface.
}
