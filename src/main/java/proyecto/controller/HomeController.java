package proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/home")
	public String mostrarHome(Model model) {
		model.addAttribute("titulo", "Inicio");
	    model.addAttribute("contenido", "home"); // nombre del archivo sin .html
	    return "layout"; // layout.html será el contenedor
    }
}
