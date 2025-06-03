package proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import proyecto.model.Tipo;
import proyecto.repository.TipoRepository;

@Service
@Primary
public class TipoServiceJpa implements TipoService{
	@Autowired
	private TipoRepository tRepo;
	
	@Override
	public List<Tipo> buscarTodos() {
		return tRepo.findAll();
	}

	@Override
	public Tipo buscarPorId(Integer id) {
		Optional<Tipo> optional = tRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardarTipo(Tipo tipo) {
		tRepo.save(tipo);
	}

	@Override
	public void borrarTipo(Integer id) {
		tRepo.deleteById(id);
	}

	@Override
	public void EditarTipo(Tipo tipo) {
		if (tRepo.existsById(tipo.getId())) { // Verifica si el producto existe
	        tRepo.save(tipo); // Guarda los cambios
	    }
	}
}
