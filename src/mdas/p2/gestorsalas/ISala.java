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
	public abstract ArrayList<Integer>	buscarSala(int aforo, ArrayList<Integer> idRecursos);
	public abstract Boolean				confirmarRegistro(int idSala);
	public abstract int					elegirSala(int aforo, ArrayList<Integer> idSalas);
	public abstract int					validarDatos(String nombre, int aforo, int tipo, String ubicacion, ArrayList<Integer> recursos);
}
