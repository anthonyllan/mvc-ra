package proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import proyecto.model.Retardos;
import proyecto.service.RetardosService;

@Controller
@RequestMapping(value="/tarde")
public class RetardosController {
	@Autowired
	private RetardosService sr;
	
	@GetMapping("/inicio")
	public String mostrarLista(Model model) {
	    List<Retardos> lista = sr.buscarTodos();
	    model.addAttribute("retardoR", lista); // Lista de departamentos
	    model.addAttribute("contenido", "retardo/listaR"); // Fragmento que se insertará
	    return "layout"; // Usar layout.html como base
	}
}
