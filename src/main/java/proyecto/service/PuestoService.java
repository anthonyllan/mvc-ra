package proyecto.service;

import java.util.List;

import proyecto.model.Puesto;

public interface PuestoService {
	List <Puesto> buscarTodos();
	Puesto buscarPorId(Integer id);
	void guardarPuesto(Puesto puesto);
	void borrarPuesto(Integer id);
	void EditarPuesto(Puesto puesto);
}
