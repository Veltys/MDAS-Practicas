package mdas.p2.administradoralumnos;


import java.util.ArrayList;

import mdas.p2.gestorreservamgr.IReservaMgt;
import mdas.p2.gestorreservamgr.ReservaMgr;


/**
 * Clase AdministadorAlumnos
 * Contiene los métodos de AdministradorAlumnos
 *
 * @author			Herminio Rodríguez García (i72rogah)
 * @date			24/05/2020
 * @version			1.3.0
 */

public class AdministradorAlumnos implements IInformarAlumno, IComprobarSancion {
	private ReservaMgr _gestorReservas;

	/**
	 * Constructor de clase
	 * Inicializa el objeto de la clase ReservaMgr
	 */

	AdministradorAlumnos(String archivoIncidencias, String archivoRecursos, String archivoReservas, String archivoSalas, String archivoSalasYRecursos, String archivoSanciones) {
		this._gestorReservas = ReservaMgr.getInstance(archivoIncidencias, archivoRecursos, archivoReservas, archivoSalas, archivoSalasYRecursos, archivoSanciones);
	}
	/**
	 * Método de una interfaz
	 * Recibe un mensaje y lo imprime por pantalla
	 *
	 *@param		mensaje						string											Mensaje
	 */

	@Override
	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}


	/**
	 * Método de una interfaz
	 * Comprueba una sancion a traves del id de un alumno
	 *

	 * @param		idAlumno						int											Id del alumno supuestamente sancionado
	 * @return		sancion							int											Tipo de sanción
	 */

	@Override
	public int comprobarSancion(int idAlumno) {
		int					sancion		= -1;
		ArrayList<Integer>	reservas;
		ArrayList<Integer>	incidencias;

		reservas = this._gestorReservas.buscarReservas(idAlumno);

		for(int reserva : reservas) {
			incidencias = this._gestorReservas.buscarIncidencias(reserva);

			for(int incidencia : incidencias) {
				sancion = this._gestorReservas.buscarSancion(incidencia);
			}

		}

		return sancion;
	}


	/**
	 * Método de una interfaz
	 * Desvuelve la descripción de una sanción a traves del codigo de esta
	 *
	 * @param		codigo_sancion						int											Código de la sancion
	 */

	public String describirSancion(int codigo_sancion) {
		return IReservaMgt.describirSancion(codigo_sancion);
	}

}
