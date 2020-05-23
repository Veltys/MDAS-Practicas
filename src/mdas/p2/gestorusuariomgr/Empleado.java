package mdas.p2.gestorusuariomgr;


/**
 * Clase Empleado
 * Extiende a la clase Usuario para almacenar datos concretos de un empleado
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			23/05/2020
 * @version			1.0.0
 */

public class Empleado extends Usuario {

	/**
	 * Constructor de clase
	 * Crea un empleado a partir de su id y su nombre
	 *
	 * @param		id								int								Identificador del usuario
	 * @param		nombre							String							Nombre del usuario
	 */

	public Empleado(int idEmpleado, String nombre) {
		super(idEmpleado, nombre);
	}

}
