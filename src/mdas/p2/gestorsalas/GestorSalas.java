package mdas.p2.gestorsalas;


import java.util.ArrayList;

import mdas.p2.gestorreservamgr.ReservaMgr;


/**
 * Clase GestorSalas
 *
 * @author			Javier Ortiz Aragones
 * @date			19/05/2020
 * @version			1.0.0
 */

public class GestorSalas implements IReserva, ISala{
	ReservaMgr _reservaMgr;


	public GestorSalas() {
		this._reservaMgr = new ReservaMgr();
	}


	@Override
	public ArrayList<Integer> buscarReservas(int idAlumno) {
		return this._reservaMgr.buscarReservas(idAlumno);
	}


	@Override
	public Boolean confirmarReserva(int id_reserva) {
		return this._reservaMgr.confirmarReserva(id_reserva);
	}

	@Override
	public Boolean eliminarReserva(int id_reserva) {
		return this._reservaMgr.eliminarReserva(id_reserva);
	}

	//ISala

	@Override
	public ArrayList<Integer> BuscarSala(int aforo, ArrayList<Integer> idsRecursos) {
		return this._reservaMgr.buscarSala(aforo, idsRecursos);
	}


	@Override
	public Boolean ConfirmarRegistro(int id_sala) {
		return this._reservaMgr.confirmarRegistro(id_sala);
	}


	@Override
	public int ElegirSala(int aforo, ArrayList<Integer> idSalas) {
		// FIXME: Refinar la búsqueda

		for(int sala : idSalas) {
			if(aforo == this._reservaMgr.obtenerAforoSala(sala)) {
				return sala;
			}
		}

		return 0;
	}


	@Override
	public Boolean ValidarDatos(String nombre, int aforo, int tipo, String ubicacion, ArrayList<Integer> recursos) {
		if(aforo < 1) {
			return false;
		}
		else {
			// TODO: Seguir validando

			return true;
		}
	}

}
