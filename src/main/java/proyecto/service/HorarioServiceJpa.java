package proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import proyecto.model.Horario;
import proyecto.repository.HorarioRepository;

@Service
@Primary
public class HorarioServiceJpa implements HorarioService{
	@Autowired
	private HorarioRepository hRepo;

	@Override
	public List<Horario> buscarTodos() {
		return hRepo.findAll();
	}

	@Override
	public Horario buscarPorId(Integer id) {
		Optional<Horario> optional = hRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardarHorario(Horario hora) {
		hRepo.save(hora);
	}

	@Override
	public void borrarHorario(Integer id) {
		hRepo.deleteById(id);
	}

	@Override
	public void EditarHorario(Horario hora) {
		if (hRepo.existsById(hora.getId())) { // Verifica si el producto existe
	        hRepo.save(hora); // Guarda los cambios
	    }
	}
}
