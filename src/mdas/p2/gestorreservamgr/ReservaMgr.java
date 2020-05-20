package mdas.p2.gestorreservamgr;


import java.util.ArrayList;

import mdas.p2.gestorreservamgr.Incidencia;
import mdas.p2.gestorreservamgr.Reserva;
import mdas.p2.gestorreservamgr.Sala;


/**
 * Clase GestorReservaMgr
 * Componente de gestión de reservas del sistema
 * Es implementado por medio del patrón Singleton, con el fin de prevenir la existencia de más de un gestor
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			18/05/2020
 * @version			0.3.0
 */


public class ReservaMgr implements IReservaMgt {
	static private ReservaMgr		_instance		= null;							// Única instancia
	private ArrayList<Incidencia>	_incidencias;
	private ArrayList<Reserva>		_reservas;


	/**
	 * Constructor de clase
	 * Privado, requisito del patrón Singleton
	 * Inicializa las listas
	 */

	private ReservaMgr() {
		this._incidencias	= new ArrayList<Incidencia>();
		this._reservas		= new ArrayList<Reserva>();
	}


	/**
	 * Método estático para obtener la única instancia válida (o crearla si no existe) del gestor
	 *
	 * @return										ReservaMgr						Instancia del gestor
	 */

	public static ReservaMgr getInstance() {
		if(ReservaMgr._instance == null) {
			ReservaMgr._instance = new ReservaMgr();
		}

		return ReservaMgr._instance;
	}


	/**
	 * Buscador de incidencias
	 * Busca una incidencia a través de un ID de reserva
	 *
	 * @param		idReserva						int								ID de la reserva para buscar sus incidencias
	 *
	 * @return										int[]							Vector de incidencias asociadas a la reserva (null si ninguna)
	 */

	@Override
	public ArrayList<Integer> buscarIncidencias(int idReserva) {
		ArrayList<Integer> res = new ArrayList<Integer>();

		for(Incidencia i : this._incidencias) {
			if(i.idReserva() == idReserva) {
				res.add(i.id());
			}
		}

		if(res.size() == 0) {
			res = null;
		}

		return res;
	}


	/**
	 * Buscador de reservas
	 * Busca una reserva a través de la ID del alumno que la ha reservado
	 *
	 * @param		idAlumno						int								ID del alumno
	 *
	 * @return										int[]							Vector de reservas asociadas al alumno (null si ninguna)
	 */

	@Override
	public ArrayList<Integer> buscarReservas(int idAlumno) {
		ArrayList<Integer> res = new ArrayList<Integer>();

		for(Reserva r : this._reservas) {
			if(r.idAlumno() == idAlumno) {
				res.add(r.id());
			}
		}

		if(res.size() == 0) {
			res = null;
		}

		return res;
	}


	// TODO: Comentar

	@Override
	public ArrayList<Integer> buscarSala(int aforo, ArrayList<Integer> idsRecursos) {
		// TODO Auto-generated method stub
		return null;
	}


	// TODO: Comentar

	@Override
	public int buscarSancion(int idIncidencia) {
		// TODO Auto-generated method stub
		return 0;
	}


	// TODO: Comentar

	@Override
	public boolean cargar(String archivoIncidencias, String archivoReservas) {
		// TODO Auto-generated method stub
		return true;
	}


	// TODO: Comentar

	@Override
	public boolean confirmarRegistro(int idReserva) {
		// TODO Auto-generated method stub
		return false;
	}


	// TODO: Comentar

	@Override
	public boolean confirmarReserva(int idReserva) {
		// TODO Auto-generated method stub
		return false;
	}


	// TODO: Comentar

	@Override
	public String describirSancion(int codigo) {
		// TODO Auto-generated method stub
		return null;
	}


	// TODO: Comentar

	@Override
	public boolean eliminarReserva(int id_reserva) {
		// TODO Auto-generated method stub
		return true;
	}


	// TODO: Comentar

	@Override
	public boolean guardar(String archivoIncidencias, String archivoReservas) {
		// TODO Auto-generated method stub
		return true;
	}


	// TODO: Comentar

	@Override
	public int obtenerAforoSala(int sala) {
		// TODO Auto-generated method stub
		return 0;
	}


	// TODO: Comentar

	@Override
	public Reserva obtenerReserva(int idReserva) {
		// TODO Auto-generated method stub
		return null;
	}


	// TODO: Comentar

	@Override
	public Sala obtenerSala(int id_sala) {
		// TODO Auto-generated method stub
		return null;
	}


	// TODO: Comentar

	@Override
	public ArrayList<Integer> obtenerTipos() {
		// TODO Auto-generated method stub
		return null;
	}
}
