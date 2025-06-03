package proyecto.service;

import java.util.List;

import proyecto.model.Rol;

public interface RolService {
	List <Rol> buscarTodos();
	Rol buscarPorId(Long id);
	void guardar(Rol rol);
	void borrar(Long id);
	void Editar(Rol rol);
}
