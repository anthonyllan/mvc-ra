package proyecto.service;

import java.util.List;

import proyecto.model.Estado;

public interface EstadoService {
	List <Estado> buscarTodos();
	Estado buscarPorId(Integer id);
	void guardarEstado(Estado es);
	void borrarEstado(Integer id);
	void EditarEstado(Estado es);
}
