package mdas.p2.gestorreservamgr;


import java.util.ArrayList;

import mdas.p2.gestorreservamgr.Reserva;
import mdas.p2.gestorreservamgr.Sala;


/**
 * Interfaz IReservaMgt
 * Interfaz del componente GestorReservaMgr
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			18/05/2020
 * @version			1.0.0
 */

public interface IReservaMgt {
	public abstract ArrayList<Integer>	buscarIncidencias(int idReserva);
	public abstract ArrayList<Integer>	buscarReservas(int idAlumno);
	public abstract ArrayList<Integer>	buscarSala(int aforo, ArrayList<Integer> idsRecursos);
	public abstract int					buscarSancion(int idIncidencia);
	public abstract boolean				confirmarRegistro(int idReserva);
	public abstract boolean				confirmarReserva(int idReserva);
	public abstract String				describirSancion(int codigo);
	public abstract Reserva				obtenerReserva(int idReserva);
	public abstract Sala				obtenerSala(int idSala);
	public abstract ArrayList<Integer>	obtenerTipos();
}
