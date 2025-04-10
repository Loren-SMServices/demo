package com.formacion.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formacion.service.ApiService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/test")
public class AngularController {
	
	@Autowired
    private ApiService apiService;
	
	 // Enviar un mensaje al cliente (Angular) 
	    @GetMapping("/enviar")
	    public String obtenerMensaje() { 
	    	return apiService.getApiData();
	    } 

	    // Recibir un mensaje del cliente (Angular) 
	    @PostMapping("/recibir")
	    public String recibirMensaje(@RequestBody String mensaje) { 

	        System.out.println("Mensaje recibido: " + mensaje); 

	        return "Mensaje recibido correctamente: " + mensaje; 

	    } 

}
