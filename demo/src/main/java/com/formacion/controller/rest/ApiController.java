
package com.formacion.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.formacion.entities.UserApi.User;
import com.formacion.service.ApiService;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/api")
public class ApiController {
	
	@Autowired
    private ApiService apiService;
	
	@GetMapping("/data")
	public String fetchApiData(Model model) {
		
		log.info("Entramos en /api/data");
		
		List<User> listaResponse = apiService.getApiDataList();
		if(listaResponse!=null) {
			log.info("Guardamos los datos de la API en BBDD");
			listaResponse = apiService.saveAllApiUser(listaResponse);
		}else {
			log.info("Buscamos en BBDD");
			listaResponse = apiService.getAllUserApiResponse();
		}
		
		model.addAttribute("lista", listaResponse); // Envía la lista a la vista
		
	    return "principal";
	}

}
