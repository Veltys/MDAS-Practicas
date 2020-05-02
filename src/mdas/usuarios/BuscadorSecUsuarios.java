package mdas.usuarios;

import java.util.List;

import mdas.usuarios.Usuario;


/**
 * Clase BuscadorSecUsuario
 * Realiza una búsqueda secuencial en una lista de usuarios
 * 
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			01/05/2020
 * @version			0.1.0
 */

public class BuscadorSecUsuarios implements IBuscadorUsuarios {
	private int _buscado;


	/**
	 * Constructor de clase
	 * Inicia el sistema de búsqueda
	 * 
	 * @param		buscado							int								DNI del usuario a buscar
	 */

	public BuscadorSecUsuarios(int buscado) {
		this._buscado = buscado;
	}


	@Override
	public int buscarUsuario(List<Usuario> usuarios) {
		// TODO Auto-generated method stub
		return 0;
	}
}
