package mdas.p2.administradorusuarios;


import mdas.p2.gestorusuariomgr.UsuarioMgr;


/**
 * Clase AdministradorUsuarios
 * Componente de gestión de usuarios del sistema
 * Implementa la interfaz IIniciarSesion
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			24/05/2020
 * @version			1.2.0
 */

public class AdministradorUsuarios implements IIniciarSesion {
	private UsuarioMgr _gu;


	/**
	 * Constructor de clase
	 * Inicializa el gestor de usuarios
	 *
	 * @param		archivoUsuarios					String							Ruta del archivo donde se encuentran los usuarios
	 */

	public AdministradorUsuarios(String archivoUsuarios) {
		this._gu = UsuarioMgr.getInstance(archivoUsuarios);
	}


	/**
	 * Comprobador de alumnos
	 * Comprueba si la ID recibida es de un alumno
	 *
	 * @param		idUsuario						int								ID del usuario
	 *
	 * @return										boolean							Si es o no un alumno
	 */

	@Override
	public boolean alumno(int idUsuario) {
		return (this._gu.buscarAlumno(idUsuario) != null);
	}


	/**
	 * Comprobador de empleados
	 * Comprueba si la ID recibida es de un empleado
	 *
	 * @param		idUsuario						int								ID del usuario
	 *
	 * @return										boolean							Si es o no un empleado
	 */

	@Override
	public boolean empleado(int idUsuario) {
		return (this._gu.buscarEmpleado(idUsuario) != null);
	}


	/**
	 * Método para iniciar la sesión
	 * Se comunica con el servidor externo (RADIUS, por ejemplo) para iniciar la sesión
	 *
	 * @return										int								ID del usuario
	 */


	@Override
	public int iniciarSesion() {
		return this._gu.iniciarSesion();
	}


	/**
	 * Observador del nombre de un usuario
	 *
	 * @param		idUsuario						int								ID del usuario
	 *
	 * @return										String							Nombre del usuario ("" si no encontrado)
	 */

	@Override
	public String nombre(int idUsuario) {
		return this._gu.nombre(idUsuario);
	}
}
