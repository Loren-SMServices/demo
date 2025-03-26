package com.formacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.formacion.entities.User;
import com.formacion.service.UserService;

import lombok.extern.java.Log;

/**
 * Controller lo usaremos para movernos entre vistas (HTML, JSP...) 
 * 
 */
@Controller
@Log
public class Controlador {
	
	@Autowired
    private UserService userService;

	
	/**
	 * Solicitud GET, en este caso sera nuestra ruta raiz (localhost:8080/)
	 * segun arranquemos encontrara la ruta / recogera los datos en el modelo y redirigira a la vista Index
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String inicio(Model model) {
		log.info("Arranca la app");
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
		log.info("Llamamos a index");
		model.addAttribute("texto", "Mas tarde habra que encargarse de capar esto");
		//Devolvemos el nombre de la vista 
		return "index";
	}
	
	/**
	 * Recibe el dato del formulario y lo reenvia de vuelta a index
	 * @param dato
	 * @param model
	 * @return
	 */
	@PostMapping("/formularioRegistro")
    public String procesarFormularioRegistro(String user,String password, Model model) {
		log.info("Procesamos el formulario de Registro");
		log.info(user);
		log.info(password);
		String mensaje = null;
		boolean existe = false;
		User usuario =  new User();
		usuario.setUsername(user);
		usuario.setPassword(password);
		List<User> lista = userService.findAllUsers();
		log.info("Hay " + userService.countUsers() + " usuarios registrados");
		
		for (User usu : lista) {
			if(usu.getUsername().equalsIgnoreCase(usuario.getUsername())) {
				existe = true;
				break;	
			}
		}
		
		if(!existe) {
			userService.saveUser(usuario);
			mensaje = "El usuario " + usuario.getUsername() + " ha sido creado";
		}
		
        // Añadir el dato recibido al modelo
        model.addAttribute("user", user);
        model.addAttribute("password", password);
        model.addAttribute("mensaje", mensaje==null?"El usuario " + usuario.getUsername() + " ya esta registrado":mensaje);
        return "index"; // Redirige de vuelta a la vista "formulario.jsp"
    }
	
	@PostMapping("/formularioLogin")
    public String procesarFormularioLogin(String user,String password, Model model) {
		log.info("Procesamos el formulario de Login");
		log.info(user);
		log.info(password);
		boolean exito = false;
		String mensaje = null;
		User usuario =  new User();
		usuario.setUsername(user);
		usuario.setPassword(password);
		String contraseñaHasheada = userService.findUserByUsername(usuario.getUsername()).getPassword();
		if(userService.verifyPassword(password, contraseñaHasheada)) {
			exito = true;
			log.info("Usuario correcto");
			mensaje = "Usuario " + usuario.getUsername() + " con Pass " + usuario.getPassword() + " logado con exito";
		}else {
			mensaje = "Error en usuario o contraseña";
		}
		
        // Añadir el dato recibido al modelo
        model.addAttribute("mensaje", mensaje==null?"El usuario" + usuario.getUsername() + " ya esta registrado":mensaje);
        return exito?"principal":"login"; // Redirige de vuelta a la vista "formulario.jsp"
    }
	
	@GetMapping("/loginRegistro")
    public String procesarFormularioLogin(String source) {
		return source.equalsIgnoreCase("registro")?"login":"index";
    }
}
