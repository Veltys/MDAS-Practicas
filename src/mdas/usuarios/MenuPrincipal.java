package mdas.usuarios;


import java.util.Scanner;

//Comentada por no usarse aún // import mdas.usuarios.GestorUsuarios;


/**
 * Clase MenuPrincipal
 * Menú principal del gestor de usuarios
 * Se comunica con el usuario de la aplicación, permitiéndole realizar las operaciones que desee
 * 
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			06/05/2020
 * @version			0.2.1
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
		GestorUsuarios	gestorUsuarios	= new GestorUsuarios();

		@SuppressWarnings("resource")														// Eliminación del warning de Eclipse por no cerrar el Scanner
		Scanner			entrada			= new Scanner(System.in);							// Apertura del Scanner para lectura por teclado de datos 

		System.out.println("Bienvenido al Gestor de usuarios");
		System.out.println("El menú de operaciones es el siguiente:");

		do {
			System.out.println("Introduzca 'a' para añadir un usuario");
			System.out.println("Introduzca 'b' para borrar un usuario");
			System.out.println("Introduzca 'c' para cargar dos archivos de usuarios (alumnos y profesores)");
			System.out.println("Introduzca 'd' para ver los detalles de un usuario");
			System.out.println("Introduzca 'e' para visualizar estadísticas");
			System.out.println("Introduzca 'f' para buscar un usuario por su DNI");
			System.out.println("Introduzca 'g' para guardar dos archivos de usuarios (alumnos y profesores)");
			System.out.println("Introduzca 's' para salir");

			System.out.print("Por favor, seleccione una operación para continuar [abcdefgs]: ");

			operacion = Character.toUpperCase(entrada.next().charAt(0));					// Con recuperar el primer caracter vale

			switch(operacion) {
				case 'A':
					gestorUsuarios.addUsuario();
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
		} while(!salir);

		// entrada.close();																	// 	No se debe cerrar un Scanner(System.in) o se cerrará el propio System.in 🤷🏼‍♂️
	}
}
