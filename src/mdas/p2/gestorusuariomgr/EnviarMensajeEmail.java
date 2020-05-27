package mdas.p2.gestorusuariomgr;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Clase EnviarMensajeEmail
 * Clase que define la estrategia para enviar un mensaje mostrándolo por email
 *
 * @author		Rafael Carlos Méndez Rodríguez
 * @date		27/05/2020
 * @version		1.0.0
 */

public class EnviarMensajeEmail extends EnviarMensaje {
	private String	_asunto;
	private String	_emisor;
	private String	_servidor;


	/**
	 * Constructor de clase
	 * Inicializa las variables de clase
	 */

	public EnviarMensajeEmail() {
		this._asunto	= "Ha sido sancionado en la plataforma de reserva de salas de la UCO";
		this._emisor	= "i82meror@uco.es";
		this._servidor	= "mandarcorreo.uco.es";
	}


	/**
	 * Método de envío de mensajes
	 * Envía un mensaje mostrándolo por eMail
	 *
	 * @param		destinatario					String							Destinatario del mensaje
	 * @param		mensaje							String							Mensaje a enviar
	 *
	 * @return										boolean							Resultado de la operación
	 */

	@Override
	public boolean enviarMensaje(String destinatario, String mensaje) {
		MimeMessage	correo;
		Properties	propiedades	= System.getProperties();
		Session		sesion		= Session.getDefaultInstance(propiedades);

		propiedades.setProperty("mail.smtp.host", this._servidor);

		try {
			correo = new MimeMessage(sesion);
			correo.setFrom(new InternetAddress(this._emisor));
			correo.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			correo.setSubject(this._asunto);
			correo.setText(mensaje);

			Transport.send(correo);

			return true;
		}
		catch(MessagingException e) {
			System.out.println("Error: " + e.getMessage());

			return false;
		}
	}
}
