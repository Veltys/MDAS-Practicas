package mdas.p2.gestorreservamgr;


import java.time.LocalDateTime;


/**
 * Clase Reserva
 * Almacena los datos de una reserva de una sala
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			20/05/2020
 * @version			1.0.0
 */

public class Reserva {
	private boolean			_estado;
	private int				_id;
	private int				_idAlumno;
	private int				_alumnos;
	private int				_duracion;
	private String			_asignatura;
	private LocalDateTime	_fechaYHora;


	/**
	 * Constructor de clase
	 * Crea una reserva a partir de su ID, ID de alumno, alumnos, asignatura, duración, estado y fecha y hora
	 *
	 * @param		id								int								ID de la reserva
	 * @param		idAlumno						int								ID del alumno asociado a la reserva
	 * @param		alumnos							int								Número de alumnos que disfrutarán la reserva
	 * @param		asignatura						String							Asignatura para la que se ha realizado la reserva
	 * @param		duracion						int								Duración (en horas) de la reserva
	 * @param		fechaYHora						LocalDateTime					Fecha y hora de la reserva
	 */


	public Reserva(int id, int idAlumno, int alumnos, String asignatura, int duracion, LocalDateTime fechaYHora) {
		this._id			= id;
		this._idAlumno		= idAlumno;
		this._alumnos		= alumnos;
		this._asignatura	= asignatura;
		this._duracion		= duracion;
		this._estado		= false;
		this._fechaYHora	= fechaYHora;
	}


	/**
	 * Constructor de clase
	 * Crea una reserva a partir de su ID, ID de alumno, alumnos, asignatura, duración, estado y fecha y hora
	 *
	 * @param		id								int								ID de la reserva
	 * @param		idAlumno						int								ID del alumno asociado a la reserva
	 * @param		alumnos							int								Número de alumnos que disfrutarán la reserva
	 * @param		asignatura						String							Asignatura para la que se ha realizado la reserva
	 * @param		duracion						int								Duración (en horas) de la reserva
	 * @param		estado							boolean							Estado de la reserva
	 * @param		fechaYHora						LocalDateTime					Fecha y hora de la reserva
	 */


	public Reserva(int id, int idAlumno, int alumnos, String asignatura, int duracion, boolean estado, LocalDateTime fechaYHora) {
		this._id			= id;
		this._idAlumno		= idAlumno;
		this._alumnos		= alumnos;
		this._asignatura	= asignatura;
		this._duracion		= duracion;
		this._estado		= estado;
		this._fechaYHora	= fechaYHora;
	}

	/**
	 * Observador de la variable privada _alumnos
	 *
	 * @return										int								Número de alumnos que disfrutarán la reserva
	 */

	public int alumnos() {
		return this._alumnos;
	}


	/**
	 * Observador de la variable privada _asignatura
	 *
	 * @return										String							Asignatura para la que se ha realizado la reserva
	 */

	public String asignatura() {
		return this._asignatura;
	}


	/**
	 * Observador de la variable privada _duracion
	 *
	 * @return										int								Duración (en horas) de la reserva
	 */

	public int duracion() {
		return this._duracion;
	}


	/**
	 * Observador de la variable privada _estado
	 *
	 * @return										boolean							Estado de la reserva
	 */

	public boolean estado() {
		return this._estado;
	}


	/**
	 * Modificador de la variable privada _estado
	 *
	 * @param	estado								boolean							Estado de la reserva
	 */

	public void estado(boolean estado) {
		this._estado = estado;
	}


	/**
	 * Observador de la variable privada _fechaYHora
	 *
	 * @return										LocalDateTime					Fecha y hora de la reserva
	 */

	public LocalDateTime fechaYHora() {
		return this._fechaYHora;
	}


	/**
	 * Observador de la variable privada _id
	 *
	 * @return										int								ID de la reserva
	 */

	public Integer id() {
		return this._id;
	}


	/**
	 * Observador de la variable privada _idAlumno
	 *
	 * @return										int								ID del alumno asociado a la reserva
	 */

	public int idAlumno() {
		return this._idAlumno;
	}
}
