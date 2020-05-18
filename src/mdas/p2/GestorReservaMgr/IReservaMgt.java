package mdas.p2.GestorReservaMgr;


import java.util.ArrayList;

import mdas.p2.GestorReservaMgr.Reserva;
import mdas.p2.GestorReservaMgr.Sala;


/**
 * Interfaz IReservaMgt
 * Interfaz del componente GestorReservaMgr
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			18/05/2020
 * @version			1.0.0
 */

public interface IReservaMgt {
	public abstract ArrayList<Integer>	BuscarIncidencias(int id_reserva);
	public abstract ArrayList<Integer>	BuscarReservas(int id_alumno);
	public abstract ArrayList<Integer>	BuscarSala(int aforo, int ids_recursos);
	public abstract ArrayList<Integer>	BuscarSancion(int id_incidencia);
	public abstract boolean				ConfirmarRegistro(int id_reserva);
	public abstract boolean				ConfirmarReserva(int id_reserva);
	public abstract String				DescribirSancion(int codigo);
	public abstract Reserva				ObtenerReserva(int id_reserva);
	public abstract Sala				ObtenerSala(int id_sala);
	public abstract ArrayList<Integer>	ObtenerTipos();
}
