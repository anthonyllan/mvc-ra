package proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import proyecto.model.Estado;
import proyecto.repository.EstadoRepository;

@Service
@Primary
public class EstadoServiceJpa implements EstadoService{
	@Autowired
	private EstadoRepository eRepo;

	@Override
	public List<Estado> buscarTodos() {
		return eRepo.findAll();
	}

	@Override
	public Estado buscarPorId(Integer id) {
		Optional<Estado> optional = eRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardarEstado(Estado es) {
		eRepo.save(es);
	}

	@Override
	public void borrarEstado(Integer id) {
		eRepo.deleteById(id);
	}

	@Override
	public void EditarEstado(Estado es) {
		if (eRepo.existsById(es.getId())) { // Verifica si el producto existe
	        eRepo.save(es); // Guarda los cambios
	    }
	}
}
