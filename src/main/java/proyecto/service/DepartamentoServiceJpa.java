package proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import proyecto.model.Departamento;
import proyecto.repository.DepartamentoRepository;

@Service
@Primary
public class DepartamentoServiceJpa implements DepartamentoService{
	
	@Autowired
	private DepartamentoRepository depRepo;

	@Override
	public List<Departamento> buscarTodos() {
		return depRepo.findAll();
	}

	@Override
	public Departamento buscarPorId(Integer id) {
		Optional<Departamento> optional = depRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardarDepartamento(Departamento departamento) {
		depRepo.save(departamento);
	}

	@Override
	public void borrarDepartamento(Integer id) {
		depRepo.deleteById(id);
	}

	@Override
	public void EditarDepartamento(Departamento departamento) {
		if (depRepo.existsById(departamento.getId())) { // Verifica si el producto existe
	        depRepo.save(departamento); // Guarda los cambios
	    }
	}

}
