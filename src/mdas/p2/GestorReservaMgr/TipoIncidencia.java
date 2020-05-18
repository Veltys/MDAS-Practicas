package mdas.p2.GestorReservaMgr;


/**
 * enum TipoIncidencia
 * Enumeración de tipos de incidencias
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			18/05/2020
 * @version			1.0.0
 */

public enum TipoIncidencia {
	SIN_IMPORTANCIA(0, "Sin importancia"),
	LEVE(1, "Leve"),
	MEDIA(2, "Media"),
	GRAVE(3, "Grave"),
	MUY_GRAVE(4, "Muy grave");


	private final int		_id;
	private final String	_descripcion;


	/**
	 * Constructor de enumeración
	 * Crea un tipo de incidencia a partir de su ID y descripción
	 *
	 * @param		id								int								ID del tipo de incidencia
	 * @param		descripcion						String							Descripción del tipo de incidencia
	 */

	TipoIncidencia(int id, String descripcion) {
		this._id			= id;
		this._descripcion	= descripcion;
	}


	/**
	 * Observador de la variable privada _id
	 *
	 * @return										int								ID del tipo de incidencia
	 */

	public int id() {
		return this._id;
	}


	/**
	 * Observador de la variable privada _id
	 *
	 * @return										String							Descripción del tipo de incidencia
	 */

	public String descripcion() {
		return this._descripcion;
	}
}
