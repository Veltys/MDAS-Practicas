package mdas.p2.gestorsalas;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Interfaz IReserva
 * Interfaz del componente GestorSalas
 *
 * @author			Javier Ortiz Aragones
 * @date			24/05/2020
 * @version			1.0.1
 */


public interface ISala {
	public abstract ArrayList<Integer>	buscarSala(int aforo, ArrayList<Integer> idRecursos);
	public abstract Boolean				confirmarRegistro(int idSala);
	public abstract int					elegirSala(int aforo, int idAlumno, String asignatura, int duracion, LocalDateTime fechaYHora, ArrayList<Integer> idSalas);
	public abstract int					validarDatos(String nombre, int aforo, int tipo, String ubicacion, ArrayList<Integer> recursos);
}
