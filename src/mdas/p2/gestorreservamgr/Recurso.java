package mdas.p2.gestorreservamgr;


/**
 * Clase Recurso
 * Almacena los datos de un recurso asociado
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			21/05/2020
 * @version			1.0.0
 */

public class Recurso {
	private int		_id;
	private String	_descripcion;
	private String	_nombre;


	/**
	 * Constructor de clase
	 * Crea un recurso a partir de su ID, descripción y nombre
	 *
	 * @param		id								int								ID del recurso
	 * @param		descripcion						String							Descripción del recurso
	 * @param		nombre							String							Nombre del recurso
	 */

	public Recurso(int id, String descripcion, String nombre) {
		this._id			= id;
		this._descripcion	= descripcion;
		this._nombre		= nombre;
	}


	/**
	 * Observador de la variable privada _id
	 *
	 * @return										int								ID del recurso
	 */

	public int id() {
		return this._id;
	}


	/**
	 * Observador de la variable privada _descripcion
	 *
	 * @return										int								Descripción del recurso
	 */

	public String descripcion() {
		return this._descripcion;
	}


	/**
	 * Observador de la variable privada _nombre
	 *
	 * @return										int								Nombre del recurso
	 */

	public String nombre() {
		return this._nombre;
	}
}
