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
import proyecto.model.Rol;
import proyecto.service.RolService;

@Controller
@RequestMapping(value="/rol")
public class RolController {
	@Autowired
	private RolService rs;
	
	@GetMapping("/inicio")
	public String mostrarLista(Model model) {
	    List<Rol> lista = rs.buscarTodos();
	    model.addAttribute("rolR", lista); // Lista de departamentos
	    model.addAttribute("contenido", "rol/listaR"); // Fragmento que se insertará
	    return "layout"; // Usar layout.html como base
	}	
	
	@GetMapping("/crear")
	public String crear(Model model) {
	    model.addAttribute("rol", new Rol());
	    model.addAttribute("contenido", "rol/fromR");
	    return "layout";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Long id, Model model) {
	    Rol e = rs.buscarPorId(id);
	    if (e != null) {
	        model.addAttribute("rol", e);
	        model.addAttribute("contenido", "rol/fromR");
	        return "layout";
	    } else {
	        return "redirect:/rol/inicio";
	    }
	}	
	
	@PostMapping("/guardar")
	public String guardar(@Valid Rol e, BindingResult result, RedirectAttributes atributos, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("contenido", "rol/fromR");
	        return "layout";
	    }
	    rs.guardar(e); // JPA detecta si es insert o update
	    atributos.addFlashAttribute("msg", "Se guardó el Rol Permiso correctamente");
	    return "redirect:/rol/inicio";
	}	
	
	@GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("rol", new Rol());
        return "rol/inicio"; // Nombre de la vista HTML
    }
	
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        System.out.println("Rol eliminado con el ID: " + id);
        rs.borrar(id);
        return "redirect:/rol/inicio";
    }	
}
