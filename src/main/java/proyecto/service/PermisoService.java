package proyecto.service;

import java.util.List;

import proyecto.model.Permiso;

public interface PermisoService {
	List <Permiso> buscarTodos();
	Permiso buscarPorId(Integer id);
	void guardarPermiso(Permiso per);
	void borrarPermiso(Integer id);
	void EditarPermiso(Permiso per);
}
