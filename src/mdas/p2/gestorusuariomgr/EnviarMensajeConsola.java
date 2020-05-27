package mdas.p2.gestorusuariomgr;


/**
 *
 * Clase EnviarMensajeConsola
 * Clase que define la estrategia para enviar un mensaje mostrándolo por consola
 *
 * @author		Rafael Carlos Méndez Rodríguez
 * @date		27/05/2020
 * @version		1.0.0
 */

public class EnviarMensajeConsola extends EnviarMensaje {
	/**
	 * Método de envío de mensajes
	 * Envía un mensaje mostrándolo por consola
	 *
	 * @param		destinatario					String							Destinatario del mensaje
	 * @param		mensaje							String							Mensaje a enviar
	 *
	 * @return										boolean							Resultado de la operación
	 */

	@Override
	public boolean enviarMensaje(String destinatario, String mensaje) {
		System.out.println("Mensaje para el usuario " + destinatario + ": " + mensaje);

		return true;
	}
}
