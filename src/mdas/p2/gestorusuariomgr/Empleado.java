package mdas.p2.gestorusuariomgr;


/**
 * Clase Empleado
 * Extiende a la clase Usuario para almacenar datos concretos de un empleado
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			24/05/2020
 * @version			1.1.0
 */

public class Empleado extends Usuario {

	/**
	 * Constructor de clase
	 * Crea un empleado a partir de su id y su nombre
	 *
	 * @param		id								int								Identificador del empleado
	 * @param		nombre							String							Nombre del empleado
	 * @param		correo							String							Correo del empleado
	 */

	public Empleado(int idEmpleado, String nombre, String correo) {
		super(idEmpleado, nombre, correo);
	}

}
