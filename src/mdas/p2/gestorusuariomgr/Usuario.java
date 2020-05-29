package mdas.p2.gestorusuariomgr;


/**
 * Clase Usuario
 * Almacena datos de un usuario del sistema
 *
 * @author		Rafael Carlos Méndez Rodríguez (i82meror)
 * @date		29/05/2020
 * @version		1.2.0
 */

public class Usuario {
	private int		_id;
	private String	_correo;
	private String	_nombre;


	/**
	 * Constructor de clase
	 * Crea un usuario a partir de su id y su nombre
	 *
	 * @param		id								int								Identificador del usuario
	 * @param		nombre							String							Nombre del usuario
	 * @param		correo							String							Correo del usuario
	 */

	public Usuario(int id, String nombre, String correo) {
		this._id		= id;
		this._nombre	= nombre;
		this._correo	= correo;
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


	/**
	 * Observador de la variable privada _id
	 *
	 * @return										int								Identificador de usuario
	 */

	public int id() {
		return this._id;
	}


	/**
	 * Observador de la variable privada _nombre
	 *
	 * @return										String							Nombre
	 */

	public String nombre() {
		return this._nombre;
	}


	/**
	 * Modificador de la variable privada _nombre
	 *
	 * @param		nombre							String							Nombre
	 */

	public void nombre(String nombre) {
		this._nombre = nombre;
	}


	/**
	 * Método "mágico" cuando una clase es usada como String
	 *
	 * @return										String							Representación en texto de los datos del usuario
	 */

	@Override
	public String toString() {
		return this._id + "," + this._nombre + "," + this._correo;
	}
}
