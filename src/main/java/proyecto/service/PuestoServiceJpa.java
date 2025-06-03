package proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import proyecto.model.Puesto;
import proyecto.repository.PuestoRepository;

@Service
@Primary
public class PuestoServiceJpa implements PuestoService{
	@Autowired
	private PuestoRepository puestoRepo;

	@Override
	public List<Puesto> buscarTodos() {
		return puestoRepo.findAll();
	}

	@Override
	public Puesto buscarPorId(Integer id) {
		Optional<Puesto> optional = puestoRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardarPuesto(Puesto puesto) {
		puestoRepo.save(puesto);
	}

	@Override
	public void borrarPuesto(Integer id) {
		puestoRepo.deleteById(id);
	}

	@Override
	public void EditarPuesto(Puesto puesto) {
		if (puestoRepo.existsById(puesto.getId())) { // Verifica si el producto existe
	        puestoRepo.save(puesto); // Guarda los cambios
		}
	}
}
