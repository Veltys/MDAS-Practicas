package mdas.p2.gestorreservamgr;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringTokenizer;

import mdas.p2.gestorreservamgr.Incidencia;
import mdas.p2.gestorreservamgr.Reserva;
import mdas.p2.gestorreservamgr.Sala;
import mdas.p2.gestorreservamgr.TipoIncidencia;


/**
 * Clase GestorReservaMgr
 * Componente de gestión de reservas del sistema
 * Es implementado por medio del patrón Singleton, con el fin de prevenir la existencia de más de un gestor
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			21/05/2020
 * @version			0.6.0
 */


public class ReservaMgr implements IReservaMgt {
	static private ReservaMgr		_instance		= null;
	private ArrayList<Incidencia>	_incidencias;
	private ArrayList<Reserva>		_reservas;
	private ArrayList<Sala>			_salas;
	private ArrayList<Sancion> 		_sanciones;


	/**
	 * Constructor de clase
	 * Privado, requisito del patrón Singleton
	 * Inicializa las listas del gestor
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

		for(Sancion s : this._sanciones) {
			if(idIncidencia == s.idIncidencia()) {
				res = s.idIncidencia();

				break;
			}
		}

		return res;
	}


	/**
	 * Método privado para buscar el tipo de incidencia
	 *
	 * @param		int_TipoIncidencia				int								ID de tipo de incidencia
	 *
	 * @return										TipoIncidencia					El tipo de incidencia encontrado
	 */

	private TipoIncidencia buscarTipoIncidencia(int int_TipoIncidencia) {
		TipoIncidencia tipoIncidencia = null;

		for(TipoIncidencia ti : TipoIncidencia.values()) {
			if(int_TipoIncidencia == ti.id()) {
				tipoIncidencia = ti;

				break;
			}
		}

		return tipoIncidencia;
	}


	/**
	 * Método de carga de archivos
	 * Carga los archivos CSV solicitados en la memoria del gestor
	 *
	 * @param		archivoIncidencias				String							Archivo de incidencias
	 * @param		archivoReservas					String							Archivo de reservas
	 * @param		archivoSalas					String							Archivo de salas
	 *
	 * @return										boolean							Resultado de la operación
	 */

	@Override
	public boolean cargar(String archivoIncidencias, String archivoReservas, String archivoSalas, String archivoSanciones) {
		int					i;
		String				linea;
		ArrayList<String>	campos;
		BufferedReader		buffer;
		StringTokenizer		stLinea;

		for(i = 0; i < 4; i++) {
			try {
				switch(i) {
				case 0:
					buffer = new BufferedReader(new FileReader(new File(archivoIncidencias)));

					break;
				case 1:
					buffer = new BufferedReader(new FileReader(new File(archivoReservas)));

					break;
				case 2:
					buffer = new BufferedReader(new FileReader(new File(archivoSalas)));

					break;
				case 3:
					buffer = new BufferedReader(new FileReader(new File(archivoSanciones)));
					break;
				default:
					buffer = null;

					break;
				}

				while((linea = buffer.readLine()) != null) {
					stLinea = new StringTokenizer(linea, ",");

					campos = new ArrayList<String>();

					while(stLinea.hasMoreTokens()) {
						campos.add(stLinea.nextToken());
					}

					switch(i) {
					case 0:
						this._incidencias.add(new Incidencia(Integer.parseInt(campos.get(0)), Integer.parseInt(campos.get(1)), campos.get(2), this.buscarTipoIncidencia(Integer.parseInt(campos.get(3)))));

						break;
					case 1:
						this._reservas.add(new Reserva(Integer.parseInt(campos.get(0)), Integer.parseInt(campos.get(1)), Integer.parseInt(campos.get(2)), campos.get(3), Integer.parseInt(campos.get(4)), Boolean.parseBoolean(campos.get(5)), LocalDateTime.parse(campos.get(6), DateTimeFormatter.ISO_LOCAL_DATE_TIME)));

						break;
					case 2:
						this._salas.add(new Sala());								// FIXME: Reformar

						break;
					case 3:
						this._sanciones.add(new Sancion(Integer.parseInt(campos.get(0)), Integer.parseInt(campos.get(1)), Integer.parseInt(campos.get(2)), Integer.parseInt(campos.get(3)), LocalDate.parse(campos.get(6), DateTimeFormatter.ISO_LOCAL_DATE)));

						break;
					default:
						break;														// Nunca se llegará aquí
					}
				}
			}
			catch(FileNotFoundException e) {
				System.out.println("Error: " + e.getMessage());

				return false;
			}
			catch(IOException e) {
				System.out.println("Error: " + e.getMessage());

				return false;
			}
		}

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
	public boolean guardar(String archivoIncidencias, String archivoReservas, String archivoSalas, String archivoSanciones) {
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
	public ArrayList<Integer> obtenerTiposDeIncidencia() {
		// TODO Auto-generated method stub
		return null;
	}


	// TODO: Comentar

	@Override
	public ArrayList<Integer> obtenerTiposDeSala() {
		// TODO Auto-generated method stub
		return null;
	}
}
