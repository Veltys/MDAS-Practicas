package mdas.p2.gestorusuariomgr;


/**
 * Clase EnviarMensaje
 * Clase abstracta para definir las estrategias para enviar un mensaje
 * Implementa la interfaz IEnviarMensaje
 *
 * @author		Rafael Carlos Méndez Rodríguez
 * @date		27/05/2020
 * @version		1.0.0
 */

abstract public class EnviarMensaje implements IEnviarMensaje {
	@Override
	abstract public boolean enviarMensaje(String destinatario, String mensaje);
}
