package mdas.p2.gestorreservamgr;


import java.io.BufferedReader;
// import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
// import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.StringTokenizer;

// import mdas.p2.gestorreservamgr.Incidencia;
// import mdas.p2.gestorreservamgr.Reserva;
// import mdas.p2.gestorreservamgr.Sala;
// import mdas.p2.gestorreservamgr.SalaYRecurso;
// import mdas.p2.gestorreservamgr.TipoIncidencia;


/**
 * Clase GestorReservaMgr
 * Componente de gestión de reservas del sistema
 * Es implementado por medio del patrón Singleton, con el fin de prevenir la existencia de más de un gestor
 * Implementa la interfaz IReservaMgt
 *
 * @author		Rafael Carlos Méndez Rodríguez (i82meror)
 * @date		27/05/2020
 * @version		0.19.0
 */


public class ReservaMgr implements IReservaMgt {
	static private ReservaMgr		_instance			= null;
	private ArrayList<Incidencia>	_incidencias;
	private ArrayList<Recurso>		_recursos;
	private ArrayList<Reserva>		_reservas;
	private ArrayList<Sala>			_salas;
	private ArrayList<SalaYRecurso>	_salasYRecursos;
	private ArrayList<Sancion> 		_sanciones;


	/**
	 * Constructor de clase
	 * Privado, requisito del patrón Singleton
	 * Inicializa las listas del gestor
	 */

	private ReservaMgr(String archivoIncidencias, String archivoRecursos, String archivoReservas, String archivoSalas, String archivoSalasYRecursos, String archivoSanciones) {
		this._incidencias		= new ArrayList<Incidencia>();
		this._recursos			= new ArrayList<Recurso>();
		this._reservas			= new ArrayList<Reserva>();
		this._salas				= new ArrayList<Sala>();
		this._salasYRecursos	= new ArrayList<SalaYRecurso>();
		this._sanciones			= new ArrayList<Sancion>();

		this.cargar(archivoIncidencias, archivoRecursos, archivoReservas, archivoSalas, archivoSalasYRecursos, archivoSanciones);

		// TODO: Timer de guardado
	}


	/**
	 * Método estático para obtener la única instancia válida (o crearla si no existe) del gestor
	 *
	 * @return										ReservaMgr						Instancia del gestor
	 */

