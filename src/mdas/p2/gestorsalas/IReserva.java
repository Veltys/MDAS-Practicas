package mdas.p2.gestorsalas;

import java.util.ArrayList;

/**
 * Interfaz IReserva
 * Interfaz del componente GestorSalas
 *
 * @author		Javier Ortiz Aragones
 * @date		28/05/2020
 * @version		1.3.0
 */


public interface IReserva {
	abstract public ArrayList<Integer>	buscarReservas(int idAlumno, boolean todas);
	abstract public Boolean				confirmarReserva(int idReserva);
	abstract public Boolean				eliminarReserva(int idReserva);
	abstract public String				mostrarReserva(int idReserva);
	abstract public int					reanudarReserva(int idUsuario, int idReserva);
	abstract public int					suspenderReserva(int idUsuario, int idReserva);
}
