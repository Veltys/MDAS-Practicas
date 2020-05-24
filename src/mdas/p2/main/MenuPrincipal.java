package mdas.p2.main;


import java.util.Scanner;

import mdas.p2.administradorusuarios.AdministradorUsuarios;


/**
 * Clase MenuPrincipal
 * Clase MenuPrincipal del programa
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			24/05/2020
 * @version			0.2.1
 */

public class MenuPrincipal {
	final static private	String		ARCHIVOUSUARIOS	= "usuarios.csv";
	private static			Scanner		_entrada		= new Scanner(System.in);


	/**
	 * Método main de la clase (y del programa)
	 *
	 * @param		args							String							Argumentos recibidos por la línea de comandos
	 */

	public static void main(String[] args) {
		boolean					salir		= false;
		char					operacion;
		int						idUsuario	= -1;
		AdministradorUsuarios	au			= new AdministradorUsuarios(MenuPrincipal.ARCHIVOUSUARIOS);

		System.out.println("Bienvenido al Gestor de salas");

		while(!salir && ((idUsuario = au.iniciarSesion()) == -1)) {
			System.out.println("Ha introducido unas credenciales de usuario no válidas");
			System.out.print("¿Desea volverlo a intentar? [S/n]: ");

			operacion = Character.toUpperCase(MenuPrincipal._entrada.next().charAt(0));

			if(Character.toUpperCase(operacion) == 'N') {
				salir = true;
			}
		}

		while(!salir) {
			System.out.println("¡Bienvenido, " + au.nombre(idUsuario) + "!");

			if(au.alumno(idUsuario)) {
				System.out.println("El menú de operaciones es el siguiente:");

				// FIXME: Evitando, por ahora, un buclie infinito
				salir = true;
			}
			else if(au.empleado(idUsuario)) {
				System.out.println("El menú de operaciones es el siguiente:");

				// FIXME: Evitando, por ahora, un buclie infinito
				salir = true;
			}
			else {
				System.out.println("Lo sentimos, su perfil de usuario no le permite utilizar este servicio");

				salir = true;
			}
		}

		System.out.println("Gracias por utilizar nuestro sistema");
		System.out.println("Saliendo...");
	}

}
