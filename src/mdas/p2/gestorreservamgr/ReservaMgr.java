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
 * @date		28/05/2020
 * @version		0.23.3
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
	 *
	 * @param		archivoIncidencias				String							Ruta del archivo donde incidencias
	 * @param		archivoRecursos					String							Ruta del archivo donde recursos
	 * @param		archivoReservas					String							Ruta del archivo donde reservas
	 * @param		archivoSalas					String							Ruta del archivo donde salas
	 * @param		archivoSalasYRecursos			String							Ruta del archivo donde salas y recursos
	 * @param		archivoSanciones				String							Ruta del archivo donde sanciones
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
	 * @param		archivoIncidencias				String							Ruta del archivo donde incidencias
	 * @param		archivoRecursos					String							Ruta del archivo donde recursos
	 * @param		archivoReservas					String							Ruta del archivo donde reservas
	 * @param		archivoSalas					String							Ruta del archivo donde salas
	 * @param		archivoSalasYRecursos			String							Ruta del archivo donde salas y recursos
	 * @param		archivoSanciones				String							Ruta del archivo donde sanciones
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
	 * Método privado para buscar recursos
	 * Busca un recurso por su ID
	 *
	 * @param		idRecurso						int								ID del recurso a buscar
	 *
	 * @return										int								Posición en la lista de recursos (-1 si no encontrado)
	 */

	private int buscarRecurso(int idRecurso) {
		int	i;
		int res			= -1;
		int	tamLista	= this._recursos.size();

		for(i = 0; i < tamLista; i++) {
			if((this._recursos.get(i).id() == idRecurso)) {
				res = i;

				break;
			}
		}

		return res;
	}


	/**
	 * Método privado para buscar reservas
	 * Busca una reserva por su ID
	 *
	 * @param		idReserva						int								ID de la reserva a buscar
	 * @param		todas							boolean							Buscar en todas las reservas o sólo las futuras
	 *
	 * @return										int								Posición en la lista de reservas (-1 si no encontrada)
	 */

	private int buscarReserva(int idReserva, boolean todas) {
		int		i;
		int		res			= -1;
		int		tamLista	= this._reservas.size();
		Reserva	reserva;

		for(i = 0; i < tamLista; i++) {
			reserva = this._reservas.get(i);

			if(reserva.id() == idReserva) {
				if(todas || reserva.fechaYHora().isAfter(LocalDateTime.now())) {
					res = i;
				}

				break;
			}
		}

		return res;
	}


	/**
	 * Buscador de reservas
	 * Busca las reservas de un alumno a través de la ID del alumno asociado a las mismas
	 *
	 * @param		idAlumno						int								ID del alumno
	 * @param		todas							boolean							Buscar todas las reservas o sólo las futuras
	 *
	 * @return										ArrayList&lt;Integer&gt;		ArrayList de IDs de reservas asociadas al alumno (null si ninguna)
	 */

	@Override
	public ArrayList<Integer> buscarReservas(int idAlumno, boolean todas) {
		ArrayList<Integer> res = new ArrayList<Integer>();

		for(Reserva r: this._reservas) {
			if((r.idAlumno() == idAlumno) && (todas || r.fechaYHora().isAfter(LocalDateTime.now()))) {
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

				break;
			}
		}

		return res;
	}


	/**
	 * Buscador de salas
	 * Busca una sala adecuada al aforo y a los recursos proporcionados
	 *
	 * @param		aforo							int								Aforo mínimo requerido
	 * @param		idsRecursos						ArrayList&lt;Integer&gt;		IDs de los recursos mínimos requeridos
	 *
	 * @return										ArrayList&lt;Integer&gt;		ArrayList de IDs de salas que cumplen los requisitos (null si ninguno)
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

		if((res != null) && (res.fecha().plusDays(res.duracion()).compareTo(LocalDate.now()) >= 0)) {
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
	 * @param		archivoIncidencias				String							Ruta del archivo donde incidencias
	 * @param		archivoRecursos					String							Ruta del archivo donde recursos
	 * @param		archivoReservas					String							Ruta del archivo donde reservas
	 * @param		archivoSalas					String							Ruta del archivo donde salas
	 * @param		archivoSalasYRecursos			String							Ruta del archivo donde salas y recursos
	 * @param		archivoSanciones				String							Ruta del archivo donde sanciones
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
		int posReserva = this.buscarReserva(idReserva, false);

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
		int posReserva = this.buscarReserva(idReserva, false);

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
	 * @param		archivoIncidencias				String							Ruta del archivo donde incidencias
	 * @param		archivoRecursos					String							Ruta del archivo donde recursos
	 * @param		archivoReservas					String							Ruta del archivo donde reservas
	 * @param		archivoSalas					String							Ruta del archivo donde salas
	 * @param		archivoSalasYRecursos			String							Ruta del archivo donde salas y recursos
	 * @param		archivoSanciones				String							Ruta del archivo donde sanciones
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
	 * Observador en texto de un recurso
	 * Recoge los datos de un recurso y los convierte en un String en texto apto para mostrárselo al usuario
	 *
	 * @param		idRecurso						int								ID del recurso a mostrar
	 *
	 * @return										String							Texto con los datos del recurso ("" si no encontrada)
	 */

	@Override
	public String mostrarRecurso(int idRecurso) {
		int		posRecurso = this.buscarRecurso(idRecurso);
		String	res;
		Recurso	recurso;

		if(posRecurso != -1) {
			recurso = this._recursos.get(posRecurso);

			res = recurso.id() + ": " + recurso.nombre() + " (" + recurso.descripcion() + ")";

			return res;
		}
		else {
			return "";
		}
	}


	/**
	 * Observador en texto de una reserva
	 * Recoge los datos de una reversa y los convierte en un String en texto apto para mostrárselo al usuario
	 *
	 * @param		idReserva						int								ID de la reserva a mostrar
	 *
	 * @return										String							Texto con los datos de la reserva ("" si no encontrada)
	 */

	@Override
	public String mostrarReserva(int idReserva) {
		int		posReserva = this.buscarReserva(idReserva, true);
		String	res;
		Recurso	recurso;
		Reserva	reserva;
		Sala	sala;

		if(posReserva != -1) {
			reserva = this._reservas.get(posReserva);

			sala = this._salas.get(this.buscarSala(reserva.idSala()));

			res =
					"Nombre de la sala: " + sala.nombre() + System.getProperty("line.separator") +
					"Aforo de la sala: " + sala.aforo() + " personas" + System.getProperty("line.separator") +
					"Ubicación de la sala: " + sala.ubicacion() + System.getProperty("line.separator") +
					"Hora de entrada: " + reserva.fechaYHora().format(DateTimeFormatter.ofPattern("HH:mm")) + System.getProperty("line.separator") +
					"Hora de salida: " + reserva.fechaYHora().plusHours(reserva.duracion()).format(DateTimeFormatter.ofPattern("HH:mm")) + System.getProperty("line.separator") +
					"Tiempo total de ocupación: " + reserva.duracion() + " hora" + ((reserva.duracion() > 1) ? ("s") : ("")) + System.getProperty("line.separator") +
					"Recursos disponibles: " + System.getProperty("line.separator")
					;

			for(SalaYRecurso syr: this._salasYRecursos) {
				if(reserva.idSala() == syr.idSala()) {
					recurso = this._recursos.get(this.buscarRecurso(syr.idRecurso()));

					res += recurso.nombre() + " (" + recurso.descripcion() + ")" + System.getProperty("line.separator");
				}
			}

			return res;
		}
		else {
			return "";
		}
	}


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
	 * @return										ArrayList&lt;Integer&gt;		Lista de IDs de los recursos cargada en el gestor
	 */

	@Override
	public ArrayList<Integer> obtenerRecursos() {
		ArrayList<Integer> res = new ArrayList<Integer>();

		for(Recurso recurso: this._recursos) {
			res.add(recurso.id());
		}

		return res;
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
		int posReserva = this.buscarReserva(idReserva, false);

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


	/**
	 * Pre-reservador de salas
	 * Crea una nueva reserva en estado de espera de confirmación
	 *
	 * @param		idAlumno						int								ID del alumno asociado a la reserva
	 * @param		idSala							int								ID de la sala asociada a la reserva
	 * @param		alumnos							int								Número de alumnos que disfrutarán la reserva
	 * @param		asignatura						String							Asignatura para la que se ha realizado la reserva
	 * @param		duracion						int								Duración (en horas) de la reserva
	 * @param		fechaYHora						LocalDateTime					Fecha y hora de la reserva
	 *
	 * @return										int								ID de la reserva
	 */

	@Override
	public int preReservarSala(int idAlumno, int idSala, int alumnos, String asignatura, int duracion, LocalDateTime fechaYHora) {
		Reserva nueva = new Reserva(this._reservas.get(this._reservas.size() - 1).id() + 1, idAlumno, idSala, alumnos, asignatura, duracion, fechaYHora);

		this._reservas.add(nueva);

		return nueva.id();
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
	public boolean salaLibre(int idAlumno, int idSala, LocalDateTime fechaYHora, int duracion) {
		ArrayList<Reserva>	reservas = new ArrayList<Reserva>();

		for(Reserva r: this._reservas) {
			if(
					(idSala == r.idSala()) &&
					LocalDateTime.now().isBefore(r.fechaYHora().plusHours(r.duracion())) &&
					!(fechaYHora.isAfter(r.fechaYHora().plusHours(r.duracion()))) &&
					!(fechaYHora.plusHours(duracion).isBefore(r.fechaYHora())) &&
					((idAlumno == r.idAlumno()) && !(r.suspendida()))
					) {
				reservas.add(r);
			}
		}

		return (reservas.size() == 0);
	}


	/**
	 * Método para reanudar una reserva en suspensión
	 * Si la modificación de una reserva ha sido cancelada, es necesario revertirla a su estado normal
	 *
	 * @param		idUsuario						int								ID del usuario que lo solicita
	 * @param		idReserva						int								ID de la reserva a reanudar
	 *
	 * @return										int								ID de la reserva a reanudada (-1 si no encontrada, -2 si la reserva no pertenece al usuario solicitado)
	 */

	@Override
	public int reanudarReserva(int idUsuario, int idReserva) {
		int		posReserva = this.buscarReserva(idReserva, false);
		Reserva	reserva;

		if(posReserva != -1) {
			reserva = this._reservas.get(posReserva);

			if(reserva.idAlumno() == idUsuario) {
				reserva.suspendida(false);

				return idReserva;
			}
			else {
				return -2;
			}
		}
		else {
			return -1;
		}
	}


	/**
	 * Método para poner una reserva en suspensión
	 * Cuando una reserva está siendo modificada, es necesario dejar el "hueco" de la misma libre, para poder crear otra en su lugar
	 *
	 * @param		idUsuario						int								ID del usuario que lo solicita
	 * @param		idReserva						int								ID de la reserva a suspender
	 *
	 * @return										int								ID de la reserva a suspendida (-1 si no encontrada, -2 si la reserva no pertenece al usuario solicitado)
	 */

	@Override
	public int suspenderReserva(int idUsuario, int idReserva) {
		int		posReserva = this.buscarReserva(idReserva, false);
		Reserva	reserva;

		if(posReserva != -1) {
			reserva = this._reservas.get(posReserva);

			if(reserva.idAlumno() == idUsuario) {
				reserva.suspendida(true);

				return idReserva;
			}
			else {
				return -2;
			}
		}
		else {
			return -1;
		}
	}
}
