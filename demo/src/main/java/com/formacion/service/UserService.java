package com.formacion.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.formacion.entities.User;
import com.formacion.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
    	// Codificar la contraseña antes de guardar
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
    
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
    
    public Long countUsers() {
        return userRepository.count();
    }
    
 // Nuevo método para verificar credenciales
    public boolean verifyUserCredentials(String username, String rawPassword) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            // Compara la contraseña ingresada con la encriptada almacenada
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    
}