package mdas.p2.gestorreservamgr;


import java.util.ArrayList;
import java.util.HashMap;

import mdas.p2.gestorreservamgr.Reserva;
import mdas.p2.gestorreservamgr.Sala;


/**
 * Interfaz IReservaMgt
 * Interfaz del componente GestorReservaMgr
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			22/05/2020
 * @version			1.5.1
 */

public interface IReservaMgt {
	abstract public ArrayList<Integer>	buscarIncidencias(int idReserva);
	abstract public ArrayList<Integer>	buscarReservas(int idAlumno);
	abstract public ArrayList<Integer>	buscarSala(int aforo, ArrayList<Integer> idsRecursos);
	abstract public int					buscarSancion(int idIncidencia);
	abstract public boolean				cargar(String archivoIncidencias, String archivoRecursos, String archivoReservas, String archivoSalas, String archivoSalasYRecursos, String archivoSanciones);
	abstract public boolean				confirmarRegistro(int idSala);
	abstract public boolean				confirmarReserva(int idReserva);
	abstract public boolean				eliminarReserva(int idReserva);
	abstract public boolean				guardar(String archivoIncidencias, String archivoRecursos, String archivoReservas, String archivoSalas, String archivoSalasYRecursos, String archivoSanciones);
	abstract public int					obtenerAforoSala(int idSala);
	abstract public Reserva				obtenerReserva(int idReserva);
	abstract public Sala				obtenerSala(int idSala);
	abstract public ArrayList<Integer>	obtenerTiposDeIncidencia();
	abstract public ArrayList<Integer>	obtenerTiposDeSala();


	/**
	 * Descriptor de sanciones
	 * Método estático para describir una sanción
	 *
	 * @param		codigo							int								Código de sanción a describir
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
}
