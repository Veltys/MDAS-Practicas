package mdas.p2.gestorsalas;


import java.time.LocalDateTime;
import java.util.ArrayList;

import mdas.p2.gestorreservamgr.IReservaMgt;
import mdas.p2.gestorreservamgr.ReservaMgr;


/**
 * Clase GestorSalas
 *
 * @author		Javier Ortiz Aragones
 * @date		29/05/2020
 * @version		1.10.0
 */

public class GestorSalas implements IReserva, ISala {

	private ReservaMgr _reservaMgr;

	/**
	 * Constructor de clase
	 * Inicializa el objeto de la clase ReservaMgr
	 *
	 * @param		archivoIncidencias				String							Ruta del archivo donde incidencias
	 * @param		archivoRecursos					String							Ruta del archivo donde recursos
	 * @param		archivoReservas					String							Ruta del archivo donde reservas
	 * @param		archivoSalas					String							Ruta del archivo donde salas
	 * @param		archivoSalasYRecursos			String							Ruta del archivo donde salas y recursos
	 * @param		archivoSanciones				String							Ruta del archivo donde sanciones
	 */

	public GestorSalas(String archivoIncidencias, String archivoRecursos, String archivoReservas, String archivoSalas, String archivoSalasYRecursos, String archivoSanciones) {
		this._reservaMgr = ReservaMgr.getInstance(archivoIncidencias, archivoRecursos, archivoReservas, archivoSalas, archivoSalasYRecursos, archivoSanciones);
	}


	/**
	 * Buscador de reservas por alumno
	 * Busca las reservas a través de un ID de alumno
	 *
	 * @param		idAlumno						int								ID del alumno para buscar sus reservas
	 * @param		todas							boolean							Buscar todas las reservas o sólo las futuras
	 *
	 * @return										int[]							Vector que contiene los IDs de las reservas del alumno
	 */

	@Override
	public ArrayList<Integer> buscarReservas(int idAlumno, boolean todas) {
		return this._reservaMgr.buscarReservas(idAlumno, todas);
	}

	/**
	 * Confirmar la reserva realizada por el alumno
	 *
	 * @param		idReserva						int								ID de la reserva para buscar sus incidencias
	 *
	 * @return										boolean							True en caso de que se haya realizado con exito, false en el caso contrario
	 */

	@Override
	public Boolean confirmarReserva(int idReserva) {
		return this._reservaMgr.confirmarReserva(idReserva);
	}

	/**
	 * Eliminar una reserva a partir de su ID
	 *
	 * @param		idReserva						int								ID de la reserva a eliminar
	 *
	 * @return										boolean							True en caso de que se haya realizado con exito, false en el caso contrario
	 */

	@Override
	public Boolean eliminarReserva(int idReserva) {
		return this._reservaMgr.eliminarReserva(idReserva);
	}


	/**
	 * Buscador de salas posibles para los datos aportados por el alumno
	 *
	 * @param		aforo							int								Aforo minimo que solicita el alumno
	 * @param		idsRecursos						int[]							Vector con los IDs de los recursos que necesita el alumno
	 *
	 * @return int[] Vector que contiene los IDs de las posibles salas
	 */

	@Override
	public ArrayList<Integer> buscarSala(int aforo, ArrayList<Integer> idsRecursos) {
		return this._reservaMgr.buscarSala(aforo, idsRecursos);
	}


	/**
	 * Buscador de la sala optima entre las posibles
	 *
	 * @param		aforo							int								Aforo minimo que solicita el alumno
	 * @param		idSalas							int[]							Vector con los IDs de las salas posibles
	 *
	 * @return										int								ID de la sala óptima
	 */

	@Override
	public int elegirSala(int aforo, int idAlumno, String asignatura, int duracion, LocalDateTime fechaYHora, ArrayList<Integer> idSalas) {
		int		maxAforo		= Integer.MAX_VALUE;
		int		nuevoAforo;
		int		idSalaElegida	= -1;

		for(int sala: idSalas) {
			if(aforo == (nuevoAforo = this._reservaMgr.obtenerAforoSala(sala))) {
				idSalaElegida = sala;

				break;
			}


			else if((maxAforo > nuevoAforo) && this._reservaMgr.salaLibre(idAlumno, sala, fechaYHora, duracion)) {
				maxAforo = nuevoAforo;

				idSalaElegida = sala;
			}
		}

		if(idSalaElegida != -1) {
			return this._reservaMgr.preReservarSala(idAlumno, idSalaElegida, aforo, asignatura, duracion, fechaYHora);
		}
		else {
			return -1;
		}
	}


