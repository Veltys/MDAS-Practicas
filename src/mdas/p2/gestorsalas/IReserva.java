package mdas.p2.gestorsalas;

import java.util.ArrayList;

/**
 * Interfaz IReserva
 * Interfaz del componente GestorSalas
 *
 * @author		Javier Ortiz Aragones
 * @date		27/05/2020
 * @version		1.1.0
 */


public interface IReserva {
	public abstract ArrayList<Integer>	buscarReservas(int idAlumno);
	public abstract Boolean				confirmarReserva(int idReserva);
	public abstract Boolean				eliminarReserva(int idReserva);
	public abstract String				mostrarReserva(int idReserva);
}
