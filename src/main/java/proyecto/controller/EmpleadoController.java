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
import proyecto.model.Empleado;
import proyecto.service.DepartamentoService;
import proyecto.service.EmpleadoService;
import proyecto.service.PuestoService;

@Controller
@RequestMapping(value="/emp")
public class EmpleadoController {
	@Autowired
	private EmpleadoService serviceEmp;
	@Autowired
	private PuestoService servicePue;
	@Autowired
	private DepartamentoService serviceDep;

	@GetMapping("/inicio")
	public String mostrarLista(Model model) {
	    List<Empleado> lista = serviceEmp.buscarTodos();
	    model.addAttribute("empleadoE", lista); // Lista de departamentos
	    model.addAttribute("contenido", "empleado/listaE"); // Fragmento que se insertará
	    return "layout"; // Usar layout.html como base
	}	
	
	@GetMapping("/crear")
	public String crear(Model model) {
	    model.addAttribute("empleado", new Empleado());
	    model.addAttribute("puestos", servicePue.buscarTodos());
	    model.addAttribute("departamentos", serviceDep.buscarTodos());
	    model.addAttribute("contenido", "empleado/fromE");
	    return "layout";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
	    Empleado emp = serviceEmp.buscarPorId(id);
	    if (emp != null) {
	        model.addAttribute("empleado", emp);
	        model.addAttribute("puestos", servicePue.buscarTodos());
	        model.addAttribute("departamentos", serviceDep.buscarTodos());
	        model.addAttribute("contenido", "empleado/fromE");
	        return "layout";
	    } else {
	        return "redirect:/emp/inicio";
	    }
	}
	
	@GetMapping("/ver/{id}")
	public String ver(@PathVariable("id") Integer id, Model model) {
	    Empleado empleado = serviceEmp.buscarPorId(id);
	    if (empleado != null) {
	        model.addAttribute("empleado", empleado);
	        model.addAttribute("contenido", "empleado/verE");
	        return "layout";
	    } else {
	        return "redirect:/emp/inicio";
	    }
	}	
	
	@PostMapping("/guardar")
	public String guardar(@Valid Empleado emp, BindingResult result, RedirectAttributes atributos, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("contenido", "empleado/fromE");
	        return "layout";
	    }
	    serviceEmp.guardarEmp(emp); // JPA detecta si es insert o update
	    atributos.addFlashAttribute("msg", "Se guardó el Empleado correctamente");
	    return "redirect:/emp/inicio";
	}	
	
	@GetMapping("/formulario")
    public String mostrarFormulario(Model model) {
        model.addAttribute("empleado", new Empleado());
        return "emp/inicio"; // Nombre de la vista HTML
    }
	
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Integer id) {
        System.out.println("Empleado eliminado con el ID: " + id);
        serviceEmp.borrarEmp(id);
        return "redirect:/emp/inicio";
    } 
}
