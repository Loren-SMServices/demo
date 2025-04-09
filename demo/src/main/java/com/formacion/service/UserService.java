package com.formacion.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.formacion.entities.Usuario;
import com.formacion.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Gaurda el usuario con el rol de Usuario por defecto
     * @param user
     * @return
     */
    public Usuario saveUser(Usuario user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // Asignar un rol por defecto si no se proporciona
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER"); // Rol predeterminado
        }
        return userRepository.save(user);
    }
    
    public List<Usuario> findAllUsers() {
        return userRepository.findAll();
    }
    
    public Long countUsers() {
        return userRepository.count();
    }
    
    /**
     * Verifica que la contraseña del formulario coincide con la hasheada
     * @param username
     * @param rawPassword
     * @return
     */
    public boolean verifyUserCredentials(String username, String rawPassword) {
    	Usuario user = userRepository.findByUsername(username);
        if (user != null) {
            // Compara la contraseña ingresada con la encriptada almacenada
            return passwordEncoder.matches(rawPassword, user.getPassword());
        }
        return false;
    }

    public boolean verifyPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * Este metodo viene implicito de UserDetailsService
     * Spring Security lo usa internamente para gestionar usuarios y permisos 
     */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        return user;

	}

    
}