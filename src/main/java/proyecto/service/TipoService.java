package proyecto.service;

import java.util.List;

import proyecto.model.Tipo;

public interface TipoService {
	List <Tipo> buscarTodos();
	Tipo buscarPorId(Integer id);
	void guardarTipo(Tipo tipo);
	void borrarTipo(Integer id);
	void EditarTipo(Tipo tipo);
}
