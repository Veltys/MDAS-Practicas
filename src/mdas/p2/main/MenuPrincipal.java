package mdas.p2.main;


import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import mdas.p2.administradoralumnos.AdministradorAlumnos;
import mdas.p2.administradorusuarios.AdministradorUsuarios;
import mdas.p2.gestorsalas.GestorSalas;


/**
 * Clase MenuPrincipal
 * Clase MenuPrincipal del programa
 *
 * @author		Rafael Carlos Méndez Rodríguez (i82meror)
 * @date		28/05/2020
 * @version		0.4.2
 */

public class MenuPrincipal {
	final static private	String		ARCHIVOUSUARIOS			= System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "usuarios.csv";
	final static private	String		ARCHIVOINCIDENCIAS		= System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "incidencias.csv";
	final static private	String		ARCHIVORECURSOS			= System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "recursos.csv";
	final static private	String		ARCHIVORESERVAS			= System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "reservas.csv";
	final static private	String		ARCHIVOSALAS			= System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "salas.csv";
	final static private	String		ARCHIVOSALASYRECURSOS	= System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "salasyrecursos.csv";
	final static private	String		ARCHIVOSANCIONES		= System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "sanciones.csv";


	private static			Scanner		_entrada		= new Scanner(System.in);


	/**
	 * Método main de la clase (y del programa)
	 *
	 * @param		args							String							Argumentos recibidos por la línea de comandos
	 *
	 * TODO: Reducir longitud
	 */

	public static void main(String[] args) {
		boolean					salir		= false;
		char					operacion;
		int						idUsuario	= -1;
		int						idReserva;
		int						idSancion;
		String					operaciones;
		ArrayList<Integer>		idsReservas	= null;
		AdministradorAlumnos	aa			= new AdministradorAlumnos(MenuPrincipal.ARCHIVOINCIDENCIAS, MenuPrincipal.ARCHIVORECURSOS, MenuPrincipal.ARCHIVORESERVAS, MenuPrincipal.ARCHIVOSALAS, MenuPrincipal.ARCHIVOSALASYRECURSOS, MenuPrincipal.ARCHIVOSANCIONES, MenuPrincipal.ARCHIVOUSUARIOS);
		AdministradorUsuarios	au			= new AdministradorUsuarios(MenuPrincipal.ARCHIVOUSUARIOS);
		GestorSalas				gs			= new GestorSalas(MenuPrincipal.ARCHIVOINCIDENCIAS, MenuPrincipal.ARCHIVORECURSOS, MenuPrincipal.ARCHIVORESERVAS, MenuPrincipal.ARCHIVOSALAS, MenuPrincipal.ARCHIVOSALASYRECURSOS, MenuPrincipal.ARCHIVOSANCIONES);

		System.out.println("Bienvenido al Gestor de salas");

		while(!salir && ((idUsuario = au.iniciarSesion()) == -1)) {
			System.out.println("Ha introducido unas credenciales de usuario no válidas");
			System.out.print("¿Desea volverlo a intentar? [S/n]: ");

			operacion = Character.toUpperCase(MenuPrincipal._entrada.next().charAt(0));

			if(Character.toUpperCase(operacion) == 'N') {
				salir = true;
			}
		}

		if(!salir) {
			System.out.println("¡Bienvenido, " + au.nombre(idUsuario) + "!");

			while(!salir) {
				operaciones	= "";

				if(au.alumno(idUsuario)) {
					if((idSancion = aa.comprobarSancion(idUsuario)) == -1) {
						System.out.println("El menú de operaciones es el siguiente:");
						System.out.println("Introduzca 'r' para realizar una reserva");

						operaciones += 'r';

						if((idsReservas = gs.buscarReservas(idUsuario, false)) != null) {
							System.out.println("Introduzca 'm' para modificar una reserva ya existente");

							operaciones += 'm';
						}

						System.out.println("Introduzca 's' para salir");

						operaciones += 's';
					}
					else {
						System.out.println(aa.notificarAlumnoSancionado(idUsuario, idSancion));

						salir = true;
					}
				}
				else if(au.empleado(idUsuario)) {
					System.out.println("El menú de operaciones es el siguiente:");
					System.out.println("Introduzca 's' para salir");

					operaciones += 's';
				}
				else {
					System.out.println("Lo sentimos, su perfil de usuario no le permite utilizar este servicio");

					salir = true;
				}

				if(!salir) {
					System.out.print("Por favor, seleccione una operación para continuar [" + operaciones + "]: ");

					operacion = Character.toUpperCase(MenuPrincipal._entrada.next().charAt(0));

					if(operaciones.indexOf(operacion) != -1) {
						switch(operacion) {
						case 'M':
							if((idReserva = MenuPrincipal.seleccionarReserva(idsReservas)) != -1) {
								gs.suspenderReserva(idReserva);

								if(MenuPrincipal.reservar(idUsuario)) {
									gs.eliminarReserva(idReserva);

									System.out.println("La reserva ha sido modificada satisfactoriamente");
								}
								else {
									gs.reanudarReserva(idReserva);

									System.out.println("La reserva no ha sido modificada");
								}
							}
							else {
								System.out.println("Operación cancelada");
							}

							break;
						case 'R':
							MenuPrincipal.reservar(idUsuario);

							break;

						case 'S':
							salir = true;

							break;

						default:													// Nunca se llegará aquí
							break;
						}
					}
					else {
						System.out.println("La operación seleccionada (" + operacion + ") es incorrecta o no tiene permisos para realizarla");
						System.out.println("Por favor, seleccione una válida del menú");
					}

				}
			}
		}

		System.out.println("Gracias por utilizar nuestro sistema");
		System.out.println("Saliendo...");
	}


