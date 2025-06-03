package proyecto.model;

import java.util.Date;

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
@Table(name="empleado")
public class Empleado {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nombrecompleto;
	
	@ManyToOne
    @JoinColumn(name = "idpuesto")
    private Puesto puesto;
	
	@ManyToOne
    @JoinColumn(name = "iddepartamento")
    private Departamento departamento;
	
	private String direccion;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date fechacontrato;
	
	private String telefono;
	private String email;
	private String genero;
	
	//Get y Set
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombrecompleto() {
		return nombrecompleto;
	}
	public void setNombrecompleto(String nombrecompleto) {
		this.nombrecompleto = nombrecompleto;
	}
	public Puesto getPuesto() {
		return puesto;
	}
	public void setPuesto(Puesto puesto) {
		this.puesto = puesto;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Date getFechacontrato() {
		return fechacontrato;
	}
	public void setFechacontrato(Date fechacontrato) {
		this.fechacontrato = fechacontrato;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
}
