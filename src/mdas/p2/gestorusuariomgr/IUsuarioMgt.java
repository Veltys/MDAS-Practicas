package mdas.p2.gestorusuariomgr;


/**
 *
 * Interfaz IUsuarioMgt
 * Interfaz del componente UsuarioMgr
 *
 * @author		Unai Friscia Pérez (unaif)
 * @date		07/06/2020
 * @version		1.3.0
 */

public interface IUsuarioMgt {
	abstract public Alumno		buscarAlumno(int idAlumno);
	abstract public Empleado	buscarEmpleado(int idEmpleado);
	abstract public boolean		enviarNotificacion(int idUsuario, String Mensaje);
	abstract public int			iniciarSesion();
	abstract public String		nombre(int idUsuario);
}
