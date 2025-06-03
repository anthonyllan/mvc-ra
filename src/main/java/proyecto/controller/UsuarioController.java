package proyecto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import proyecto.dto.UsuarioRegistroDto;
import proyecto.model.Usuario;
import proyecto.service.UsuarioService;

@Controller
@RequestMapping(value="/registro")
public class UsuarioController {
	@Autowired
	private UsuarioService su;

	public UsuarioController(UsuarioService su) {
		super();
		this.su = su;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDto retornarNuevoUsuarioRegistroDto() {
		return new UsuarioRegistroDto();
	}
	
	@GetMapping
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}
	
	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDto registroDto) {
		su.guardarUsuario(registroDto);
		return "redirect:/registro?exito";
	}
	
	@GetMapping("/inicio")
	public String mostrarLista(Model model) {
	    List<Usuario> lista = su.buscarTodos();
	    model.addAttribute("usuarioU", lista); // Lista de departamentos
	    model.addAttribute("contenido", "usuario/listaU"); // Fragmento que se insertará
	    return "layout"; // Usar layout.html como base
	}	
	
	@GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        System.out.println("Usuario eliminado con el ID: " + id);
        su.borrar(id);
        return "redirect:/registro/inicio";
    }
	   
}
