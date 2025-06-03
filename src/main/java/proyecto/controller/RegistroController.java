package proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RegistroController {
	@GetMapping("/login")
	public String iniciarSecion() {
	    return "login"; // Usar layout.html como base
	}
	
	@GetMapping("/")
	public String mostrarHome(Model model) {
		model.addAttribute("titulo", "Inicio");
	    model.addAttribute("contenido", "home"); // nombre del archivo sin .html
	    return "layout"; // layout.html será el contenedor
    }
}
