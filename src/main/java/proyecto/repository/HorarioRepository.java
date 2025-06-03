package proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.model.Empleado;
import proyecto.model.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Integer>{
	Horario findByEmpleado(Empleado empleado);
}
