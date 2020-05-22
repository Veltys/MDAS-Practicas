package mdas.p2.gestorreservamgr;


/**
 * Clase SalaYRecurso
 * Almacena los datos de la asociación de un recurso a una sala
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			22/05/2020
 * @version			1.1.0
 */

public class SalaYRecurso {
	private int	_idSala;
	private int	_idRecurso;

	/**
	 * Constructor de clase
	 * Crea una relación entre sala y recurso a partir de su ID de sala, e ID de recuso
	 *
	 * @param		idSala							int								ID de la sala
	 * @param		idRecurso						int								ID del recurso
	 */

	public SalaYRecurso(int idSala, int idRecurso) {
		this._idSala	= idSala;
		this._idRecurso	= idRecurso;
	}


	/**
	 * Observador de la variable privada _idRecurso
	 *
	 * @return										int								ID del recurso
	 */

	public int idRecurso() {
		return this._idRecurso;
	}


	/**
	 * Observador de la variable privada _idSala
	 *
	 * @return										int								ID de la sala
	 */

	public int idSala() {
		return this._idSala;
	}


	/**
	 * Método "mágico" cuando una clase es usada como String
	 *
	 * @return										String							Representación en texto de la relación sala y recurso
	 */

	@Override
	public String toString() {
		return this._idSala + ", " + this._idRecurso;
	}
}
