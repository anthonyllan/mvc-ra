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
import proyecto.model.Horario;
import proyecto.service.EmpleadoService;
import proyecto.service.HorarioService;

@Controller
@RequestMapping(value="/hora")
public class HorarioController {
	@Autowired
	private EmpleadoService serviceEmp;
	@Autowired
	private HorarioService serviceH;
	
	@GetMapping("/inicio")
	public String mostrarLista(Model model) {
	    List<Horario> lista = serviceH.buscarTodos();
	    model.addAttribute("horarioH", lista); // Lista de departamentos
	    model.addAttribute("contenido", "horario/listaH"); // Fragmento que se insertará
	    return "layout"; // Usar layout.html como base
	}	
	
	@GetMapping("/crear")
	public String crear(Model model) {
	    model.addAttribute("horario", new Horario());
	    model.addAttribute("empleados", serviceEmp.buscarTodos());
	    model.addAttribute("contenido", "horario/fromH");
	    return "layout";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
	    Horario h = serviceH.buscarPorId(id);
	    if (h != null) {
	        model.addAttribute("horario", h);
	        model.addAttribute("empleados", serviceEmp.buscarTodos());
	        model.addAttribute("contenido", "horario/fromH");
	        return "layout";
	    } else {
	        return "redirect:/hora/inicio";
	    }
	}	
	
	@PostMapping("/guardar")
	public String guardar(@Valid Horario h, BindingResult result, RedirectAttributes atributos, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("contenido", "horario/fromH");
	        return "layout";
	    }
	    serviceH.guardarHorario(h); // JPA detecta si es insert o update
	    atributos.addFlashAttribute("msg", "Se guardó el Horario correctamente");
	    return "redirect:/hora/inicio";
	}	
	
	@GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("horario", new Horario());
        return "hora/inicio"; // Nombre de la vista HTML
    }
	
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        System.out.println("Horario eliminado con el ID: " + id);
        serviceH.borrarHorario(id);
        return "redirect:/hora/inicio";
    }	
}
