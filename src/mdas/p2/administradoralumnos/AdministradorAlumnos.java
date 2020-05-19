package mdas.p2.administradoralumnos;


import java.util.ArrayList;

import mdas.p2.gestorreservamgr.GestorReservaMgr;


// TODO: Comentar clase

public class AdministradorAlumnos implements IInformarAlumno, IComprobarSancion {
	GestorReservaMgr _gestorReservas;


	// TODO: Comentar método

	@Override
	public void MostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}


	// TODO: Comentar método

	@Override
	public int ComprobarSancion(int idAlumno) {
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
