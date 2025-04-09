package com.formacion.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.formacion.entities.UserApi.User;
import com.formacion.service.ApiService;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/usuario")
@Log4j2
public class ControladorUsuario {
	
	private ApiService apiService;
	
	public ControladorUsuario(ApiService apiService) {
		this.apiService = apiService;
	}
	
	
	@PostMapping("/updateUsuario")
	public ResponseEntity<String> updateUsuario(@RequestParam Long id,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String image) {

		log.info("Actualizamos el usuario de la modal en Principal");
		log.info("Llega dato con id: " + id + " y nombre: " + firstName);
		
		User user = new User();
		user.setId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setImage(image);
		
		apiService.updateUser(user);
		
		// Devuelve una respuesta de éxito
        return ResponseEntity.ok("Actualizado correctamente.");

	}
	

}
