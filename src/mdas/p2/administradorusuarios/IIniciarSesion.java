package mdas.p2.administradorusuarios;


/**
 * Interfaz IIniciarSesion
 * Interfaz del componente AdministradorUsuarios
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			24/05/2020
 * @version			1.1.0
 */

public interface IIniciarSesion {
	abstract public int		iniciarSesion();
	abstract public boolean	alumno(int idUsuario);
	abstract public boolean	empleado(int idUsuario);
	abstract public String	nombre(int idUsuario);
}
