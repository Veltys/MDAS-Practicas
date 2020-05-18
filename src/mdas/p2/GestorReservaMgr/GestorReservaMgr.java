package mdas.p2.GestorReservaMgr;


import java.util.ArrayList;

import mdas.p2.GestorReservaMgr.Incidencia;
import mdas.p2.GestorReservaMgr.Reserva;
import mdas.p2.GestorReservaMgr.Sala;


/**
 * Clase GestorReservaMgr
 * Componente de gestión de reservas del sistema
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			18/05/2020
 * @version			0.1.0
 */


public class GestorReservaMgr implements IReservaMgt {
	ArrayList<Incidencia>	_incidencias;
	ArrayList<Reserva>		_reservas;


	// TODO: Constructor


	/**
	 * Buscador de incidencias
	 * Busca una incidencia a través de un ID de reserva
	 *
	 * @param		id_reserva						int								ID de la reserva para buscar sus incidencias
	 *
	 * @return										int[]							Vector de incidencias asociadas a la reserva (null si ninguna)
	 */

	@Override
	public ArrayList<Integer> BuscarIncidencias(int id_reserva) {
		ArrayList<Integer> res = new ArrayList<Integer>();

		for(Incidencia i : this._incidencias) {
			if(i.id_reserva() == id_reserva) {
				res.add(i.id());
			}
		}

		if(res.size() == 0) {
			res = null;
		}

		return res;
	}


	// TODO: Comentar

	@Override
	public ArrayList<Integer> BuscarReservas(int id_alumno) {
		// TODO Auto-generated method stub
		return null;
	}


	// TODO: Comentar

	@Override
	public ArrayList<Integer> BuscarSala(int aforo, int ids_recursos) {
		// TODO Auto-generated method stub
		return null;
	}


	// TODO: Comentar

	@Override
	public ArrayList<Integer> BuscarSancion(int id_incidencia) {
		// TODO Auto-generated method stub
		return null;
	}


	// TODO: Comentar

	@Override
	public boolean ConfirmarRegistro(int id_reserva) {
		// TODO Auto-generated method stub
		return false;
	}


	// TODO: Comentar

	@Override
	public boolean ConfirmarReserva(int id_reserva) {
		// TODO Auto-generated method stub
		return false;
	}


	// TODO: Comentar

	@Override
	public String DescribirSancion(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}


	// TODO: Comentar

	@Override
	public Reserva ObtenerReserva(int id_reserva) {
		// TODO Auto-generated method stub
		return null;
	}


	// TODO: Comentar

	@Override
	public Sala ObtenerSala(int id_sala) {
		// TODO Auto-generated method stub
		return null;
	}


	// TODO: Comentar

	@Override
	public ArrayList<Integer> ObtenerTipos() {
		// TODO Auto-generated method stub
		return null;
	}
}
