package com.formacion.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formacion.entities.UserApi.User;
import com.formacion.entities.UserApi.UserApiResponse;
import com.formacion.repository.ResponseRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.extern.java.Log;

@Service
@Log
public class ApiService {

	private final WebClient webClient;

	@PersistenceContext // Inyectar el EntityManager
	private EntityManager entityManager;

	@Autowired
	private ResponseRepository responseRepository;

	public ApiService(WebClient webClient) {
		this.webClient = webClient;
	}

	public List<User> getApiDataList() {
		String url = "https://dummyjson.com/users"; // URL de la API
		String response = webClient.get().uri(url).retrieve().bodyToMono(String.class) // Recibe como texto
				.block();
		log.info(response);

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// Convierte el JSON al objeto `UsersResponse`
			UserApiResponse usersResponse = objectMapper.readValue(response, UserApiResponse.class);
			
			log.info("Devolvemos " + usersResponse.getUsers().size() + " filas");
			return usersResponse.getUsers(); // Devuelve la lista de usuarios
		} catch (Exception e) {
			log.info("Error al mapear el JSON a UsersResponse " + e.getMessage());
			return null;
		}
	}

	public List<User> getAllUserApiResponse() {
		return responseRepository.findAll();
	}

	@Transactional
	public List<User> saveAllApiUser(List<User> users) {
	    List<User> usuariosGuardados = new ArrayList<>();
	    
	    for (User user : users) {
	    	// Verificar si el usuario ya existe en la base de datos
            Optional<User> optionalUser = responseRepository.findById(user.getId());
            if(optionalUser.isPresent()) {
            	user = optionalUser.get();
            }else {
            	user.setId(null);
            }
	    	if (user.getId() == null) {
	    		log.info("Usuario no encontrado, se guardara");
	            // Entidad nueva: inicializar versión
	            user.setVersion(0);
	            usuariosGuardados.add(responseRepository.save(user));
	        } else {
	            // Entidad existente: recuperar y actualizar
	            User managedUser = responseRepository.findById(user.getId())
	                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
	            managedUser.setFirstName(user.getFirstName());
	            usuariosGuardados.add(managedUser);
	        }
	    }
	    return usuariosGuardados;
	}
	
	@Transactional
	public User findUserById(Long id) {
		return responseRepository.findUserById(id);	    
	}
	
	public User updateUser(User user) {
		User existingUser = responseRepository.findById(user.getId()).orElseThrow();
		existingUser.setFirstName(user.getFirstName()); // Actualiza campos
		existingUser.setLastName(user.getLastName());
		existingUser.setImage(user.getImage());
		return responseRepository.save(existingUser); // Guarda cambios

	}

}
