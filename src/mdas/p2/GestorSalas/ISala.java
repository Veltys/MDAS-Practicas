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


public interface ISala {
	
	public abstract ArrayList<Integer> BuscarSala(int aforo, ArrayList<Integer> id_recursos);
	
	public abstract Boolean ConfirmarRegistro(int id_reserva);
	
	public abstract int ElegirSala(int aforo, ArrayList<Integer> id_salas);
	
	public abstract Boolean ValidarDatos(String nombre, int aforo, int tipo, String ubicacion, ArrayList<Integer> recursos);	

}
