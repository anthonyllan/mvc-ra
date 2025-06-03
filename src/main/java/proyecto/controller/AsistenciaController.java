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
import proyecto.model.Asistencia;
import proyecto.service.AsistenciaService;
import proyecto.service.EmpleadoService;
import proyecto.service.PermisoService;

@Controller
@RequestMapping(value="/asis")
public class AsistenciaController {
	@Autowired
	private EmpleadoService serviceE;
	@Autowired
	private AsistenciaService serviceA;
	@Autowired
	private PermisoService serviceP;
	
	@GetMapping("/inicio")
	public String mostrarLista(Model model) {
		List<Asistencia> lista = serviceA.buscarTodos();
	    model.addAttribute("asistenciaA", lista); // Lista de departamentos
	    model.addAttribute("contenido", "asistencia/listaA"); // Fragmento que se insertará
	    return "layout"; // Usar layout.html como base
	}	
	
	@GetMapping("/crear")
	public String crear(Model model) {
	    model.addAttribute("asistencia", new Asistencia());
	    model.addAttribute("empleados", serviceE.buscarTodos());
	    model.addAttribute("permisos", serviceP.buscarTodos());
	    model.addAttribute("contenido", "asistencia/fromA");
	    return "layout";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
	    Asistencia a = serviceA.buscarPorId(id);
	    if (a != null) {
	        model.addAttribute("asistencia", a);
	        model.addAttribute("empleados", serviceE.buscarTodos());
	        model.addAttribute("permisos", serviceP.buscarTodos());
	        model.addAttribute("contenido", "asistencia/fromA");
	        return "layout";
	    } else {
	        return "redirect:/asis/inicio";
	    }
	}	
	
	@PostMapping("/guardar")
	public String guardar(@Valid Asistencia asistencia, BindingResult result, 
	                     RedirectAttributes atributos, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("empleados", serviceE.buscarTodos());
	        model.addAttribute("permisos", serviceP.buscarTodos());
	        model.addAttribute("contenido", "asistencia/fromA");
	        return "layout";
	    }
	    
	    try {
	        serviceA.guardarAsistencia(asistencia);
	        atributos.addFlashAttribute("msg", "Asistencia registrada y cálculos realizados");
	    } catch (Exception e) {
	        atributos.addFlashAttribute("error", "Error al guardar la asistencia: " + e.getMessage());
	    }
	    
	    return "redirect:/asis/inicio";
	}
	
	@GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("asistencia", new Asistencia());
        return "asis/inicio"; // Nombre de la vista HTML
    }
	
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        System.out.println("Asistencia eliminado con el ID: " + id);
        serviceA.borrarAsistencia(id);
        return "redirect:/asis/inicio";
    }
}
