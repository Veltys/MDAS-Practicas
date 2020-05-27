package mdas.p2.gestorusuariomgr;


/**
 *
 * Interfaz IEnviarMensaje
 * Interfaz para la implementación del patrón Strategy en la funcionalidad de envío de mensajes
 *
 * @author		Rafael Carlos Méndez Rodríguez
 * @date		27/05/2020
 * @version		1.0.0
 */

public interface IEnviarMensaje {
	abstract public boolean enviarMensaje(String destinatario, String mensaje);
}