	public static ReservaMgr getInstance(String archivoIncidencias, String archivoRecursos, String archivoReservas, String archivoSalas, String archivoSalasYRecursos, String archivoSanciones) {
		if(ReservaMgr._instance == null) {
			ReservaMgr._instance = new ReservaMgr(archivoIncidencias, archivoRecursos, archivoReservas, archivoSalas, archivoSalasYRecursos, archivoSanciones);
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

		for(Incidencia i: this._incidencias) {
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
	 * Método privado para buscar reservas
	 * Busca una reserva por su ID
	 *
	 * @param		idReserva						int								ID de la reserva a buscar
	 *
	 * @return										int								Posición en la lista de reservas (-1 si no encontrada)
	 */

	private int buscarReserva(int idReserva) {
		int	i;
		int res			= -1;
		int	tamLista	= this._reservas.size();

		for(i = 0; i < tamLista; i++) {
			if((this._reservas.get(i).id() >= idReserva)) {
				res = i;
			}
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

		for(Reserva r: this._reservas) {
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
	 * Método privado para buscar salas
	 * Busca una sala por su ID
	 *
	 * @param		idSala							int								ID de la sala a buscar
	 *
	 * @return										int								Posición en la lista de salas (-1 si no encontrada)
	 */

	private int buscarSala(int idSala) {
		int	i;
		int res			= -1;
		int	tamLista	= this._salas.size();

		for(i = 0; i < tamLista; i++) {
			if((this._salas.get(i).id() == idSala)) {
				res = i;
			}
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
		int					recursosQueTengo;
		ArrayList<Integer>	res					= new ArrayList<Integer>();

		for(Sala s: this._salas) {
			if((s.aforo() >= aforo)) {
				recursosQueTengo = 0;

				for(SalaYRecurso syr: this._salasYRecursos) {
					if(((s.id() == syr.idSala()) && idsRecursos.contains(syr.idRecurso()))) {
						recursosQueTengo++;
					}
				}

				if(recursosQueTengo >= idsRecursos.size()) {
					res.add(s.id());
				}
			}
		}

		if(res.size() == 0) {
			res = null;
		}

		return res;
	}


	/**
	 * Buscador de sanciones
	 * Busca la sanción asociada a la incidencia dada
	 *
	 * @param		idIncidencia					int								ID de la incidencia
	 *
	 * @return										int								ID de la sanción (-1 si no la hay)
	 */

	@Override
	public int buscarSancion(int idIncidencia) {
		Sancion res = null;

		for(Sancion s: this._sanciones) {
			if(idIncidencia == s.idIncidencia()) {
				res = s;

				break;
			}
		}

		if(res.fecha().plusDays(res.duracion()).compareTo(LocalDate.now()) >= 0) {
			return res.id();
		}
		else {
			return -1;
		}
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

		for(TipoIncidencia ti: TipoIncidencia.values()) {
			if(int_TipoIncidencia == ti.id()) {
				tipoIncidencia = ti;

				break;
			}
		}

		return tipoIncidencia;
	}


	/**
	 * Método privado para buscar el tipo de sala
	 *
	 * @param		int_TipoSala					int								ID de tipo de sala
	 *
	 * @return										TipoSala						El tipo de sala encontrado
	 */

	private TipoSala buscarTipoSala(int int_TipoSala) {
		TipoSala tipoSala = null;

		for(TipoSala ts: TipoSala.values()) {
			if(int_TipoSala == ts.id()) {
				tipoSala = ts;

				break;
			}
		}

		return tipoSala;
	}


	/**
	 * Método de carga de archivos
	 * Carga los archivos CSV solicitados en la memoria del gestor
	 *
	 * @param		archivoIncidencias				String							Archivo de incidencias
	 * @param		archivoRecursos					String							Archivo de recursos
	 * @param		archivoReservas					String							Archivo de reservas
	 * @param		archivoSalas					String							Archivo de salas
	 * @param		archivoSalasYRecursos			String							Archivo de salas y recursos
	 * @param		archivoSanciones				String							Archivo de sanciones
	 *
	 * @return										boolean							Resultado de la operación
	 */

	private boolean cargar(String archivoIncidencias, String archivoRecursos, String archivoReservas, String archivoSalas, String archivoSalasYRecursos, String archivoSanciones) {
		int					i;
		String				linea;
		ArrayList<String>	campos;
		BufferedReader		buffer;
		StringTokenizer		stLinea;

		for(i = 0; i <= 5; i++) {
			try {
				switch(i) {
				case 0:
					buffer = new BufferedReader(new FileReader(new File(archivoIncidencias)));

					break;
				case 1:
					buffer = new BufferedReader(new FileReader(new File(archivoRecursos)));

					break;
				case 2:
					buffer = new BufferedReader(new FileReader(new File(archivoReservas)));

					break;
				case 3:
					buffer = new BufferedReader(new FileReader(new File(archivoSalas)));

					break;
				case 4:
					buffer = new BufferedReader(new FileReader(new File(archivoSalasYRecursos)));

					break;
				case 5:
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
						this._recursos.add(new Recurso(Integer.parseInt(campos.get(0)), campos.get(1), campos.get(2)));

						break;
					case 2:
						this._reservas.add(new Reserva(Integer.parseInt(campos.get(0)), Integer.parseInt(campos.get(1)), Integer.parseInt(campos.get(2)), Integer.parseInt(campos.get(3)), campos.get(4), Integer.parseInt(campos.get(5)), Boolean.parseBoolean(campos.get(6)), LocalDateTime.parse(campos.get(7), DateTimeFormatter.ISO_LOCAL_DATE_TIME)));

						break;
					case 3:
						this._salas.add(new Sala(Integer.parseInt(campos.get(0)), Integer.parseInt(campos.get(1)), Boolean.parseBoolean(campos.get(2)), campos.get(3), this.buscarTipoSala(Integer.parseInt(campos.get(4))), campos.get(5)));

						break;
					case 4:
						this._salasYRecursos.add(new SalaYRecurso(Integer.parseInt(campos.get(0)), Integer.parseInt(campos.get(1))));

						break;
					case 5:
						this._sanciones.add(new Sancion(Integer.parseInt(campos.get(0)), Integer.parseInt(campos.get(1)), Integer.parseInt(campos.get(2)), Integer.parseInt(campos.get(3)), LocalDate.parse(campos.get(4), DateTimeFormatter.ISO_LOCAL_DATE)));

						break;
					default:														// Nunca se llegará aquí
						break;
					}
				}

				buffer.close();
			}
			catch(FileNotFoundException e) {
				System.out.println("Error: " + e.getMessage());
			}
			catch(IOException e) {
				System.out.println("Error: " + e.getMessage());

				return false;
			}
		}

		return true;
	}


	/**
	 * Método de confirmación del registro de una sala
	 * Marca como disponible una sala prerregistrada
	 *
	 * @param		idSala							int								ID de la sala a confirmar
	 *
	 * @return										boolean							Resultado de la operación
	 */

	@Override
	public boolean confirmarRegistro(int idSala) {
		int posSala = this.buscarSala(idSala);

		if(posSala != -1) {
			this._salas.get(posSala).estado(true);

			return true;
		}
		else {
			return false;
		}
	}


	/**
	 * Método de confirmación del registro de una reserva
	 * Marca como reservada una reserva de sala en estado prerreservada
	 *
	 * @param		idReserva						int								ID de la reserva a confirmar
	 *
	 * @return										boolean							Resultado de la operación
	 */


	@Override
	public boolean confirmarReserva(int idReserva) {
		int posReserva = this.buscarReserva(idReserva);

		if(posReserva != -1) {
			this._reservas.get(posReserva).estado(true);

			return true;
		}
		else {
			return false;
		}
	}


	/**
	 * Método de eliminación de una reserva
	 * Elimina una reserva de sala de la lista
	 *
	 * @param		idReserva						int								ID de la reserva a eliminar
	 *
	 * @return										boolean							Resultado de la operación
	 */

	@Override
	public boolean eliminarReserva(int idReserva) {
		int posReserva = this.buscarReserva(idReserva);

		if(posReserva != -1) {
			this._reservas.remove(posReserva);

			return true;
		}
		else {
			return false;
		}
	}


	/**
	 * Método de guardado de archivos
	 * Guarda la memoria del gestor en los archivos CSV solicitados
	 *
	 * @param		archivoIncidencias				String							Archivo de incidencias
	 * @param		archivoRecursos					String							Archivo de recursos
	 * @param		archivoReservas					String							Archivo de reservas
	 * @param		archivoSalas					String							Archivo de salas
	 * @param		archivoSalasYRecursos			String							Archivo de salas y recursos
	 * @param		archivoSanciones				String							Archivo de sanciones
	 *
	 * @return										boolean							Resultado de la operación
	 */

	/*
	private boolean guardar(String archivoIncidencias, String archivoRecursos, String archivoReservas, String archivoSalas, String archivoSalasYRecursos, String archivoSanciones) {
		int					i;
		ArrayList<String>	lineas	= new ArrayList<String>();
		BufferedWriter		buffer	= null;

		for(i = 0; i <= 5; i++) {
			try {
				switch(i) {
				case 0:
					buffer = new BufferedWriter(new FileWriter(new File(archivoIncidencias)));

					for(Incidencia in: this._incidencias) {
						lineas.add(in.toString());
					}

					break;
				case 1:
					buffer = new BufferedWriter(new FileWriter(new File(archivoRecursos)));

					for(Recurso rec: this._recursos) {
						lineas.add(rec.toString());
					}

					break;
				case 2:
					buffer = new BufferedWriter(new FileWriter(new File(archivoReservas)));

					for(Reserva res: this._reservas) {
						lineas.add(res.toString());
					}

					break;
				case 3:
					buffer = new BufferedWriter(new FileWriter(new File(archivoSalas)));

					for(Sala sal: this._salas) {
						lineas.add(sal.toString());
					}

					break;
				case 4:
					buffer = new BufferedWriter(new FileWriter(new File(archivoSalasYRecursos)));

					for(SalaYRecurso syl: this._salasYRecursos) {
						lineas.add(syl.toString());
					}

					break;
				case 5:
					buffer = new BufferedWriter(new FileWriter(new File(archivoSanciones)));

					for(Sancion san: this._sanciones) {
						lineas.add(san.toString());
					}

					break;
				default:															// Nunca se llegará aquí
					break;
				}

				for(String linea: lineas) {
					buffer.write(linea + System.getProperty("line.separator"));
				}

				buffer.close();
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
	 */

	/**
	 * Observador del aforo de una sala
	 * Busca una sala por su ID y devuelve su aforo
	 *
	 * @param		idSala							int								ID de la sala
	 *
	 * @return										int								Aforo de la sala (-1 si no encontrada)
	 */

	@Override
	public int obtenerAforoSala(int idSala) {
		int posSala = this.buscarSala(idSala);

		if(posSala != -1) {
			return this._salas.get(posSala).aforo();
		}
		else {
			return -1;
		}
	}


	/**
	 * Observador de la lista de recursos
	 *
	 * @return										ArrayList<Recurso>				Lista de recursos cargada en el gestor
	 */

	@Override
	public ArrayList<Recurso> obtenerRecursos() {
		return this._recursos;
	}


	/**
	 * Observador de una reserva
	 * Busca una reserva por su ID y la devuelve
	 *
	 * @param		idReserva						int								ID de la reserva
	 *
	 * @return										int								Reserva (null si no encontrada)
	 */

	@Override
	public Reserva obtenerReserva(int idReserva) {
		int posReserva = this.buscarReserva(idReserva);

		if(posReserva != -1) {
			return this._reservas.get(posReserva);
		}
		else {
			return null;
		}
	}


	/**
	 * Observador de una sala
	 * Busca una sala por su ID y la devuelve
	 *
	 * @param		idSala							int								ID de la sala
	 *
	 * @return										int								Sala (null si no encontrada)
	 */

	@Override
	public Sala obtenerSala(int idSala) {
		int posSala = this.buscarSala(idSala);

		if(posSala != -1) {
			return this._salas.get(posSala);
		}
		else {
			return null;
		}
	}


	@Override
	/**
	 * Observador de una sanción
	 * Busca una sanción por su ID y la devuelve
	 *
	 * @param		idSancion						int								ID de la sanción
	 *
	 * @return										int								Sanción (null si no encontrada)
	 */

	public Sancion obtenerSancion(int idSancion) {
		int	i;
		int posSancion	= -1;
		int	tamLista	= this._sanciones.size();

		for(i = 0; i < tamLista; i++) {
			if((this._sanciones.get(i).id() == idSancion)) {
				posSancion = i;
			}
		}

		if(posSancion != -1) {
			return this._sanciones.get(posSancion);
		}
		else {
			return null;
		}
	}


	// TODO: Comentar

	@Override
	public int preRegistrarSala(String nombre, int aforo, int tipo, String ubicacion, ArrayList<Integer> recursos) {
		// TODO: Por hacer

		return -1;
	}


	// TODO: Comentar

	@Override
	public int preReservarSala(int idAlumno, int idSala, int alumos, String asignatura, int duracion, LocalDateTime fechaYHora) {
		// TODO: Por hacer

		return -1;
	}


	/**
	 * Método de comprobación de solapamiento de reservas
	 *
	 * @param		idSala							int								ID de la sala a comprobar
	 * @param		fechaYHora						LocalDateTime					Fecha y hora para la que debe estar libre
	 * @param		duracion						int								Duración mínima (en horas) que debe estar libre
	 *
	 * @return										boolean							Si la sala está libre o no
	 */

	@Override
	public boolean salaLibre(int idSala, LocalDateTime fechaYHora, int duracion) {
		ArrayList<Reserva>	reservas = new ArrayList<Reserva>();

		for(Reserva r: this._reservas) {
			if(
					(idSala == r.idSala()) &&
					LocalDateTime.now().isBefore(r.fechaYHora().plusHours(r.duracion())) &&
					!(fechaYHora.isAfter(r.fechaYHora().plusHours(r.duracion()))) &&
					!(fechaYHora.plusHours(duracion).isBefore(r.fechaYHora()))
					) {
				reservas.add(r);
			}
		}

		return (reservas.size() == 0);
	}
}
