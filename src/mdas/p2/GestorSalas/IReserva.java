package mdas.p2.GestorSalas;

import java.util.ArrayList;

/**
 * Interfaz IReserva
 * Interfaz del componente GestorSalas
 *
 * @author			Javier Ortiz Aragones
 * @date			19/05/2020
 * @version			1.0.0
 */


public interface IReserva {

	public abstract ArrayList<Integer> BuscarReservas(int id_alumno);
	
	public abstract Boolean ConfirmarReserva(int id_reserva);
	
	public abstract Boolean EliminarReserva(int id_reserva);
	
}
