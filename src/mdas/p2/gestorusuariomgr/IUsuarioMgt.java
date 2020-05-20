package mdas.p2.gestorusuariomgr;

/**
 * 
 * Interfaz IUsuarioMgt
 * Interfaz del componente UsuarioMgr
 * 
 * @author unaif
 * @date 20/05/2020
 * @version 1.0.0
 */

public interface IUsuarioMgt {
	abstract public int iniciarSesion();
	abstract public Alumno buscaralumno(int id_alumno);
	abstract public boolean enviarnotificacion(int id_usuario, String Mensaje);
	abstract public void mostrarmensaje(String mensaje);
}
