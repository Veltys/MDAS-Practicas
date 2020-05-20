package mdas.p2.gestorreservamgr;


import mdas.p2.gestorreservamgr.Sancion;
import mdas.p2.gestorreservamgr.TipoIncidencia;


/**
 * Clase Incidencia
 * Contiene los datos de una incidencia
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			20/05/2020
 * @version			0.4.0
 */

public class Incidencia {
	private int				_id;
	private int				_idReserva;
	private String			_descripcion;
	private Sancion			_sancion;
	private TipoIncidencia	_tipo;


	/**
	 * Constructor de clase
	 * Crea una incidencia a partir de su ID, descripción y tipo
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
		this._sancion		= null;
		this._tipo			= tipo;
	}


	/**
	 * Constructor de clase
	 * Crea una incidencia a partir de su ID, descripción, sanción y tipo
	 *
	 * @param		id								int								ID de la incidencia
	 * @param		idReserva						int								ID de la reserva asociada a la incidencia
	 * @param		descripcion						String							Descripción de la incidencia
	 * @param		sancion							Sancion							Sanción asociada a la incidencia
	 * @param		tipo							TipoIncidencia					Tipo de incidencia
	 */

	public Incidencia(int id, int idReserva, String descripcion, Sancion sancion, TipoIncidencia tipo) {
		this._id			= id;
		this._idReserva		= idReserva;
		this._descripcion	= descripcion;
		this._sancion		= sancion;
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
	 * Observador del método id() de la variable privada _sancion
	 *
	 * @return										int								ID de la sanción (-1 si no hay sanción)
	 */

	public int idSancion() {
		if(this._sancion == null) {
			return -1;
		}
		else {
			return this._sancion.id();
		}
	}
}
