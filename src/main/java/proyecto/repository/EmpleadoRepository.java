package proyecto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer>{

}
