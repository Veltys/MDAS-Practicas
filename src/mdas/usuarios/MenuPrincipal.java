package mdas.usuarios;


import java.util.Scanner;

//Comentada por no usarse aún // import mdas.usuarios.GestorUsuarios;


/**
 * Clase MenuPrincipal
 * Menú principal del gestor de usuarios
 * Se comunica con el usuario de la aplicación, permitiéndole realizar las operaciones que desee
 * 
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			07/05/2020
 * @version			0.4.0
 */

public class MenuPrincipal {
	/**
	 * Método main
	 * Método principal de la clase
	 * 
	 * @param		args															Argumentos recibidos de la línea de comandos
	 */

	public static void main(String[] args) {
		final boolean	DEBUG				= true;											// Constante de depuración

		boolean			ok_archivo;															// "Bandera" de validación del archivo de alumnos / profesores
		boolean			salir				= false;										// "Bandera" que indica si se ha activado el evento de salida
		char			operacion;															// Operación a realizar
		int				i;
		int				usuarios			= 0;											// Contador de usuarios cargados
		String			archivo_alumnos		= null;											// Ruta al archivo de alumnos
		String			archivo_profesores	= null;											// Ruta al archivo de profesores
		GestorUsuarios	gestorUsuarios		= new GestorUsuarios();							// Gestor de usuarios

		@SuppressWarnings("resource")														// Eliminación del warning de Eclipse por no cerrar el Scanner
		Scanner			entrada			= new Scanner(System.in);							// Apertura del Scanner para lectura por teclado de datos 

		if(DEBUG) {
			gestorUsuarios.addAlumno("45746293Y", "Rafael Carlos Méndez Rodíguez", null, "G. I. I. (S.)", 4);
			gestorUsuarios.addProfesor("00000000T", "Rafael Barbudo Lunar", null, 30, Categorias.values()[3]);

			usuarios = 2;
		}

		System.out.println("Bienvenido al Gestor de usuarios");
		System.out.println("El menú de operaciones es el siguiente:");

		do {
			System.out.println("Introduzca 'a' para añadir un usuario");

			if(usuarios > 0) {
				System.out.println("Introduzca 'b' para borrar un usuario");
			}

			System.out.println("Introduzca 'c' para cargar dos archivos de usuarios (alumnos y profesores)");

			if(usuarios > 0) {
				System.out.println("Introduzca 'd' para ver los detalles de un usuario");
				System.out.println("Introduzca 'e' para visualizar estadísticas");
				System.out.println("Introduzca 'f' para buscar un usuario por su DNI");
				System.out.println("Introduzca 'g' para guardar dos archivos de usuarios (alumnos y profesores)");
			}

			System.out.println("Introduzca 's' para salir");

			System.out.print("Por favor, seleccione una operación para continuar [");

			if(usuarios > 0) {
				System.out.print("abcdefgs]: ");
			}
			else {
				System.out.print("acs]: ");
			}

			operacion = Character.toUpperCase(entrada.next().charAt(0));					// Con recuperar el primer caracter vale

			switch(operacion) {
				case 'A':
					gestorUsuarios.addUsuario();

					usuarios++;
				break;

				case 'C':
					// TODO: Implementar
				break;

				case 'S':
					salir = true;

					System.out.println("¡Gracias por utilizar nuestro sistema!");
					System.out.println("Saliendo...");
				break;

				default:
					if(usuarios > 0) {
						switch(operacion) {
							case 'B':
								// TODO: Implementar

								usuarios--;
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
								System.out.println("Para guardar la lista de usuarios es necesario proporcionar dos nombres de archivo");

								for(i = 0; i < 2; i++) {
									System.out.print("Introduzca la ruta del archivo de " + ((i == 0) ? ("alumnos") : ("profesores")) + ": ");
	
									entrada.nextLine();														// Avance del Scanner para evitar leer ""
	
									do {
										if(i == 0) {
											archivo_alumnos = entrada.nextLine();
										}
										else {
											archivo_profesores = entrada.nextLine();
										}
	
										if("".equals((i == 0) ? (archivo_alumnos) : (archivo_profesores))) {
											System.out.println("Es necesario que proporcione un nombre de archivo");
	
											System.out.print("¿Desea volver a intentarlo? [s/N]: ");
	
											if(i == 0) {
												archivo_alumnos = entrada.next();
											}
											else {
												archivo_profesores = entrada.next();
											}
	
											entrada.nextLine();												// Avance del Scanner para evitar leer ""
	
											if(Character.toUpperCase(((i == 0) ? (archivo_alumnos) : (archivo_profesores)).charAt(0)) == 'S') {
												ok_archivo = false;
											}
											else {
												ok_archivo = true;

												break;
											}
										}
										else {
											ok_archivo = true;
										}
									} while(!ok_archivo);
								}

								if(!("".equals(archivo_alumnos) || "".equals(archivo_profesores))) {
									gestorUsuarios.saveUsuarios(archivo_alumnos, archivo_profesores);
								}
								else {
									System.out.println("Operación cancelada");
								}
							break;

							default:
								System.out.println("La operación seleccionada (" + operacion + ") es incorrecta");
								System.out.println("Por favor, seleccione una válida de entre las siguientes opciones:");
							break;
						}
					}
					else {
						System.out.println("La operación seleccionada (" + operacion + ") es incorrecta o no está disponible en este momento");
						System.out.println("Por favor, seleccione una válida de entre las siguientes opciones:");
					}
				break;
			}

			System.out.println();
} while(!salir);

		// entrada.close();																	// 	No se debe cerrar un Scanner(System.in) o se cerrará el propio System.in 🤷🏼‍♂️
	}
}
