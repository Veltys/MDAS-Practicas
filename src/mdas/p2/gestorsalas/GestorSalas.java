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
	private ReservaMgr _reservaMgr;


	public GestorSalas() {
		this._reservaMgr = new ReservaMgr();
	}


	@Override
	public ArrayList<Integer> buscarReservas(int idAlumno) {
		return this._reservaMgr.buscarReservas(idAlumno);
	}


	@Override
	public Boolean confirmarReserva(int idReserva) {
		return this._reservaMgr.confirmarReserva(idReserva);
	}

	@Override
	public Boolean eliminarReserva(int idReserva) {
		return this._reservaMgr.eliminarReserva(idReserva);
	}

	//ISala

	@Override
	public ArrayList<Integer> buscarSala(int aforo, ArrayList<Integer> idsRecursos) {
		return this._reservaMgr.buscarSala(aforo, idsRecursos);
	}


	@Override
	public Boolean confirmarRegistro(int idSala) {
		return this._reservaMgr.confirmarRegistro(idSala);
	}


	@Override
	public int elegirSala(int aforo, ArrayList<Integer> idSalas) {
		// FIXME: Refinar la búsqueda

		for(int sala : idSalas) {
			if(aforo == this._reservaMgr.obtenerAforoSala(sala)) {
				return sala;
			}
		}

		return 0;
	}


	@Override
	public Boolean validarDatos(String nombre, int aforo, int tipo, String ubicacion, ArrayList<Integer> recursos) {
		if(aforo < 1) {
			return false;
		}
		else {
			// TODO: Seguir validando

			return true;
		}
	}

}
