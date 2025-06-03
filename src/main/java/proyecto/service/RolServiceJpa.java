package proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import proyecto.model.Rol;
import proyecto.repository.RolRepositoryl;

@Service
@Primary
public class RolServiceJpa implements RolService{
	@Autowired
	private RolRepositoryl rr;

	@Override
	public List<Rol> buscarTodos() {
		return rr.findAll();
	}

	@Override
	public Rol buscarPorId(Long id) {
		Optional<Rol> optional = rr.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardar(Rol rol) {
		rr.save(rol);
	}

	@Override
	public void borrar(Long id) {
		rr.deleteById(id);
	}

	@Override
	public void Editar(Rol rol) {
		if (rr.existsById(rol.getId())) { // Verifica si el producto existe
	        rr.save(rol); // Guarda los cambios
	    }
	}
	
	
}
