package proyecto.service;

import java.util.List;

import proyecto.model.Departamento;


public interface DepartamentoService {
	List <Departamento> buscarTodos();
	Departamento buscarPorId(Integer id);
	void guardarDepartamento(Departamento departamento);
	void borrarDepartamento(Integer id);
	void EditarDepartamento(Departamento departamento);
}
