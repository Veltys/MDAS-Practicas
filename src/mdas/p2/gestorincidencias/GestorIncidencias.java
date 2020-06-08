package mdas.p2.gestorincidencias;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
 * @date		07/06/2020
 * @version		1.0.2
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
		String mensaje;
		String nombre	= this._usuarioMgr.nombre(idAlumno);

		if(nombre != null) {
			mensaje	= "Estimado " + nombre + ":" + System.getProperty("line.separator") +
					"Lamentamos informarle de que ha sido sancionado en la plataforma de reserva de salas de la UCO." + System.getProperty("line.separator") +
					"La incidencia ocurrida ha sido: \"" + descripcion + "\"." + System.getProperty("line.separator") +
					"Eso conllevará la siguiente sanción: \"" + IReservaMgt.describirSancion(codigoSancion) + "\"." + System.getProperty("line.separator") +
					"La sanción empezará el día " + fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " y terminará el día " + fecha.plusDays(duracion).format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
					", es decir, será de " + duracion + " dia" + ((duracion != 1) ? ("s") : ("")) + " de duración." + System.getProperty("line.separator") +
					"Reciba un cordial saludo.";

			return this._usuarioMgr.enviarNotificacion(idAlumno, mensaje);
		}
		else {
			return false;
		}
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
	 * @param		tipo							int								Tipo de incidencia
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
	 * @param		codigoSancion					int								Código de la sanción
	 * @param		fecha							LocalDate						Fecha de la sanción
	 * @param		duracion						int								Duración (en días) de la sanción
	 *
	 * @return										int								ID de la sanción
	 */

	@Override
	public int sancionarAlumno(int idIncidencia, int codigoSancion, LocalDate fecha, int duracion) {
		return this._reservaMgr.sancionarAlumno(idIncidencia, codigoSancion, fecha, duracion);
	}



}
