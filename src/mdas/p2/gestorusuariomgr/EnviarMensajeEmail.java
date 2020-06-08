package mdas.p2.gestorusuariomgr;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * Clase EnviarMensajeEmail
 * Clase que define la estrategia para enviar un mensaje mostrándolo por email
 *
 * @author		Rafael Carlos Méndez Rodríguez
 * @date		07/06/2020
 * @version		1.1.0
 */

public class EnviarMensajeEmail extends EnviarMensaje {
	private String	_asunto;
	private String	_contrasenya;
	private String	_emisor;
	private String	_puerto;
	private String	_servidor;
	private String	_usuario;


	/**
	 * Constructor de clase
	 * Inicializa las variables de clase
	 * @throws										IOException						Excepción relativa a la carga de las propiedades
	 */

	public EnviarMensajeEmail() throws IOException {
		super();

		InputStream	entrada;
		Properties	propiedades	= new Properties();

		entrada = new FileInputStream(System.getProperty("user.dir") + File.separatorChar + "config" + File.separatorChar + "email.properties");

		propiedades.load(entrada);

		entrada.close();

		this._asunto		= propiedades.getProperty("email.asunto");
		this._contrasenya	= propiedades.getProperty("email.contrasenya");
		this._emisor		= propiedades.getProperty("email.emisor");
		this._puerto		= propiedades.getProperty("email.puerto");
		this._servidor		= propiedades.getProperty("email.servidor");
		this._usuario		= propiedades.getProperty("email.usuario");
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
		String		contrasenya	= this._contrasenya;
		String		usuario		= this._usuario;

		MimeMessage	correo;
		Properties	propiedades	= System.getProperties();
		Session		sesion;
		Transport	transporte	= null;

		propiedades.setProperty("mail.smtp.host", this._servidor);
		propiedades.setProperty("mail.smtp.port", this._puerto);
		propiedades.setProperty("mail.smtp.auth", "true");

		propiedades.put("mail.smtp.socketFactory.port", this._puerto);
		propiedades.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		propiedades.put("mail.smtp.socketFactory.fallback", "true");
		propiedades.put("mail.smtp.starttls.enable", "true");

		sesion = Session.getDefaultInstance(propiedades, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usuario, contrasenya);
			}
		});

		try {
			transporte = sesion.getTransport();
		}
		catch (NoSuchProviderException e) {
			System.out.println("Error: " + e.getMessage());
		}

		if(transporte != null) {
			try {
				correo = new MimeMessage(sesion);
				correo.setFrom(new InternetAddress(this._emisor));
				correo.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
				correo.setSubject(this._asunto);
				correo.setText(mensaje, "UTF-8");

				transporte.connect();

				Transport.send(correo);

				transporte.close();

				return true;
			}
			catch(MessagingException e) {
				System.out.println("Error: " + e.getMessage());

				return false;
			}
		}
		else {
			return false;
		}
	}
}
