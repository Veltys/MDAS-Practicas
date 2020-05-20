package mdas.p2.gestorusuariomgr;


import mdas.p2.gestorusuariomgr.Usuario;


/**
 * Clase Alumno
 * Extiende a la clase Usuario para almacenar datos concretos de un alumno
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			17/05/2020
 * @version			1.0.0
 */


public class Alumno extends Usuario {
	private String	_correo;

	
	/**
	 * Constructor por defecto de la clase
	 * Crea un usuario sin valores
	 * 
	 */
	
	public Alumno() {
		super();
		
		this._correo = null;
	}
	
	/**
	 * Constructor de clase
	 * Crea un alumno a partir de su ID, nombre y correo
	 *
	 * @param		id								int								Identificador del alumno
	 * @param		nombre							String							Nombre del alumno
	 * @param		correo							String							Correo del alumno
	 */

	public Alumno(int id, String nombre, String correo) {
		super(id, nombre);

		this._correo = correo;
	}


	/**
	 * Observador de la variable privada _correo
	 *
	 * @return										String							Correo del alumno
	 */

	public String correo() {
		return this._correo;
	}


	/**
	 * Modificador de la variable privada _correo
	 *
	 * @param		correo							String							Correo del alumno
	 */

	public void correo(String correo) {
		this._correo = correo;
	}
}
