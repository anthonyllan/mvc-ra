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
import proyecto.model.Departamento;
import proyecto.service.DepartamentoService;

@Controller
@RequestMapping(value="/depa")
public class DepartamentoController {
	@Autowired
	private DepartamentoService serviceDep;
	
	//metodo para mostrar la lista
	@GetMapping("/inicio")
	public String mostrarLista(Model model) {
	    List<Departamento> lista = serviceDep.buscarTodos();
	    model.addAttribute("departamentoD", lista); // Lista de departamentos
	    model.addAttribute("contenido", "departamento/listaD"); // Fragmento que se insertará
	    return "layout"; // Usar layout.html como base
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
	    model.addAttribute("departamento", new Departamento());
	    model.addAttribute("contenido", "departamento/fromD");
	    return "layout";
	}

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model) {
	    Departamento d = serviceDep.buscarPorId(id);
	    if (d != null) {
	        model.addAttribute("departamento", d);
	        model.addAttribute("contenido", "departamento/fromD");
	        return "layout";
	    } else {
	        return "redirect:/depa/inicio";
	    }
	}
	
	@PostMapping("/guardar")
	public String guardarProducto(@Valid Departamento dep, BindingResult result, RedirectAttributes atributos, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("contenido", "departamento/fromD");
	        return "layout";
	    }
	    serviceDep.guardarDepartamento(dep); // JPA detecta si es insert o update
	    atributos.addFlashAttribute("msg", "Se guardó el Departamento correctamente");
	    return "redirect:/depa/inicio";
	}
		
		@GetMapping("/formulario")
	    public String mostrarFormulario(Model model) {
	        model.addAttribute("departamento", new Departamento());
	        return "depa/inicio"; // Nombre de la vista HTML
	    }
	    
	  //Metodo para eliminar el producto por el id
	    @GetMapping("/eliminar/{id}")
	    public String eliminar(@PathVariable("id") Integer id) {
	        System.out.println("Departamento eliminado con el ID: " + id);
	        serviceDep.borrarDepartamento(id);
	        return "redirect:/depa/inicio";
	    } 
}
