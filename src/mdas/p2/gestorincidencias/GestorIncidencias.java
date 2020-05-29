package mdas.p2.gestorincidencias;


import java.time.LocalDate;
import java.util.ArrayList;

import mdas.p2.gestorincidencias.IIncidencia;
import mdas.p2.gestorincidencias.ISancion;
import mdas.p2.gestorreservamgr.IReservaMgt;
import mdas.p2.gestorreservamgr.ReservaMgr;
import mdas.p2.gestorusuariomgr.UsuarioMgr;


/**
 * Clase GestorIncidencias
 *
 * @author		Rafael Carlos Méndez Rodríguez
 * @date		29/05/2020
 * @version		1.0.0
 */

public class GestorIncidencias implements IIncidencia, ISancion {
	private ReservaMgr _reservaMgr;
	private UsuarioMgr _usuarioMgr;

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
	 * @param		archivoUsuarios					String							Ruta del archivo donde usuarios
	 */

	public GestorIncidencias(String archivoIncidencias, String archivoRecursos, String archivoReservas, String archivoSalas, String archivoSalasYRecursos, String archivoSanciones, String archivoUsuarios) {
		this._reservaMgr = ReservaMgr.getInstance(archivoIncidencias, archivoRecursos, archivoReservas, archivoSalas, archivoSalasYRecursos, archivoSanciones);
		this._usuarioMgr = UsuarioMgr.getInstance(archivoUsuarios);
	}


	/**
	 * Notificador de alumnos sancionados
	 * Notifica de la sanción al alumno sancionado
	 *
	 * @param		idAlumno						int								ID del alumno sancionado
	 * @param		descripcion						String							Descripción de la incidencia
	 * @param		codigoSancion					int								Código de la sanción
	 * @param		fecha							LocalDate						Fecha de la sanción
	 * @param		duracion						int								Duración (en días) de la sanción
	 *
	 * @return										boolean							Resultado de la operación
	 */


	@Override
	public boolean enviarNotificacion(int idAlumno, String descripcion, int codigoSancion, LocalDate fecha, int duracion) {
		String mensaje = "";

		// TODO: Redactar el mensaje

		return this._usuarioMgr.enviarNotificacion(idAlumno, mensaje);
	}


	/**
	 * Observador en texto de un tipo de incidencia
	 * Recoge los datos de un tipo de incidencia y los convierte en un String en texto apto para mostrárselo al usuario
	 *
	 * @param		idTipoDeIncidencia				int								ID del tipo de incidencia a mostrar
	 *
	 * @return										String							Texto con los datos del tipo de incidencia ("" si no encontrado)
	 */

	@Override
	public String mostrarTipoDeIncidencia(int idTipoDeIncidencia) {
		return IReservaMgt.mostrarTipoDeIncidencia(idTipoDeIncidencia);
	}


	/**
	 * Observador de los tipos de incidencia
	 *
	 * @return										ArrayList&lt;Integer&gt;		Lista de IDs de los tipos de incidencia
	 */

	@Override
	public ArrayList<Integer> obtenerTiposDeIncidencia() {
		return IReservaMgt.obtenerTiposDeIncidencia();
	}


	/**
	 * Registrador de incidencias
	 * Crea una nueva incidencia
	 *
	 * @param		idReserva						int								ID de la reserva asociada a la incidencia
	 * @param		descripcion						String							Descripción de la incidencia
	 * @param		tipoIncidencia					int								Tipo de incidencia
	 *
	 * @return										int								ID de la incidencia
	 */

	@Override
	public int registrarIncidencia(int idReserva, String descripcion, int tipo) {
		return this._reservaMgr.registrarIncidencia(idReserva, descripcion, tipo);
	}


	/**
	 * Sancionador de alumnos
	 * Sanciona a un alumno
	 *
	 * @param		idIncidencia					int								ID de la incidencia asociada a la sanción
	 * @param		idAlumno						int								ID del alumno sancionado
	 * @param		codigoSancion					int								Código de la sanción
	 * @param		fecha							LocalDate						Fecha de la sanción
	 * @param		duracion						int								Duración (en días) de la sanción
	 *
	 * @return										int								ID de la sanción
	 */

	@Override
	public int sancionarAlumno(int idIncidencia, int idAlumno, int codigoSancion, LocalDate fecha, int duracion) {
		return this._reservaMgr.sancionarAlumno(idIncidencia, idAlumno, codigoSancion, fecha, duracion);
	}



}
