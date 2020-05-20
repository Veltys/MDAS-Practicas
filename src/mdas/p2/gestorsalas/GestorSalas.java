package mdas.p2.gestorsalas;


import java.util.ArrayList;

import mdas.p2.gestorreservamgr.ReservaMgr;


/**
 * Clase GestorSalas
 *
 * @author			Javier Ortiz Aragones
 * @date			19/05/2020
 * @version			1.0.2
 */

public class GestorSalas implements IReserva, ISala{
	private ReservaMgr _gestorReservas;


	// TODO: Comentar

	public GestorSalas() {
		this._gestorReservas = ReservaMgr.getInstance();
	}


	// TODO: Comentar

	@Override
	public ArrayList<Integer> buscarReservas(int idAlumno) {
		return this._gestorReservas.buscarReservas(idAlumno);
	}


	// TODO: Comentar

	@Override
	public Boolean confirmarReserva(int idReserva) {
		return this._gestorReservas.confirmarReserva(idReserva);
	}

	// TODO: Comentar

	@Override
	public Boolean eliminarReserva(int idReserva) {
		return this._gestorReservas.eliminarReserva(idReserva);
	}


	// TODO: Comentar

	@Override
	public ArrayList<Integer> buscarSala(int aforo, ArrayList<Integer> idsRecursos) {
		return this._gestorReservas.buscarSala(aforo, idsRecursos);
	}


	// TODO: Comentar

	@Override
	public Boolean confirmarRegistro(int idSala) {
		return this._gestorReservas.confirmarRegistro(idSala);
	}


	// TODO: Comentar

	@Override
	public int elegirSala(int aforo, ArrayList<Integer> idSalas) {
		// FIXME: Refinar la búsqueda

		//sala mas apropiada e ir comparandola

		for(int sala : idSalas) {
			if(aforo == this._gestorReservas.obtenerAforoSala(sala)) {
				return sala;
			}
		}

		return 0;
	}


	// TODO: Comentar

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
