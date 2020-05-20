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
 * @date			20/05/2020
 * @version			0.4.0
 */


public class ReservaMgr implements IReservaMgt {
	static private ReservaMgr		_instance		= null;							// Única instancia
	private ArrayList<Incidencia>	_incidencias;
	private ArrayList<Reserva>		_reservas;
	private ArrayList<Sala>			_salas;


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
	 * @return										ArrayList<Integer>				ArrayList de IDs de reservas asociadas al alumno (null si ninguna)
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


	/**
	 * Buscador de salas
	 * Busca una sala adecuada al aforo y a los recursos proporcionados
	 *
	 * @param		aforo							int								Aforo mínimo requerido
	 * @param		idsRecursos						ArrayList<Integer>				IDs de los recursos mínimos requeridos
	 *
	 * @return										ArrayList<Integer>				ArrayList de IDs de salas que cumplen los requisitos (null si ninguno)
	 */

	@Override
	public ArrayList<Integer> buscarSala(int aforo, ArrayList<Integer> idsRecursos) {
		ArrayList<Integer>	res	= new ArrayList<Integer>();

		for(Sala s : this._salas) {
			if((s.aforo() >= aforo) && s.tengoRecursos(idsRecursos)) {
				res.add(s.id());
			}
		}

		if(res.size() == 0) {
			res = null;
		}

		return res;
	}


	/**
	 * Buscador de sanciones
	 * Busca las sanción asociada a la incidencia dada
	 *
	 * @param		idIncidencia					int								ID de la incidencia
	 *
	 * @return										int								ID de la sanción (-1 si no la hay)
	 */

	@Override
	public int buscarSancion(int idIncidencia) {
		int res = -1;

		for(Incidencia i : this._incidencias) {
			if(idIncidencia == i.id()) {
				res = i.idSancion();

				break;
			}
		}

		return res;
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
