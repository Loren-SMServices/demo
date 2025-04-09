package com.formacion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formacion.entities.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Usuario findByUsername(String username); // Buscar usuario por su nombre
}
