package com.formacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formacion.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); // Buscar usuario por su nombre
}
