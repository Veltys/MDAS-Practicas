package mdas.usuarios;


import java.util.Scanner;

import mdas.usuarios.GestorUsuarios;


/**
 * Clase MenuPrincipal
 * Menú principal del gestor de usuarios
 * Se comunica con el usuario de la aplicación, permitiéndole realizar las operaciones que desee
 * 
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			06/05/2020
 * @version			0.1.0
 */

public class MenuPrincipal {
	/**
	 * Método main
	 * Método principal de la clase
	 * 
	 * @param		args															Argumentos recibidos de la línea de comandos
	 */

	public static void main(String[] args) {
		boolean			salir			= false;
		char			operacion;
		GestorUsuarios	_gestorUsuarios	= new GestorUsuarios();
		Scanner			entrada			= new Scanner(System.in);							// Apertura del scanner para lectura por teclado de datos

		System.out.println("Bienvenido al Gestor de usuarios");
		System.out.println("El menú de operaciones es el siguiente:");

		do {
			System.out.println("Presione 'a' para añadir un usuario");
			System.out.println("Presione 'b' para borrar un usuario");
			System.out.println("Presione 'c' para cargar dos archivos de usuarios (alumnos y profesores)");
			System.out.println("Presione 'd' para ver los detalles de un usuario");
			System.out.println("Presione 'e' para visualizar estadísticas");
			System.out.println("Presione 'f' para buscar un usuario por su DNI");
			System.out.println("Presione 'g' para guardar dos archivos de usuarios (alumnos y profesores)");
			System.out.println("Presione 's' para salir");
			System.out.println("Por favor, seleccione una operación para continuar [abcdefg]:");

			operacion = Character.toUpperCase(entrada.next().charAt(0));					// Con recuperar el primer caracter vale

			switch(operacion) {
				case 'A':
					// TODO: Implementar
				break;

				case 'B':
					// TODO: Implementar
				break;

				case 'C':
					// TODO: Implementar
				break;

				case 'D':
					// TODO: Implementar
				break;

				case 'E':
					// TODO: Implementar
				break;

				case 'F':
					// TODO: Implementar
				break;

				case 'G':
					// TODO: Implementar
				break;

				case 'S':
					salir = true;

					System.out.println("¡Gracias por utilizar nuestro sistema!");
					System.out.println("Saliendo...");
				break;

				default:
					System.out.println("La operación seleccionada (" + operacion + ") es incorrecta o no está disponible para usted");
					System.out.println("Por favor, seleccione una válida de entre las siguientes:");
				break;
			}
		} while(salir != true);

		entrada.close();
	}
}
