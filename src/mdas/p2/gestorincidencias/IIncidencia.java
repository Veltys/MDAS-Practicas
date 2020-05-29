package mdas.p2.gestorincidencias;


import java.util.ArrayList;


/**
 * Interfaz IIncidencia
 * Interfaz del componente GestorIncidencias
 *
 * @author		Rafael Carlos Méndez Rodríguez
 * @date		29/05/2020
 * @version		1.0.0
 */


public interface IIncidencia {
	abstract public String				mostrarTipoDeIncidencia(int idTipoDeIncidencia);
	abstract public ArrayList<Integer>	obtenerTiposDeIncidencia();
	abstract public int					registrarIncidencia(int idReserva, String descripcion, int tipo);
}
