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
import proyecto.model.Tipo;
import proyecto.service.TipoService;

@Controller
@RequestMapping(value="/tipo")
public class TipoController {
	@Autowired
	private TipoService serviceT;
	
	@GetMapping("/inicio")
	public String mostrarLista(Model model) {
	    List<Tipo> lista = serviceT.buscarTodos();
	    model.addAttribute("tipoT", lista); // Lista de departamentos
	    model.addAttribute("contenido", "tipo/listaT"); // Fragmento que se insertará
	    return "layout"; // Usar layout.html como base
	}	
	
	@GetMapping("/crear")
	public String crear(Model model) {
	    model.addAttribute("tipo", new Tipo());
	    model.addAttribute("contenido", "tipo/fromT");
	    return "layout";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
	    Tipo t = serviceT.buscarPorId(id);
	    if (t != null) {
	        model.addAttribute("tipo", t);
	        model.addAttribute("contenido", "tipo/fromT");
	        return "layout";
	    } else {
	        return "redirect:/tipo/inicio";
	    }
	}	
	
	@PostMapping("/guardar")
	public String guardar(@Valid Tipo t, BindingResult result, RedirectAttributes atributos, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("contenido", "tipo/fromT");
	        return "layout";
	    }
	    serviceT.guardarTipo(t); // JPA detecta si es insert o update
	    atributos.addFlashAttribute("msg", "Se guardó el Tipo Permiso correctamente");
	    return "redirect:/tipo/inicio";
	}	
	
	@GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("tipo", new Tipo());
        return "tipo/inicio"; // Nombre de la vista HTML
    }
	
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        System.out.println("Horario eliminado con el ID: " + id);
        serviceT.borrarTipo(id);
        return "redirect:/tipo/inicio";
    }	
}
