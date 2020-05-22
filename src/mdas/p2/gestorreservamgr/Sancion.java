package mdas.p2.gestorreservamgr;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Clase Sancion
 * Almacena los datos de una sanción asociada a una reserva y a un alumno
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			22/05/2020
 * @version			1.1.0
 */

public class Sancion {
	private int				_id;
	private int				_idIncidencia;
	private int				_codigo;
	private int				_duracion;
	private LocalDate		_fecha;


	/**
	 * Constructor de clase
	 * Crea una sanción a partir de su ID, ID de incidencia, código, duración y fecha
	 *
	 * @param		id								int								ID de la sanción
	 * @param		idIncidencia					int								ID de la incidencia asociada a la sanción
	 * @param		codigo							int								Código de la sanción
	 * @param		duracion						int								Duración (en días) de la sanción
	 * @param		fecha							LocalDate						Fecha de la sanción
	 */

	public Sancion(int id, int idIncidencia, int codigo, int duracion, LocalDate fecha) {
		this._id			= id;
		this._idIncidencia	= idIncidencia;
		this._codigo		= codigo;
		this._duracion		= duracion;
		this._fecha			= fecha;
	}


	/**
	 * Observador de la variable privada _codigo
	 *
	 * @return										int								Código de la sanción
	 */

	public int codigo() {
		return this._codigo;
	}


	/**
	 * Observador de la variable privada _duracion
	 *
	 * @return										int								Duración (en días) de la sanción
	 */

	public int duracion() {
		return this._duracion;
	}


	/**
	 * Observador de la variable privada _fecha
	 *
	 * @return										LocalDate						Fecha de la sanción
	 */

	public LocalDate fecha() {
		return this._fecha;
	}


	/**
	 * Observador de la variable privada _id
	 *
	 * @return										int								ID de la sanción
	 */

	public int id() {
		return this._id;
	}


	/**
	 * Observador de la variable privada _idIncidencia
	 *
	 * @return										int								ID de la incidencia asociada a la incidencia
	 */

	public int idIncidencia() {
		return this._idIncidencia;
	}


	/**
	 * Método "mágico" cuando una clase es usada como String
	 *
	 * @return										String							Representación en texto de los datos de la sancion
	 */

	@Override
	public String toString() {
		return this._id + ", " + this._idIncidencia + ", " + this._codigo + ", " + this._duracion + ", " + this._fecha.format(DateTimeFormatter.ISO_LOCAL_DATE);
	}
}
