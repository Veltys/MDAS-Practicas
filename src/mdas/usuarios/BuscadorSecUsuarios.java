package mdas.usuarios;

import java.util.List;

import mdas.usuarios.Usuario;


/**
 * Clase BuscadorSecUsuario
 * Realiza una búsqueda secuencial en una lista de usuarios
 * 
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			03/05/2020
 * @version			1.0.0
 */

public class BuscadorSecUsuarios implements IBuscadorUsuarios {
	/**
	 * Método de búsqueda de secuencial de usuarios
	 * Busca secuencialmente en la lista dada el usuario recibido
	 * 
	 * @param		usuarios						List<Usuario>					Lista de usuarios
	 * @param		dni_buscado						int								DNI del usuario buscado
	 * 
	 * @return										int								Su posición en la lista (-1 si no se ha encontrado)
	 */

	@Override
	public int buscarUsuario(List<Usuario> usuarios, int dni_buscado) {
		boolean	encontrado	= false;													// "Bandera" que indica si ha sido encontrado el usuario
		int		i;
		int		tam_lista	= usuarios.size();											// Tamaño de la lista recibida (se calcula aquí para no obligar a su recálculo en cada ejecución del bucle

		for(i = 0; i < tam_lista; i++) {												// Iteración de la lista de manera secuencial
			if(usuarios.get(i).dni() == dni_buscado) {									// 	Comprobación de DNI
				encontrado = true;														//		Activación de la "bandera" en caso positivo

				break;																	//		Al haber sido encontrado no es necesario seguir iterando
			}
		}

		if(encontrado) {																// Si ha sido encontrado el usuario
			return i;																	// 	Se devuelve su posición
		}
		else {																			// Si no
			return -1;																	// 	-1 significa que no
		}
	}
}
