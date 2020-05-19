package mdas.p2.gestorsalas;

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
	public abstract ArrayList<Integer>	BuscarSala(int aforo, ArrayList<Integer> idRecursos);
	public abstract Boolean				ConfirmarRegistro(int idSala);
	public abstract int					ElegirSala(int aforo, ArrayList<Integer> idSalas);
	public abstract Boolean				ValidarDatos(String nombre, int aforo, int tipo, String ubicacion, ArrayList<Integer> recursos);
}
