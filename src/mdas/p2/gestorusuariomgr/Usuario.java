package mdas.p2.gestorusuariomgr;


/**
 * Clase Usuario
 * Almacena datos de un usuario del sistema
 * Implementa la interfaz comparable, para facilitar su ordenación
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			17/05/2020
 * @version			1.0.0
 */

public class Usuario {
	private int		_id;
	private String	_nombre;

/**
 * Constructor por defecto de la clase
 * Crea un usuario sin valores
 * 
 */
	
	
	public Usuario() {
		this._id = -1;
		this._nombre = null;
	}
	
	/**
	 * Constructor de clase
	 * Crea un usuario a partir de su id y su nombre
	 *
	 * @param		id								int								Identificador del usuario
	 * @param		nombre							String							Nombre del usuario
	 */

	public Usuario(int id, String nombre) {
		this._id		= id;
		this._nombre	= nombre;
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
}
