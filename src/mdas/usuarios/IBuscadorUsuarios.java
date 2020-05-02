package mdas.usuarios;

import java.util.List;

import mdas.usuarios.Usuario;


/**
 * Interfaz IBuscadorUsuarios
 * Interfaz de búsqueda de usuarios
 * 
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			02/05/2020
 * @version			1.0.0
 */

public interface IBuscadorUsuarios {
	/**
	 * Método de búsqueda de usuarios
	 * Busca en la lista dada el usuario recibido
	 * 
	 * @param		usuarios						List<Usuario>					Lista de usuarios
	 * 
	 * @return										int								Su posición en la lista (-1 si no encontrado)
	 */

	abstract public int buscarUsuario(List<Usuario> usuarios);
}
