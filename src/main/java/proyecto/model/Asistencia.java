package proyecto.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "asistencia")
public class Asistencia {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

	@ManyToOne
    @JoinColumn(name = "idempleado")
    private Empleado empleado;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate fecha = LocalDate.now();
    
    private LocalTime horaentrada;
    private LocalTime horasalida;
    
    @ManyToOne(optional = true) // Esto hace que la relación sea opcional
    @JoinColumn(name = "idpermiso", nullable = true) // Permite valores NULL en la BD
    private Permiso permiso;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHoraentrada() {
		return horaentrada;
	}

	public void setHoraentrada(LocalTime horaentrada) {
		this.horaentrada = horaentrada;
	}

	public LocalTime getHorasalida() {
		return horasalida;
	}

	public void setHorasalida(LocalTime horasalida) {
		this.horasalida = horasalida;
	}

	public Permiso getPermiso() {
		return permiso;
	}

	public void setPermiso(Permiso permiso) {
		this.permiso = permiso;
	}
}