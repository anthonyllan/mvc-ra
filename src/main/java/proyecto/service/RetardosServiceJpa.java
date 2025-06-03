package proyecto.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import proyecto.model.Retardos;
import proyecto.repository.RetardosRepository;

@Service
@Primary
public class RetardosServiceJpa implements RetardosService{
	@Autowired
    private RetardosRepository reRepo;

	@Override
	public List<Retardos> buscarTodos() {
		return reRepo.findAll();
	}

	public static String formatDecimalToTime(Double decimalHours) {
	    if (decimalHours == null) return null;
	    int hours = (int) Math.floor(decimalHours);
	    int minutes = (int) Math.round((decimalHours - hours) * 60);
	    return String.format("%02d:%02d", hours, minutes);
	}

	// Método auxiliar para obtener datos específicos si es necesario
	public Map<String, Object> obtenerDatosRetardo(Retardos retardo) {
	    Map<String, Object> datos = new HashMap<>();
	    datos.put("id", retardo.getId());
	    datos.put("fecha", retardo.getFecha());
	    datos.put("horastrabajadas", retardo.getHorastrabajadas());
	    datos.put("horasfaltantes", retardo.getHorasfaltantes());
	    datos.put("retraso", retardo.getRetraso());
	    
	    if(retardo.getAsistencia() != null) {
	        datos.put("idAsistencia", retardo.getAsistencia().getId());
	    }
	    
	    if(retardo.getHorario() != null) {
	        datos.put("idHorario", retardo.getHorario().getId());
	    }
	    
	    return datos;
	}

}
