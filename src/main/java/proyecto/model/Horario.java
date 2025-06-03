package proyecto.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "horario")
public class Horario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idempleado")
    private Empleado empleado;

    private LocalTime horaentrada;
    private LocalTime horasalida;

    private String diastrabajar;

    public String getDiastrabajar() {
		return diastrabajar;
	}

	public void setDiastrabajar(String diastrabajar) {
		this.diastrabajar = diastrabajar;
	}

	// Constructores
    public Horario() {
    }

    // Getters y Setters
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

	
}