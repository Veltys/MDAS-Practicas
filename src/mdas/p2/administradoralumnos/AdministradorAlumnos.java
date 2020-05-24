package mdas.p2.administradoralumnos;


import java.util.ArrayList;

import mdas.p2.gestorreservamgr.ReservaMgr;


/**
 * Clase AdministadorAlumnos
 * Contiene los métodos de AdministradorAlumnos
 *
 * @author			Herminio Rodríguez García (i72rogah)
 * @date			19/05/2020
 * @version			1.2.0
 */

public class AdministradorAlumnos implements IInformarAlumno, IComprobarSancion {
	private ReservaMgr _gestorReservas;

	/**
	 * Constructor de clase
	 * Inicializa el objeto de la clase ReservaMgr
	 */
	
	AdministradorAlumnos() {
		this._gestorReservas = new ReservaMgr();
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
		return this._gestorReservas.describirSancion(codigo_sancion);
}
	
}
