package proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import proyecto.model.Permiso;
import proyecto.repository.PermisoRepository;

@Service
@Primary
public class PermisoServiceJpa implements PermisoService{
	@Autowired
	private PermisoRepository pRepo;
	
	@Override
	public List<Permiso> buscarTodos() {
		return pRepo.findAll();
	}

	@Override
	public Permiso buscarPorId(Integer id) {
		Optional<Permiso> optional = pRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardarPermiso(Permiso per) {
		pRepo.save(per);
	}

	@Override
	public void borrarPermiso(Integer id) {
		pRepo.deleteById(id);
	}

	@Override
	public void EditarPermiso(Permiso per) {
		if (pRepo.existsById(per.getId())) { // Verifica si el producto existe
	        pRepo.save(per); // Guarda los cambios
	    }
	}

}
