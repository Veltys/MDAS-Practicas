package mdas.p2.gestorusuariomgr;


/**
 *
 * Interfaz IUsuarioMgt
 * Interfaz del componente UsuarioMgr
 *
 * @author		Unai Friscia Pérez (unaif)
 * @date		26/05/2020
 * @version		1.2.1
 */

public interface IUsuarioMgt {
	abstract public Alumno		buscarAlumno(int idAlumno);
	abstract public Empleado	buscarEmpleado(int idEmpleado);
	abstract public boolean		enviarNotificacion(int idUsuario, String Mensaje);
	abstract public int			iniciarSesion();
	abstract public void		mostrarMensaje(String mensaje);
	abstract public String		nombre(int idUsuario);
}
