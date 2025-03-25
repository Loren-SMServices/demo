package com.formacion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller lo usaremos para movernos entre vistas (HTML, JSP...) 
 * 
 */
@Controller
public class Controlador {
	
	/**
	 * Solicitud GET, en este caso sera nuestra ruta raiz (localhost:8080/)
	 * segun arranquemos encontrara la ruta / recogera los datos en el modelo y redirigira a la vista Index
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String inicio(Model model) {
		//model se encarga de mandar los datos en formato clave valor
		model.addAttribute("texto", "Texto de ejemplo");
		//Devolvemos el nombre de la vista
		return "index";
	}
	
	/**
	 * Solicitud GET, en este caso sera nuestra ruta raiz (localhost:8080/index)
	 * Esto es un problema ya que no podemos dejar que se acceda libremente a cualquier ruta
	 * @param model
	 * @return
	 */
	@GetMapping("/index")
	public String inicioDesdeIndex(Model model) {
		model.addAttribute("texto", "Mas tarde habra que encargarse de capar esto");
		//Devolvemos el nombre de la vista 
		return "index";
	}

}
