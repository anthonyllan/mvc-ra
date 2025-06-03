package proyecto.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import proyecto.model.Empleado;
import proyecto.repository.EmpleadoRepository;

@Service
@Primary
public class EmpleadoServiceJpa implements EmpleadoService{
	@Autowired
	private EmpleadoRepository empRepo;

	@Override
	public List<Empleado> buscarTodos() {
		return empRepo.findAll();
	}

	@Override
	public Empleado buscarPorId(Integer id) {
		Optional<Empleado> optional = empRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Override
	public void guardarEmp(Empleado empleado) {
		empRepo.save(empleado);
	}

	@Override
	public void borrarEmp(Integer id) {
		empRepo.deleteById(id);
	}

	@Override
	public void EditarEmp(Empleado empleado) {
		if (empRepo.existsById(empleado.getId())) { // Verifica si el producto existe
	        empRepo.save(empleado); // Guarda los cambios
	    }
	}

}
