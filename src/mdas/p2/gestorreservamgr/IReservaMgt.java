package mdas.p2.gestorreservamgr;


import java.util.ArrayList;

import mdas.p2.gestorreservamgr.Reserva;
import mdas.p2.gestorreservamgr.Sala;


/**
 * Interfaz IReservaMgt
 * Interfaz del componente GestorReservaMgr
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			21/05/2020
 * @version			1.3.0
 */

public interface IReservaMgt {
	abstract public ArrayList<Integer>	buscarIncidencias(int idReserva);
	abstract public ArrayList<Integer>	buscarReservas(int idAlumno);
	abstract public ArrayList<Integer>	buscarSala(int aforo, ArrayList<Integer> idsRecursos);
	abstract public int					buscarSancion(int idIncidencia);
	abstract public boolean				cargar(String archivoIncidencias, String archivoReservas, String archivoSalas, String archivoSanciones);
	abstract public boolean				confirmarRegistro(int idReserva);
	abstract public boolean				confirmarReserva(int idReserva);
	abstract public boolean				eliminarReserva(int id_reserva);
	abstract public String				describirSancion(int codigo);
	abstract public boolean				guardar(String archivoIncidencias, String archivoReservas, String archivoSalas, String archivoSanciones);
	abstract public int					obtenerAforoSala(int sala);
	abstract public Reserva				obtenerReserva(int idReserva);
	abstract public Sala				obtenerSala(int idSala);
	abstract public ArrayList<Integer>	obtenerTiposDeIncidencia();
	abstract public ArrayList<Integer>	obtenerTiposDeSala();
}
