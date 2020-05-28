package mdas.p2.gestorsalas;


import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Interfaz IReserva
 * Interfaz del componente GestorSalas
 *
 * @author		Javier Ortiz Aragones
 * @date		28/05/2020
 * @version		1.2.0
 */


public interface ISala {
	abstract public ArrayList<Integer>	buscarSala(int aforo, ArrayList<Integer> idRecursos);
	abstract public Boolean				confirmarRegistro(int idSala);
	abstract public int					elegirSala(int aforo, int idAlumno, String asignatura, int duracion, LocalDateTime fechaYHora, ArrayList<Integer> idSalas);
	abstract public String				mostrarRecurso(int idRecurso);
	abstract public ArrayList<Integer>	obtenerRecursos();
	abstract public int					validarDatos(String nombre, int aforo, int tipo, String ubicacion, ArrayList<Integer> recursos);
}
