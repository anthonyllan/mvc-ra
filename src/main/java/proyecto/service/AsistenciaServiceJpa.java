package proyecto.service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import proyecto.model.Asistencia;
import proyecto.model.Horario;
import proyecto.model.Retardos;
import proyecto.repository.AsistenciaRepository;
import proyecto.repository.HorarioRepository;
import proyecto.repository.RetardosRepository;

@Service
@Primary
public class AsistenciaServiceJpa implements AsistenciaService{
	@Autowired
	private AsistenciaRepository aRepo;
	@Autowired
    private HorarioRepository hRepo;
    
    @Autowired
    private RetardosRepository rRepo;

	@Override
	public List<Asistencia> buscarTodos() {
		return aRepo.findAll();
	}

	@Override
	public Asistencia buscarPorId(Integer id) {
		Optional<Asistencia> optional = aRepo.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	@Transactional
    @Override
    public void guardarAsistencia(Asistencia asistencia) {
        // 1. Guardar la asistencia primero
        aRepo.save(asistencia);
        
        // 2. Buscar el horario del empleado
        Horario horario = hRepo.findByEmpleado(asistencia.getEmpleado());
        
        if (horario != null) {
            // 3. Calcular y guardar los retardos
            Retardos retardo = calcularRetardos(asistencia, horario);
            rRepo.save(retardo);
        }
    }
	
	private Retardos calcularRetardos(Asistencia asistencia, Horario horario) {
        Retardos retardo = new Retardos();
        retardo.setFecha(asistencia.getFecha());
        retardo.setAsistencia(asistencia);
        retardo.setHorario(horario);
        
        // Calcular horas trabajadas
        long minutosTrabajados = Duration.between(
            asistencia.getHoraentrada(), 
            asistencia.getHorasalida()
        ).toMinutes();
        
        double horasTrabajadas = minutosTrabajados / 60.0;
        retardo.setHorastrabajadas(formatDecimalToTime(horasTrabajadas));
        
        // Calcular retraso en entrada
        if (asistencia.getHoraentrada().isAfter(horario.getHoraentrada())) {
            long minutosRetraso = Duration.between(
                horario.getHoraentrada(), 
                asistencia.getHoraentrada()
            ).toMinutes();
            retardo.setRetraso(formatDecimalToTime(minutosRetraso / 60.0));
        } else {
            retardo.setRetraso("00:00");
        }
        
        // Calcular horas faltantes
        long minutosEsperados = Duration.between(
            horario.getHoraentrada(), 
            horario.getHorasalida()
        ).toMinutes();
        
        long diferencia = minutosEsperados - minutosTrabajados;
        if (diferencia > 0) {
            retardo.setHorasfaltantes(formatDecimalToTime(diferencia / 60.0));
        } else {
            retardo.setHorasfaltantes("00:00");
        }
        
        return retardo;
    }
    
    private String formatDecimalToTime(double decimalHours) {
        int hours = (int) Math.floor(decimalHours);
        int minutes = (int) Math.round((decimalHours - hours) * 60);
        return String.format("%02d:%02d", hours, minutes);
    }

	@Override
	public void borrarAsistencia(Integer id) {
		aRepo.deleteById(id);
	}

	@Override
	public void EditarAsistencia(Asistencia asis) {
		if (aRepo.existsById(asis.getId())) { // Verifica si el producto existe
	        aRepo.save(asis); // Guarda los cambios
	    }
	}
}
