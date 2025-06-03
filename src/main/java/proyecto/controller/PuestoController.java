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
import proyecto.model.Puesto;
import proyecto.service.PuestoService;

@Controller
@RequestMapping(value="/puesto")
public class PuestoController {
	@Autowired
	private PuestoService servicePuesto;
	
	//metodo para mostrar la lista
	@GetMapping("/inicio")
	public String mostrarLista(Model model) {
		List<Puesto> lista = servicePuesto.buscarTodos();
		model.addAttribute("puestoP", lista);
		model.addAttribute("contenido", "puesto/listaP");
		return "layout";
	}

	@GetMapping("/crear")
	public String crear(Model model) {
	    model.addAttribute("puesto", new Puesto());
	    model.addAttribute("contenido", "puesto/fromP");
	    return "layout";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
	    Puesto p = servicePuesto.buscarPorId(id);
	    if (p != null) {
	        model.addAttribute("puesto", p);
	        model.addAttribute("contenido", "puesto/fromP"
	        		+ "");
	        return "layout";
	    } else {
	        return "redirect:/puesto/inicio";
	    }
	}

	@PostMapping("/guardar")
	public String guardarProducto(@Valid Puesto puesto, BindingResult result, RedirectAttributes atributos, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("contenido", "puesto/fromP");
	        return "layout";
	    }
	    servicePuesto.guardarPuesto(puesto); // JPA detecta si es insert o update
	    atributos.addFlashAttribute("msg", "Se guardó el Puesto correctamente");
	    return "redirect:/puesto/inicio";
	}
	
	@GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("puesto", new Puesto());
        return "puesto/inicio"; // Nombre de la vista HTML
    }	
	
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        System.out.println("Puesto eliminado con el ID: " + id);
        servicePuesto.borrarPuesto(id);
        return "redirect:/puesto/inicio";
    } 	
}
