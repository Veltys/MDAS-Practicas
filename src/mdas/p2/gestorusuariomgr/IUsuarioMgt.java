package mdas.p2.gestorusuariomgr;


/**
 *
 * Interfaz IUsuarioMgt
 * Interfaz del componente UsuarioMgr
 *
 * @author	Unai Friscia Pérez (unaif)
 * @date	23/05/2020
 * @version	1.0.2
 */

public interface IUsuarioMgt {
	abstract public int iniciarSesion();
	abstract public Alumno buscarAlumno(int idAlumno);
	abstract public boolean enviarNotificacion(int idUsuario, String Mensaje);
	abstract public void mostrarMensaje(String mensaje);
	abstract public boolean cargar(String ficheroUsuarios);
}
