package mdas.p2.gestorreservamgr;


/**
 * enum TipoIncidencia
 * Enumeración de tipos de salas
 *
 * @author		Rafael Carlos Méndez Rodríguez (i82meror)
 * @date		21/05/2020
 * @version		1.0.0
 */

public enum TipoSala {
	AULA(0, "Aula"),
	SUM(1, "Sala de usos múltiples"),
	Laboratorio(2, "Laboratorio"),
	STG(3, "Sala de trabajo en grupo"),
	DESPACHO(4, "Despacho");


	private final int		_id;
	private final String	_descripcion;


	/**
	 * Constructor de enumeración
	 * Crea un tipo de sala a partir de su ID y descripción
	 *
	 * @param		id								int								ID del tipo de sala
	 * @param		descripcion						String							Descripción del tipo de sala
	 */

	TipoSala(int id, String descripcion) {
		this._id			= id;
		this._descripcion	= descripcion;
	}


	/**
	 * Observador de la variable privada _id
	 *
	 * @return										int								ID del tipo de sala
	 */

	public int id() {
		return this._id;
	}


	/**
	 * Observador de la variable privada _descripcion
	 *
	 * @return										String							Descripción del tipo de sala
	 */

	public String descripcion() {
		return this._descripcion;
	}
}
