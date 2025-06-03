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
import proyecto.model.Permiso;
import proyecto.service.EmpleadoService;
import proyecto.service.EstadoService;
import proyecto.service.PermisoService;
import proyecto.service.TipoService;

@Controller
@RequestMapping(value="/per")
public class PermisoController {
	@Autowired
	private PermisoService sp;
	@Autowired
	private EmpleadoService se;
	@Autowired
	private EstadoService ses;
	@Autowired
	private TipoService st;
	
	@GetMapping("/inicio")
	public String mostrarLista(Model model) {
	    List<Permiso> lista = sp.buscarTodos();
	    model.addAttribute("permisoP", lista); // Lista de departamentos
	    model.addAttribute("contenido", "permiso/listaP"); // Fragmento que se insertará
	    return "layout"; // Usar layout.html como base
	}	
	
	@GetMapping("/crear")
	public String crear(Model model) {
	    model.addAttribute("permiso", new Permiso());
	    model.addAttribute("empleados", se.buscarTodos());
	    model.addAttribute("estados", ses.buscarTodos());
	    model.addAttribute("tipos", st.buscarTodos());
	    model.addAttribute("contenido", "permiso/fromP");
	    return "layout";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
	    Permiso p = sp.buscarPorId(id);
	    if (p != null) {
	        model.addAttribute("permiso", p);
	        model.addAttribute("empleados", se.buscarTodos());
		    model.addAttribute("estados", ses.buscarTodos());
		    model.addAttribute("tipos", st.buscarTodos());
	        model.addAttribute("contenido", "permiso/fromP");
	        return "layout";
	    } else {
	        return "redirect:/per/inicio";
	    }
	}	
	
	@PostMapping("/guardar")
	public String guardar(@Valid Permiso p, BindingResult result, RedirectAttributes atributos, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("contenido", "permiso/fromP");
	        return "layout";
	    }
	    sp.guardarPermiso(p); // JPA detecta si es insert o update
	    atributos.addFlashAttribute("msg", "Se guardó el Permiso correctamente");
	    return "redirect:/per/inicio";
	}	
	
	@GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("permiso", new Permiso());
        return "per/inicio"; // Nombre de la vista HTML
    }
	
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        System.out.println("Permiso eliminado con el ID: " + id);
        sp.borrarPermiso(id);
        return "redirect:/per/inicio";
    }	
}