	/**
	 * Método privado para seleccionar una reserva
	 *
	 * @param		idsReservas						ArrayList&lt;Integer&gt;		Lista de IDs de reservas asociadas al alumno
	 *
	 * @return										int								ID de la reserva seleccionada (-1 si no)
	 */

	static private int seleccionarReserva(ArrayList<Integer> idsReservas) {
		GestorSalas			gs						= new GestorSalas(MenuPrincipal.ARCHIVOINCIDENCIAS, MenuPrincipal.ARCHIVORECURSOS, MenuPrincipal.ARCHIVORESERVAS, MenuPrincipal.ARCHIVOSALAS, MenuPrincipal.ARCHIVOSALASYRECURSOS, MenuPrincipal.ARCHIVOSANCIONES);

		String opcion;

		System.out.println("A continuación se le presentarán todas las reservas pendientes que ha realizado");

		for(int idReserva: idsReservas) {
			System.out.println(gs.mostrarReserva(idReserva));
		}

		System.out.print("Seleccione la que desea modificar (no introduzca nada para cancelar): ");

		opcion = MenuPrincipal._entrada.next();

		if(!"".equals(opcion)) {
			return Integer.parseInt(opcion);
		}
		else {
			return -1;
		}
	}


	/**
	 * Método privado para reservar una sala
	 *
	 * @param		idAlumno						int								ID del alumno que reserva la sala
	 *
	 * @return										boolean							Resultado de la operación
	 *
	 * TODO: Reducir la complejidad N-Path
	 */

