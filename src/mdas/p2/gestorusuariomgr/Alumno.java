package mdas.p2.gestorusuariomgr;


import mdas.p2.gestorusuariomgr.Usuario;


/**
 * Clase Alumno
 * Extiende a la clase Usuario para almacenar datos concretos de un alumno
 *
 * @author		Rafael Carlos Méndez Rodríguez (i82meror)
 * @date		29/05/2020
 * @version		1.3.0
 */


public class Alumno extends Usuario {
	/**
	 * Constructor de clase
	 * Crea un alumno a partir de su ID, nombre y correo
	 *
	 * @param		id								int								Identificador del alumno
	 * @param		nombre							String							Nombre del alumno
	 * @param		correo							String							Correo del alumno
	 */

	public Alumno(int id, String nombre, String correo) {
		super(id, nombre, correo);
	}


	/**
	 * Método para exportar los datos de la clase en formato CSV
	 *
	 * @return										String							Representación en texto CSV de los datos del alumno
	 */

	@Override
	public String toCsv() {
		return "false," + super.toString();
	}


	/**
	 * Método "mágico" cuando una clase es usada como String
	 *
	 * @return										String							Representación en texto de los datos del alumno
	 */

	@Override
	public String toString() {
		return "false," + super.toString();
	}
}
