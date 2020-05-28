package mdas.p2.gestorreservamgr;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

// import mdas.p2.gestorreservamgr.Reserva;
// import mdas.p2.gestorreservamgr.Sala;
// import mdas.p2.gestorreservamgr.TipoIncidencia;
// import mdas.p2.gestorreservamgr.TipoSala;


/**
 * Interfaz IReservaMgt
 * Interfaz del componente GestorReservaMgr
 *
 * @author		Rafael Carlos Méndez Rodríguez (i82meror)
 * @date		28/05/2020
 * @version		1.14.1
 */

public interface IReservaMgt {
	abstract public ArrayList<Integer>	buscarIncidencias(int idReserva);
	abstract public ArrayList<Integer>	buscarReservas(int idAlumno, boolean todas);
	abstract public ArrayList<Integer>	buscarSala(int aforo, ArrayList<Integer> idsRecursos);
	abstract public int					buscarSancion(int idIncidencia);
	abstract public boolean				confirmarRegistro(int idSala);
	abstract public boolean				confirmarReserva(int idReserva);
	abstract public boolean				eliminarReserva(int idReserva);
	abstract public String				mostrarRecurso(int idRecurso);
	abstract public String				mostrarReserva(int idReserva);
	abstract public int					obtenerAforoSala(int idSala);
	abstract public ArrayList<Integer>	obtenerRecursos();
	abstract public Reserva				obtenerReserva(int idReserva);
	abstract public Sancion				obtenerSancion(int idSancion);
	abstract public Sala				obtenerSala(int idSala);
	abstract public int					preRegistrarSala(String nombre, int aforo, int tipo, String ubicacion, ArrayList<Integer> recursos);
	abstract public int					preReservarSala(int idAlumno, int idSala, int alumos, String asignatura, int duracion, LocalDateTime fechaYHora);
	abstract public int					reanudarReserva(int idUsuario, int idReserva);
	abstract public boolean				salaLibre(int idAlumno, int idSala, LocalDateTime fechaYHora, int duracion);
	abstract public int					suspenderReserva(int idUsuario, int idReserva);


	/**
	 * Descriptor de sanciones
	 * Método estático para describir una sanción
	 *
	 * @param		codigoSancion					int								Código de sanción a describir
	 *
	 * @return										String							Descripción en texto de la sanción
	 */


	static public String describirSancion(int codigoSancion) {
		int							i			= 0;
		HashMap<Integer, String>	descripcion	= new HashMap<Integer, String>();

		descripcion.put(i++, "El alumno responsable de la reserva no se ha presentado a la hora de ésta");
		descripcion.put(i++, "El grupo ha generado molestias, ruidos o similar a otros grupos de otras salas vecinas");
		descripcion.put(i++, "El grupo ha ensuciado la sala");
		descripcion.put(i++, "El grupo no ha abandonado la sala a la hora acordada");
		descripcion.put(i++, "Se ha extraviado o deteriorado material prestado de poco valor (papeleras, rotuladores, lápices, borrador, etc.)");
		descripcion.put(i++, "Se ha extraviado o deteriorado material o recursos de medio valor (lámparas, mobiliario, pintura, cableado, etc.)");
		descripcion.put(i++, "Se ha extraviado o deteriorado material o recursos de mucho valor (equipos electrónicos especiales, proyectores, etc.)");
		// TODO: Añadir más si se me ocurren

		try {
			return descripcion.get(codigoSancion);
		} catch (IndexOutOfBoundsException e) {
			return "Otro suceso no contemplado (véase descripción)";
		}
	}


	/**
	 * Observador de los tipos de incidencia
	 * Itera los tipos de incidencia, recoge sus IDs y los devuelve
	 *
	 * @return										ArrayList&lt;Integer&gt;		Tipos de incidencias
	 */

	static public ArrayList<Integer> obtenerTiposDeIncidencia() {
		ArrayList<Integer> res = new ArrayList<Integer>();

		for(TipoIncidencia ti: TipoIncidencia.values()) {
			res.add(ti.id());
		}

		return res;
	}


	/**
	 * Observador de los tipos de sala
	 * Itera los tipos de sala, recoge sus IDs y los devuelve
	 *
	 * @return										ArrayList&lt;Integer&gt;		Tipos de salas
	 */

	static public ArrayList<Integer> obtenerTiposDeSala() {
		ArrayList<Integer> res = new ArrayList<Integer>();

		for(TipoSala ts: TipoSala.values()) {
			res.add(ts.id());
		}

		return res;
	}
}
