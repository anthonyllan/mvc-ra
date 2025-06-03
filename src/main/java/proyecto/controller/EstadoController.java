package proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
import proyecto.model.Estado;
import proyecto.service.EstadoService;

@Controller
@RequestMapping(value="/estado")
public class EstadoController {
	@Autowired
	private EstadoService serviceE;
	
	@GetMapping("/inicio")
	public String mostrarLista(Model model) {
	    List<Estado> lista = serviceE.buscarTodos();
	    model.addAttribute("estadoE", lista); // Lista de departamentos
	    model.addAttribute("contenido", "estado/listaE"); // Fragmento que se insertará
	    return "layout"; // Usar layout.html como base
	}	
	
	@GetMapping("/crear")
	public String crear(Model model) {
	    model.addAttribute("estado", new Estado());
	    model.addAttribute("contenido", "estado/fromE");
	    return "layout";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
	    Estado e = serviceE.buscarPorId(id);
	    if (e != null) {
	        model.addAttribute("estado", e);
	        model.addAttribute("contenido", "estado/fromE");
	        return "layout";
	    } else {
	        return "redirect:/estado/inicio";
	    }
	}	
	
	@PostMapping("/guardar")
	public String guardar(@Valid Estado e, BindingResult result, RedirectAttributes atributos, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("contenido", "estado/fromE");
	        return "layout";
	    }
	    serviceE.guardarEstado(e); // JPA detecta si es insert o update
	    atributos.addFlashAttribute("msg", "Se guardó el Estado Permiso correctamente");
	    return "redirect:/estado/inicio";
	}	
	
	@GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("estado", new Estado());
        return "estado/inicio"; // Nombre de la vista HTML
    }
	
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        System.out.println("Estado eliminado con el ID: " + id);
        serviceE.borrarEstado(id);
        return "redirect:/estado/inicio";
    }	
}
