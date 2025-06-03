package proyecto.service;

import java.util.List;

import proyecto.model.Asistencia;

public interface AsistenciaService {
	List <Asistencia> buscarTodos();
	Asistencia buscarPorId(Integer id);
	void guardarAsistencia(Asistencia asis);
	void borrarAsistencia(Integer id);
	void EditarAsistencia(Asistencia asis);
}
