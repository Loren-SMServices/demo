package com.formacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formacion.entities.UserApi.User;

@Repository
public interface ResponseRepository extends JpaRepository<User, Long> {
	User findUserById(Long id);
}

