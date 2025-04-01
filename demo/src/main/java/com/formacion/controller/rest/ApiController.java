package com.formacion.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.formacion.entities.UserApiResponse.User;
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
	    List<User> lista = apiService.getApiDataList();
	    model.addAttribute("lista", lista); // Envía la lista a la vista
	    return "principal";
	}

}
