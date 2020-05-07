package mdas.usuarios;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import mdas.usuarios.GestorUsuarios;
import mdas.usuarios.BuscadorDicUsuarios;
import mdas.usuarios.BuscadorSecUsuarios;


/**
 * Clase MenuPrincipal
 * Menú principal del gestor de usuarios
 * Se comunica con el usuario de la aplicación, permitiéndole realizar las operaciones que desee
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			07/05/2020
 * @version			1.0.0
 */

public class MenuPrincipal {
	private static			Scanner		entrada				= new Scanner(System.in);			// Apertura del Scanner para lectura por teclado de datos


	/**
	 * Método main
	 * Método principal de la clase
	 *
	 * @param		args															Argumentos recibidos de la línea de comandos
	 */

	public static void main(String[] args) {
		boolean					ok_cargar;														// Confirmación de cargado de usuarios
		boolean					salir				= false;									// "Bandera" que indica si se ha activado el evento de salida
		char					operacion;														// Operación a realizar
		int						borrado;														// Posición del usuario a borrar
		String					archivo_alumnos		= null;										// Ruta al archivo de alumnos
		String					archivo_profesores	= null;										// Ruta al archivo de profesores
		String					ok_borrar;														// Confirmación de borrado de usuario
		List<IBuscadorUsuarios>	buscadores		= new ArrayList<IBuscadorUsuarios>(); 			// Buscadores de usuarios
		GestorUsuarios			gestorUsuarios		= new GestorUsuarios();						// Gestor de usuarios

		buscadores.add(new BuscadorDicUsuarios());												// Buscador dicotómico de usuarios
		buscadores.add(new BuscadorSecUsuarios());												// Buscador secuencial de usuarios

		System.out.println("Bienvenido al Gestor de usuarios");
		System.out.println("El menú de operaciones es el siguiente:");

		do {
			System.out.println("Introduzca 'a' para añadir un usuario");

			if(gestorUsuarios.numUsuarios() > 0) {
				System.out.println("Introduzca 'b' para borrar un usuario");
			}

			System.out.println("Introduzca 'c' para cargar dos archivos de usuarios (alumnos y profesores)");

			if(gestorUsuarios.numUsuarios() > 0) {
				System.out.println("Introduzca 'd' para ver los detalles de un usuario");
				System.out.println("Introduzca 'e' para visualizar estadísticas");
				System.out.println("Introduzca 'f' para buscar un usuario por su DNI");
				System.out.println("Introduzca 'g' para guardar dos archivos de usuarios (alumnos y profesores)");
			}

			System.out.println("Introduzca 's' para salir");

			System.out.print("Por favor, seleccione una operación para continuar [");

			if(gestorUsuarios.numUsuarios() > 0) {
				System.out.print("abcdefgs]: ");
			}
			else {
				System.out.print("acs]: ");
			}

			operacion = Character.toUpperCase(MenuPrincipal.entrada.next().charAt(0));						// Con recuperar el primer caracter vale

			switch(operacion) {
			case 'A':
				gestorUsuarios.addUsuario();

				break;

			case 'C':
				System.out.println("Para cargar la lista de usuarios es necesario proporcionar dos nombres de archivo");

				MenuPrincipal.entrada.nextLine();											// Avance del Scanner para permitir otra lectura

				if(gestorUsuarios.numUsuarios() > 0) {
					System.out.print("La lista de usuarios actualmente cargada será borrada. ¿Está seguro? [s/N]: ");

					ok_borrar = MenuPrincipal.entrada.next();

					if(Character.toUpperCase(ok_borrar.charAt(0)) == 'S') {
						ok_cargar = true;
					}
					else {
						ok_cargar = false;
					}
				}
				else {
					ok_cargar = true;
				}

				if(ok_cargar &&
						!(
								("".equals(
										archivo_alumnos = MenuPrincipal.obtenerNombreArchivo("alumnos")
										))
								||
								("".equals(
										archivo_profesores = MenuPrincipal.obtenerNombreArchivo("profesores")
										))
								)
						) {
					gestorUsuarios.loadUsuarios(archivo_alumnos, archivo_profesores);
				}
				else {
					System.out.println("Operación cancelada");
				}

				break;

			case 'S':
				salir = true;

				System.out.println("¡Gracias por utilizar nuestro sistema!");
				System.out.println("Saliendo...");

				break;

			default:
				if(gestorUsuarios.numUsuarios() > 0) {
					switch(operacion) {
					case 'B':
						borrado = MenuPrincipal.buscarUsuario(gestorUsuarios);

						if(borrado > -1) {
							System.out.print("El usuario anteriormente mostrado será borrado. ¿Está seguro? [s/N]: ");

							ok_borrar = MenuPrincipal.entrada.next();

							System.out.print("El usuario ");

							if(Character.toUpperCase(ok_borrar.charAt(0)) == 'S') {
								gestorUsuarios.removeUsuario(borrado);
							}
							else {
								System.out.print("no ");
							}

							System.out.println("ha sido borrado del sistema");
						}

						break;

					case 'E':
						gestorUsuarios.printEstadisticas();

						break;

					case 'D':																		// Alterado el orden alfabético a propósito
						System.out.println("Para visualizar los detalles de un usuario es necesario buscarlo");

						// break;																	// Comentado a propósito

					case 'F':
						MenuPrincipal.buscarUsuario(gestorUsuarios);

						break;

					case 'G':
						System.out.println("Para guardar la lista de usuarios es necesario proporcionar dos nombres de archivo");

						MenuPrincipal.entrada.nextLine();											// Avance del Scanner para permitir otra lectura

						if(
								!(
										("".equals(
												archivo_alumnos = MenuPrincipal.obtenerNombreArchivo("alumnos")
												))
										||
										("".equals(
												archivo_profesores = MenuPrincipal.obtenerNombreArchivo("profesores")
												))
										)
								) {
							gestorUsuarios.saveUsuarios(archivo_alumnos, archivo_profesores);

							System.out.println("Operación realizada con éxito");
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


	/**
	 * Buscador de usuarios
	 * Busca un usuario en el gestor dado
	 *
	 * @param		gestorUsuarios					GestorUsuarios					Gestor de usuarios en el que buscar
	 *
	 * @return										int								Posición en la que se halla en la lista interna del gestor
	 */

	private static int buscarUsuario(GestorUsuarios gestorUsuarios) {
		int						dni					= -1;										// DNI (sin letra) del usuario a buscar
		int						encontrado;														// Posición del usuario buscado en la lista (-1 si no se ha encontrado)
		List<IBuscadorUsuarios>	buscadores		= new ArrayList<IBuscadorUsuarios>(); 			// Buscadores de usuarios
		Random					aleatorio			= new Random();								// Generador de números aleatorios

		buscadores.add(new BuscadorDicUsuarios());												// Buscador dicotómico de usuarios
		buscadores.add(new BuscadorSecUsuarios());												// Buscador secuencial de usuarios

		do {
			System.out.print("Introduzca el DNI (sin letra) del usuario a buscar: ");

			try {
				dni = MenuPrincipal.entrada.nextInt();
			}
			catch(InputMismatchException e) {
				MenuPrincipal.entrada.nextLine();												// Avance del Scanner para permitir otra lectura

				System.out.println("El DNI introducido no es válido");
				System.out.println("Por favor, inténtelo de nuevo y recuerde introducir un DNI sin su letra");
			}
		} while(dni == -1);

		encontrado = gestorUsuarios.searchUsuario(buscadores.get(aleatorio.nextInt(1)), dni);

		if(encontrado > -1) {
			System.out.print("Encontrado: ");

			gestorUsuarios.printUsuario(encontrado);
		}
		else {
			System.out.println("El usuario con DNI " + String.format("%08d", dni) + " no ha sido hallado en el gestor de usuarios");
		}

		return encontrado;
	}


	/**
	 * Obtenedor de un nombre de archivo
	 * Obtiene un nombre de archivo para el tipo configurado
	 *
	 * @param		tipo							String							Tipo de archivo a obtener
	 *
	 * @return										String							Ruta obtenida (o "" si se canceló el proceso)
	 */

	private static String obtenerNombreArchivo(String tipo) {
		boolean					ok_archivo			= false;									// "Bandera" de validación del archivo de alumnos / profesores
		String					archivo				= null;										// Ruta al archivo

		do {
			System.out.print("Introduzca la ruta del archivo de " + tipo + ": ");

			archivo = MenuPrincipal.entrada.nextLine();

			if("".equals(archivo)) {
				System.out.println("Es necesario que proporcione un nombre de archivo");

				System.out.print("¿Desea volver a intentarlo? [s/N]: ");

				archivo = MenuPrincipal.entrada.next();

				MenuPrincipal.entrada.nextLine();												// Avance del Scanner para evitar leer ""

				if(Character.toUpperCase((archivo).charAt(0)) == 'S') {
					ok_archivo = false;
				}
				else {
					ok_archivo = false;

					break;
				}
			}
			else {
				ok_archivo = true;
			}
		} while(!ok_archivo);

		if(ok_archivo) {
			return archivo;
		}
		else {
			return "";
		}
	}
}
