package mdas.p2.administradoralumnos;


import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import mdas.p2.gestorreservamgr.IReservaMgt;
import mdas.p2.gestorreservamgr.ReservaMgr;
import mdas.p2.gestorreservamgr.Sancion;
import mdas.p2.gestorusuariomgr.UsuarioMgr;


/**
 * Clase AdministadorAlumnos
 * Contiene los métodos de AdministradorAlumnos
 *
 * @author		Herminio Rodríguez García (i72rogah)
 * @date		28/05/2020
 * @version		2.1.0
 */

public class AdministradorAlumnos implements IInformarAlumno, IComprobarSancion {
	private ReservaMgr _gr;
	private UsuarioMgr _gu;

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
	 * @param		archivoUsuarios					String							Ruta del archivo donde se encuentran los usuarios
	 */


	public AdministradorAlumnos(String archivoIncidencias, String archivoRecursos, String archivoReservas, String archivoSalas, String archivoSalasYRecursos, String archivoSanciones, String archivoUsuarios) {
		this._gr = ReservaMgr.getInstance(archivoIncidencias, archivoRecursos, archivoReservas, archivoSalas, archivoSalasYRecursos, archivoSanciones);
		this._gu = UsuarioMgr.getInstance(archivoUsuarios);
	}


	/**
	 * Método comprobador de sanciones
	 * Comprueba una sancion a traves del ID de un alumno
	 *

	 * @param		idAlumno						int								Id del alumno supuestamente sancionado
	 *
	 * @return		sancion							int								ID de la sanción
	 */

	@Override
	public int comprobarSancion(int idAlumno) {
		int					sancion		= -1;
		ArrayList<Integer>	reservas;
		ArrayList<Integer>	incidencias;

		if((reservas = this._gr.buscarReservas(idAlumno, true)) != null) {
			for(int reserva: reservas) {
				incidencias = this._gr.buscarIncidencias(reserva);

				if(incidencias != null) {
					for(int incidencia: incidencias) {
						sancion = this._gr.buscarSancion(incidencia);

						if(sancion != -1) {
							break;
						}
					}

					if(sancion != -1) {
						break;
					}
				}
			}
		}

		return sancion;
	}


	/**
	 * Método descriptor de sanciones
	 * Devuelve la descripción de una sanción a traves del codigo de esta
	 *
	 * @param		codigoSancion						int							Código de la sancion
	 */

	@Override
	public String describirSancion(int codigoSancion) {
		return IReservaMgt.describirSancion(codigoSancion);
	}


	/**
	 * Notificador de alumnos sancionados
	 * Genera una notificación texto de notificación para un usuario y la sanción de éste
	 *
	 *@param		idAlumno					int									ID del alumno
	 *@param		idSancion					int									ID de la sanción
	 *
	 *@return									String								Mensaje de notificación
	 */

	@Override
	public String notificarAlumnoSancionado(int idAlumno, int idSancion) {
		Sancion s = this._gr.obtenerSancion(idSancion);

		return "Lo sentimos, el usuario " + this._gu.nombre(idAlumno) + " está sancionado hasta el día " + s.fecha().plusDays(s.duracion()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}
}
