package mdas.p2.GestorReservaMgr;


import mdas.p2.GestorReservaMgr.TipoIncidencia;


/**
 * Clase Incidencia
 * Contiene los datos de una incidencia
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			18/05/2020
 * @version			0.3.0
 */

public class Incidencia {
	private int				_id;
	private int				_id_reserva;
	private String			_descripcion;
	private TipoIncidencia	_tipo;


	/**
	 * Constructor de clase
	 * Crea una incidencia a partir de su ID, descripción y tipo
	 *
	 * @param		id								int								ID de la incidencia
	 * @param		id_reserva						int								ID de la reserva asociada a la incidencia
	 * @param		descripcion						String							Descripción de la incidencia
	 * @param		tipo							TipoIncidencia					Tipo de incidencia
	 */

	public Incidencia(int id, int id_reserva, String descripcion, TipoIncidencia tipo) {
		this._id			= id;
		this._id_reserva	= id_reserva;
		this._descripcion	= descripcion;
		this._tipo			= tipo;
	}


	/**
	 * Observador de la variable privada _id
	 *
	 * @return										int								ID de la incidencia
	 */

	public int id() {
		return this._id;
	}


	/**
	 * Observador de la variable privada _id_reserva
	 *
	 * @return										int								ID de la reserva asociada a la incidencia
	 */

	public int id_reserva() {
		return this._id_reserva;
	}
}
