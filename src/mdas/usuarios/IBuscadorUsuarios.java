package mdas.usuarios;

import java.util.List;

import mdas.usuarios.Usuario;


/**
 * Interfaz IBuscadorUsuarios
 * Interfaz de búsqueda de usuarios
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			06/05/2020
 * @version			1.0.2
 */

public interface IBuscadorUsuarios {
	/**
	 * Método abstracto de búsqueda de usuarios
	 * Busca en la lista dada el usuario recibido
	 *
	 * @param		usuarios						List&lt;Usuario&gt;				Lista de usuarios
	 * @param		dni_buscado						int								DNI del usuario buscado
	 *
	 * @return										int								Su posición en la lista (-1 si no se ha encontrado)
	 */

	abstract public int buscarUsuario(List<Usuario> usuarios, int dni_buscado);
}
