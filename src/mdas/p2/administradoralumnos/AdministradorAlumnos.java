package mdas.p2.administradoralumnos;


import java.util.ArrayList;

import mdas.p2.gestorreservamgr.ReservaMgr;


// TODO: Comentar clase

public class AdministradorAlumnos implements IInformarAlumno, IComprobarSancion {
	private ReservaMgr _gestorReservas;


	// TODO: Comentar método

	@Override
	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}


	// TODO: Comentar método

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
}
