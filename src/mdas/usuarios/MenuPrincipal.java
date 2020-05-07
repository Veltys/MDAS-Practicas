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
 * @version			0.5.2
 */

public class MenuPrincipal {
	private final static	boolean		DEBUG				= true;								// Constante de depuración

	@SuppressWarnings("resource")																// Eliminación del warning de Eclipse por no cerrar el Scanner
	private static			Scanner		entrada				= new Scanner(System.in);			// Apertura del Scanner para lectura por teclado de datos


	/**
	 * Método main
	 * Método principal de la clase
	 *
	 * @param		args															Argumentos recibidos de la línea de comandos
	 */

	public static void main(String[] args) {
		boolean					ok_archivo			= false;									// "Bandera" de validación del archivo de alumnos / profesores
		boolean					salir				= false;									// "Bandera" que indica si se ha activado el evento de salida
		char					operacion;														// Operación a realizar
		int						borrado;														// Posición del usuario a borrar
		int						i;
		int						usuarios			= 0;										// Contador de usuarios cargados
		String					archivo_alumnos		= null;										// Ruta al archivo de alumnos
		String					archivo_profesores	= null;										// Ruta al archivo de profesores
		String					ok_borrar;														// Confirmación de borrado de usuario
		List<IBuscadorUsuarios>	buscadores		= new ArrayList<IBuscadorUsuarios>(); 			// Buscadores de usuarios
		GestorUsuarios			gestorUsuarios		= new GestorUsuarios();						// Gestor de usuarios

		buscadores.add(new BuscadorDicUsuarios());												// Buscador dicotómico de usuarios
		buscadores.add(new BuscadorSecUsuarios());												// Buscador secuencial de usuarios

		if(MenuPrincipal.DEBUG) {
			gestorUsuarios.addAlumno("45746293Y", "Rafael Carlos Méndez Rodíguez", null, "G. I. I. (S.)", 4);
			gestorUsuarios.addAlumno("00000001R", "Perico el de los Palotes Duros", null, "Vendedor de chuches", 1);
			gestorUsuarios.addAlumno("00000003A", "Naruto es Sinónimo de Relleno", null, "Anime malo", 3);
			gestorUsuarios.addProfesor("00000000T", "Rafael Barbudo Lunar", null, 30, Categorias.values()[3]);
			gestorUsuarios.addProfesor("00000002W", "Alguien Más de Relleno", null, 30, Categorias.values()[0]);

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

			operacion = Character.toUpperCase(MenuPrincipal.entrada.next().charAt(0));						// Con recuperar el primer caracter vale

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
						borrado = MenuPrincipal.buscarUsuario(gestorUsuarios);

						if(borrado > -1) {
							System.out.print("El usuario anteriormente mostrado será borrado. ¿Está seguro? [s/N]: ");

							ok_borrar = MenuPrincipal.entrada.next();

							System.out.print("El usuario ");

							if(Character.toUpperCase(ok_borrar.charAt(0)) == 'S') {
								gestorUsuarios.removeUsuario(borrado);

								usuarios--;
							}
							else {
								System.out.print("no ");
							}

							System.out.println("ha sido borrado del sistema");
						}

						break;

					case 'E':
						// TODO: Implementar

						break;

					case 'D':																		// Alterado el orden alfabético a propósito
						System.out.println("Para visualizar los detalles de un usuario es necesario buscarlo");

						// break;																	// Comentado a propósito

					case 'F':
						MenuPrincipal.buscarUsuario(gestorUsuarios);

						break;

					case 'G':
						System.out.println("Para guardar la lista de usuarios es necesario proporcionar dos nombres de archivo");

						for(i = 0; i < 2; i++) {
							if((i == 0) || ((i == 1) && ok_archivo)) {
								do {
									System.out.print("Introduzca la ruta del archivo de " + ((i == 0) ? ("alumnos") : ("profesores")) + ": ");

									if(i == 0) {
										MenuPrincipal.entrada.nextLine();							// Avance del Scanner para evitar leer ""

										archivo_alumnos = MenuPrincipal.entrada.nextLine();
									}
									else {
										archivo_profesores = MenuPrincipal.entrada.nextLine();
									}

									if("".equals((i == 0) ? (archivo_alumnos) : (archivo_profesores))) {
										System.out.println("Es necesario que proporcione un nombre de archivo");

										System.out.print("¿Desea volver a intentarlo? [s/N]: ");

										if(i == 0) {
											archivo_alumnos = MenuPrincipal.entrada.next();
										}
										else {
											archivo_profesores = MenuPrincipal.entrada.next();
										}

										MenuPrincipal.entrada.nextLine();							// Avance del Scanner para evitar leer ""

										if(Character.toUpperCase(((i == 0) ? (archivo_alumnos) : (archivo_profesores)).charAt(0)) == 'S') {
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
							}
						}

						if(ok_archivo) {
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

		if(!MenuPrincipal.DEBUG) {
			encontrado = gestorUsuarios.searchUsuario(buscadores.get(aleatorio.nextInt(1)), dni);
		}
		else {																	// Para depuración se elegirá siempre el buscador dicotómico, al se el más complejo de implementar
			encontrado = gestorUsuarios.searchUsuario(buscadores.get(0), dni);
		}

		if(encontrado > -1) {
			System.out.print("Encontrado: ");

			gestorUsuarios.printUsuario(encontrado);
		}
		else {
			System.out.println("El usuario con DNI " + String.format("%08d", dni) + " no ha sido hallado en el gestor de usuarios");
		}

		return encontrado;
	}
}
