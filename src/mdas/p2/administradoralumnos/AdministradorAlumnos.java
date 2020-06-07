package mdas.p2.administradoralumnos;


import java.util.ArrayList;

import mdas.p2.gestorreservamgr.ReservaMgr;


/**
 * Clase AdministadorAlumnos
 * Contiene los métodos de AdministradorAlumnos
 *
 * @author		Herminio Rodríguez García (i72rogah)
 * @date		29/05/2020
 * @version		2.2.0
 */

public class AdministradorAlumnos implements IInformarAlumno, IComprobarSancion {
	private ReservaMgr _gr;

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


	public AdministradorAlumnos(String archivoIncidencias, String archivoRecursos, String archivoReservas, String archivoSalas, String archivoSalasYRecursos, String archivoSanciones) {
		this._gr = ReservaMgr.getInstance(archivoIncidencias, archivoRecursos, archivoReservas, archivoSalas, archivoSalasYRecursos, archivoSanciones);
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
	 * Notificador de alumnos sancionados
	 * Genera una notificación texto de notificación para un usuario y la sanción de éste
	 *
	 *@param		idSancion					int									ID de la sanción
	 *
	 *@return									String								Mensaje de notificación
	 */

	@Override
	public String notificarAlumnoSancionado(int idSancion) {
		return this._gr.notificarAlumnoSancionado(idSancion);
	}
}
