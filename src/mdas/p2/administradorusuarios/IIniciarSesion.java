package mdas.p2.administradorusuarios;


/**
 * Interfaz IIniciarSesion
 * Interfaz del componente AdministradorUsuarios
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			23/05/2020
 * @version			1.0.0
 */

public interface IIniciarSesion {
	abstract public int		iniciarSesion();
	abstract public String	nombre(int idUsuario);
}
