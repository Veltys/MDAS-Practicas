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
	 * Método de búsqueda sencuencial de usuarios
	 * Busca secuencialmente en la lista dada el usuario recibido
	 * 
	 * @param		usuarios						List<Usuario>					Lista de usuarios
	 * @param		buscado							Usuario							Usuario buscado
	 * 
	 * @return										int								Su posición en la lista (-1 si no encontrado)
	 */

	abstract public int buscadorSeqUsuarios(List<Usuario> usuarios, Usuario buscado);


	/**
	 * Método de búsqueda dicotómica de usuarios
	 * Busca dicotómicamente en la lista dada el usuario recibido
	 * 
	 * @param		usuarios						List<Usuario>					Lista de usuarios
	 * @param		buscado							Usuario							Usuario buscado
	 * 
	 * @return										int								Su posición en la lista (-1 si no encontrado)
	 */

	abstract public int buscadorDicUsuarios(List<Usuario> usuarios, Usuario buscado);
}
