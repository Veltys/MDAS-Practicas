package mdas.p2.gestorreservamgr;


// import mdas.p2.gestorreservamgr.TipoIncidencia;


/**
 * Clase Incidencia
 * Contiene los datos de una incidencia
 *
 * @author		Rafael Carlos Méndez Rodríguez (i82meror)
 * @date		29/05/2020
 * @version		1.1.1
 */

public class Incidencia {
	private int				_id;
	private int				_idReserva;
	private String			_descripcion;
	private TipoIncidencia	_tipo;


	/**
	 * Constructor de clase
	 * Crea una incidencia a partir de su ID, ID de la reserva asociada, descripción y tipo
	 *
	 * @param		id								int								ID de la incidencia
	 * @param		idReserva						int								ID de la reserva asociada a la incidencia
	 * @param		descripcion						String							Descripción de la incidencia
	 * @param		tipo							TipoIncidencia					Tipo de incidencia
	 */

	public Incidencia(int id, int idReserva, String descripcion, TipoIncidencia tipo) {
		this._id			= id;
		this._idReserva		= idReserva;
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
	 * Observador de la variable privada _idReserva
	 *
	 * @return										int								ID de la reserva asociada a la incidencia
	 */

	public int idReserva() {
		return this._idReserva;
	}


	/**
	 * Observador de la variable privada _descripcion
	 *
	 * @return										String							Descripción de la incidencia
	 */

	public String descripcion() {
		return this._descripcion;
	}


	/**
	 * Observador de la variable privada _tipo
	 *
	 * @return										TipoIncidencia					Tipo de incidencia
	 */

	public TipoIncidencia tipo() {
		return this._tipo;
	}


	/**
	 * Método "mágico" cuando una clase es usada como String
	 *
	 * @return										String							Representación en texto de los datos de la incidencia
	 */

	@Override
	public String toString() {
		return this._id + "," + this._idReserva + "," + this._descripcion + "," + this._tipo.id();
	}
}
