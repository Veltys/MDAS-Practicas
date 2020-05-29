package mdas.p2.gestorincidencias;


import java.time.LocalDate;


/**
 * Interfaz ISancion
 * Interfaz del componente GestorIncidencias
 *
 * @author		Rafael Carlos Méndez Rodríguez
 * @date		29/05/2020
 * @version		1.0.0
 */


public interface ISancion {
	abstract public boolean				enviarNotificacion(int idAlumno, String descipcion, int codigoSancion, LocalDate fecha, int duracion);
	abstract public int					sancionarAlumno(int idIncidencia, int idAlumno, int codigoSancion, LocalDate fecha, int duracion);
}
