package proyecto.service;

import java.util.List;

import proyecto.model.Horario;

public interface HorarioService {
	List <Horario> buscarTodos();
	Horario buscarPorId(Integer id);
	void guardarHorario(Horario hora);
	void borrarHorario(Integer id);
	void EditarHorario(Horario hora);
}
