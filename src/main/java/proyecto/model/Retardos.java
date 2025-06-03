package proyecto.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "horarioasistencia")
public class Retardos {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate fecha;
    private String horasfaltantes;
    private String horastrabajadas;
    private String retraso;

    @ManyToOne
    @JoinColumn(name = "idasistencia", nullable = false)
    private Asistencia asistencia;

    @ManyToOne
    @JoinColumn(name = "idhorario", nullable = false)
    private Horario horario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public String getHorasfaltantes() {
		return horasfaltantes;
	}

	public void setHorasfaltantes(String horasfaltantes) {
		this.horasfaltantes = horasfaltantes;
	}

	public String getHorastrabajadas() {
		return horastrabajadas;
	}

	public void setHorastrabajadas(String horastrabajadas) {
		this.horastrabajadas = horastrabajadas;
	}

	public String getRetraso() {
		return retraso;
	}

	public void setRetraso(String retraso) {
		this.retraso = retraso;
	}

	public Asistencia getAsistencia() {
		return asistencia;
	}

	public void setAsistencia(Asistencia asistencia) {
		this.asistencia = asistencia;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}
}
