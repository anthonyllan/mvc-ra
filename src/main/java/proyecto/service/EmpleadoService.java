package proyecto.service;

import java.util.List;

import proyecto.model.Empleado;

public interface EmpleadoService {
	List <Empleado> buscarTodos();
	Empleado buscarPorId(Integer id);
	void guardarEmp(Empleado empleado);
	void borrarEmp(Integer id);
	void EditarEmp(Empleado empleado);
}