	static private boolean reservar(int idAlumno) {
		boolean				okFecha					= false;
		int					alumnos;
		int					duracion;
		int					idReserva;
		ArrayList<Integer>	idsRecursos				= new ArrayList<Integer>();
		ArrayList<Integer>	idsRecursosIncorrectos	= new ArrayList<Integer>();
		ArrayList<Integer>	idsRecursosPedidos		= new ArrayList<Integer>();
		ArrayList<Integer>	idsSalasCandidatas;
		String				asignatura;
		String				linea;
		LocalDateTime		ahora;
		LocalDateTime		fechaYHora				= null;
		GestorSalas			gs						= new GestorSalas(MenuPrincipal.ARCHIVOINCIDENCIAS, MenuPrincipal.ARCHIVORECURSOS, MenuPrincipal.ARCHIVORESERVAS, MenuPrincipal.ARCHIVOSALAS, MenuPrincipal.ARCHIVOSALASYRECURSOS, MenuPrincipal.ARCHIVOSANCIONES);
		StringTokenizer		stLinea;


		System.out.println("Para realizar una reserva es necesario introducir datos que se le irán solicitando uno por uno");
		System.out.print("En primer lugar, introduzca las personas que ocuparán la sala reservada: ");

		alumnos = MenuPrincipal._entrada.nextInt();

		System.out.println("A continuación se le presentarán una serie de recursos disponibles");

		idsRecursos = gs.obtenerRecursos();

		for(int idRecurso: idsRecursos) {
			System.out.println(gs.mostrarRecurso(idRecurso));
		}

		MenuPrincipal._entrada.nextLine();

		System.out.print("Seleccione tantos como necesite, introduciendo sus números, separados por espacios: ");
		linea = MenuPrincipal._entrada.nextLine();

		stLinea = new StringTokenizer(linea, " ");

		while(stLinea.hasMoreTokens()) {
			idsRecursosPedidos.add(Integer.parseInt(stLinea.nextToken()));
		}

		for(Integer idRecursoPedido: idsRecursosPedidos) {
			if(!idsRecursos.contains(idRecursoPedido)) {
				System.out.println("El número de recurso " + idRecursoPedido + " es incorrecto y no será tomado en cuenta");

				idsRecursosIncorrectos.add(idRecursoPedido);
			}
		}

		for(Integer idRecursoIncorrecto: idsRecursosIncorrectos) {
			idsRecursosPedidos.remove(idRecursoIncorrecto);
		}

		idsSalasCandidatas = gs.buscarSala(alumnos, idsRecursosPedidos);

		if(idsSalasCandidatas != null) {
			System.out.print("Introduzca la asignatura para la que será reservada la sala: ");

			asignatura = MenuPrincipal._entrada.nextLine();

			do {
				try {
					System.out.print("Introduzca la fecha y la hora de la reserva en formato \"HH:mm dd/MM/yyyy\": ");

					fechaYHora = LocalDateTime.parse(MenuPrincipal._entrada.nextLine(), DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy"));

					okFecha = true;
				}
				catch (DateTimeParseException e) {
					System.out.println("La fecha introducida es incorrecta");
					System.out.println("Por favor inténtelo de nuevo");
				}

				ahora = LocalDateTime.now();
				if(okFecha && !(fechaYHora.isAfter(ahora))) {
					System.out.println("La fecha introducida es del pasado");
					System.out.println("Por favor inténtelo de nuevo");
					System.out.println("Ahora mismo son las " + ahora.format(DateTimeFormatter.ofPattern("HH:mm")) + " del " + ahora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

					okFecha = false;
				}
			} while(!okFecha);

			System.out.print("Para finalizar, introduzca la duración (en horas) de la reserva: ");

			duracion = MenuPrincipal._entrada.nextInt();

			if((idReserva = gs.elegirSala(alumnos, idAlumno, asignatura, duracion, fechaYHora, idsSalasCandidatas)) != -1) {
				System.out.println("La sala más adecuada para sus requisitos es:" + System.getProperty("line.separator") + gs.mostrarReserva(idReserva));
				System.out.print("¿Está de acuerdo? [S/n]: ");

				if(Character.toUpperCase(MenuPrincipal._entrada.next().charAt(0)) != 'N') {
					gs.confirmarReserva(idReserva);

					System.out.println("La reserva se ha confirmado");

					return true;
				}
				else {
					gs.eliminarReserva(idReserva);

					System.out.println("La reserva se ha cancelado");

					return false;
				}
			}
			else {
				System.out.println("No ha sido posible encontrar una sala acorde a los requisitos solicitados");

				return false;
			}
		}
		else {
			System.out.println("No ha sido posible encontrar una sala acorde a los requisitos solicitados");

			return false;
		}
	}
}