	/**
	 * Observador en texto de un recurso
	 * Recoge los datos de un recurso y los convierte en un String en texto apto para mostrárselo al usuario
	 *
	 * @param		idRecurso						int								ID del recurso a mostrar
	 *
	 * @return										String							Texto con los datos del recurso ("" si no encontrada)
	 */

	@Override
	public String mostrarRecurso(int idRecurso) {
		return this._reservaMgr.mostrarRecurso(idRecurso);
	}


	/**
	 * Observador en texto de un tipo de sala
	 * Recoge los datos de un tipo de sala y los convierte en un String en texto apto para mostrárselo al usuario
	 *
	 * @param		idTipoDeSala					int								ID del tipo de sala a mostrar
	 *
	 * @return										String							Texto con los datos del tipo de sala ("" si no encontrado)
	 */

	@Override
	public String mostrarTipoDeSala(int idTipoDeSala) {
		return IReservaMgt.mostrarTipoDeSala(idTipoDeSala);
	}


	/**
	 * Observador en texto de una reserva
	 * Recoge los datos de una reserva y los convierte en un String en texto apto para mostrárselo al usuario
	 *
	 * @param		idReserva						int								ID de la reserva a mostrar
	 *
	 * @return										String							Texto con los datos de la reserva ("" si no encontrada)
	 */

	@Override
	public String mostrarReserva(int idReserva) {
		return this._reservaMgr.mostrarReserva(idReserva);
	}


	/**
	 * Observador de la lista de reservas
	 * Devuelve los IDs de todas o sólo de las ya pasadas reservas de la lista
	 *
	 * @param		todas							boolean							Devolver todas las reservas o sólo las pasadas
	 *
	 * @return										int								Lista de reservas
	 */

	@Override
	public ArrayList<Integer> obtenerReservas(boolean todas) {
		return this._reservaMgr.obtenerReservas(todas);
	}


	/**
	 * Observador de la lista de recursos
	 *
	 * @return										ArrayList&lt;Integer&gt;		Lista de IDs de los recursos cargada en el gestor
	 */

	@Override
	public ArrayList<Integer> obtenerRecursos() {
		return this._reservaMgr.obtenerRecursos();
	}


	/**
	 * Observador de los tipos de sala
	 *
	 * @return										ArrayList&lt;Integer&gt;		Lista de IDs de los tipos de sala
	 */

	@Override
	public ArrayList<Integer> obtenerTiposDeSala() {
		return IReservaMgt.obtenerTiposDeSala();
	}


	/**
	 * Método para reanudar una reserva en suspensión
	 * Si la modificación de una reserva ha sido cancelada, es necesario revertirla a su estado normal
	 *
	 * @param		idUsuario						int								ID del usuario que lo solicita
	 * @param		idReserva						int								ID de la reserva a reanudar
	 *
	 * @return										int								ID de la reserva a reanudada (-1 si no encontrada, -2 si la reserva no pertenece al usuario solicitado)
	 */

	@Override
	public int reanudarReserva(int idUsuario, int idReserva) {
		return this._reservaMgr.reanudarReserva(idUsuario, idReserva);
	}


	/**
	 * Método para poner una reserva en suspensión
	 * Cuando una reserva está siendo modificada, es necesario dejar el "hueco" de la misma libre, para poder crear otra en su lugar
	 *
	 * @param		idUsuario						int								ID del usuario que lo solicita
	 * @param		idReserva						int								ID de la reserva a suspender
	 *
	 * @return										int								ID de la reserva a suspendida (-1 si no encontrada, -2 si la reserva no pertenece al usuario solicitado)
	 */

	@Override
	public int suspenderReserva(int idUsuario, int idReserva) {
		return this._reservaMgr.suspenderReserva(idUsuario, idReserva);
	}


	/**
	 * Validador de los datos introducidos por el empleado para la nueva sala
	 *
	 * @param		nombre							String							Nombre de la nueva sala
	 * @param		aforo							int								Aforo máximo de la nueva sala
	 * @param		tipo							int								Tipo de sala(aula, laboratorio...)
	 * @param		ubicacion						String							Ubicación de la nueva sala
	 * @param		recursos						int[]							ID de los recursos disponibles en la nueva sala
	 *
	 * @return										int								Id de la sala preRegistrada
	 */

	@Override
	public int validarDatos(String nombre, int aforo, int tipo, String ubicacion, ArrayList<Integer> recursos) {
		if(aforo < 1) {
			return -1;
		}
		else {
			for(int tipoSala: IReservaMgt.obtenerTiposDeSala()) {
				if(tipo == tipoSala) {
					return this._reservaMgr.registrarSala(nombre, aforo, tipo, ubicacion, recursos);
				}
			}
			return -1;
		}
	}
}
