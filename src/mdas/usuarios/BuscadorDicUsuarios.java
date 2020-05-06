package mdas.usuarios;

import java.util.List;

import mdas.usuarios.Usuario;


/**
 * Clase BuscadorSecUsuario
 * Realiza una búsqueda dicotómica en una lista de usuarios
 * 
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			06/05/2020
 * @version			1.0.1
 */

public class BuscadorDicUsuarios implements IBuscadorUsuarios {
	/**
	 * Método de búsqueda de dicotómica de usuarios
	 * Busca dicotómicamente en la lista dada el usuario recibido
	 * 
	 * @param		usuarios						List&lt;Usuario&gt;				Lista de usuarios
	 * @param		dni_buscado						int								DNI del usuario buscado
	 * 
	 * @return										int								Su posición en la lista (-1 si no se ha encontrado)
	 */

	@Override
	public int buscarUsuario(List<Usuario> usuarios, int dni_buscado) {
		int		tam_lista			= usuarios.size();														// Tamaño de la lista recibida (se calcula aquí para no obligar a su recálculo en cada ejecución del bucle
		int		mitad				= tam_lista / 2;														// Mitad del tamaño de la lista recibida
		int		dni_usuario_mitad	= usuarios.get(mitad).dni();											// DNI del usuario justo en mitad de la lista
		int		res;																						// Auxiliar para almacenar un resultado intermedio

		if(dni_usuario_mitad == dni_buscado) {																// Si se ha encontrado al usuario buscado
			return mitad;																					// 	Se devuelve su posición en la lista
		}
		else if(tam_lista == 1) {																			// Si el tamaño de la lista recibida es 1
			return -1;																						// 	No está el elemento buscado
		}
		else if(dni_buscado < dni_usuario_mitad) {															// Si no y el usuario buscado debe estar antes de la mitad
			return this.buscarUsuario(usuarios.subList(0, mitad - 1), dni_buscado);							// 	Se busca en la primera mitad de la lista
		}
		else /* if(dni_buscado > dni_usuario_mitad) */ {													// Si no y el usuario buscado debe estar después de la mitad
			res = mitad + this.buscarUsuario(usuarios.subList(mitad + 1, tam_lista - 1), dni_buscado);		// 	Se busca en la segunda mitad de la lista

			if(res != -1) {																					// Si finalmente se ha encontrado al usuario
				return mitad + res;																			// 	Se retorna su posición en la segunda mitad de la lista más la mitad de la misma
			}
			else {																							// Si no
				return -1;																					// 	-1 significa que no
			}
		}
	}
}
