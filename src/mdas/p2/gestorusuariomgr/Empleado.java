package mdas.p2.gestorusuariomgr;


import mdas.p2.gestorusuariomgr.Usuario;


/**
 * Clase Empleado
 * Extiende a la clase Usuario para almacenar datos concretos de un empleado
 *
 * @author		Rafael Carlos Méndez Rodríguez (i82meror)
 * @date		29/05/2020
 * @version		1.3.0
 */

public class Empleado extends Usuario {

	/**
	 * Constructor de clase
	 * Crea un empleado a partir de su id y su nombre
	 *
	 * @param		idEmpleado						int								Identificador del empleado
	 * @param		nombre							String							Nombre del empleado
	 * @param		correo							String							Correo del empleado
	 */

	public Empleado(int idEmpleado, String nombre, String correo) {
		super(idEmpleado, nombre, correo);
	}


	/**
	 * Método para exportar los datos de la clase en formato CSV
	 *
	 * @return										String							Representación en texto CSV de los datos del empleado
	 */

	@Override
	public String toCsv() {
		return "true," + super.toString();
	}


	/**
	 * Método "mágico" cuando una clase es usada como String
	 *
	 * @return										String							Representación en texto de los datos del empleado
	 */

	@Override
	public String toString() {
		return "true," + super.toString();
	}
}
