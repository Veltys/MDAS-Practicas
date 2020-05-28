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
 * @version		1.0.0
 */

public class MenuPrincipal {
	final static private	String						ARCHIVOUSUARIOS			= System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "usuarios.csv";
	final static private	String						ARCHIVOINCIDENCIAS		= System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "incidencias.csv";
	final static private	String						ARCHIVORECURSOS			= System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "recursos.csv";
	final static private	String						ARCHIVORESERVAS			= System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "reservas.csv";
	final static private	String						ARCHIVOSALAS			= System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "salas.csv";
	final static private	String						ARCHIVOSALASYRECURSOS	= System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "salasyrecursos.csv";
	final static private	String						ARCHIVOSANCIONES		= System.getProperty("user.dir") + File.separatorChar + "data" + File.separatorChar + "sanciones.csv";

	private static			Scanner					_entrada					= new Scanner(System.in);
	private static			AdministradorAlumnos	_aa							= new AdministradorAlumnos(MenuPrincipal.ARCHIVOINCIDENCIAS, MenuPrincipal.ARCHIVORECURSOS, MenuPrincipal.ARCHIVORESERVAS, MenuPrincipal.ARCHIVOSALAS, MenuPrincipal.ARCHIVOSALASYRECURSOS, MenuPrincipal.ARCHIVOSANCIONES, MenuPrincipal.ARCHIVOUSUARIOS);
	private static			AdministradorUsuarios	_au							= new AdministradorUsuarios(MenuPrincipal.ARCHIVOUSUARIOS);
	private static			GestorSalas				_gs							= new GestorSalas(MenuPrincipal.ARCHIVOINCIDENCIAS, MenuPrincipal.ARCHIVORECURSOS, MenuPrincipal.ARCHIVORESERVAS, MenuPrincipal.ARCHIVOSALAS, MenuPrincipal.ARCHIVOSALASYRECURSOS, MenuPrincipal.ARCHIVOSANCIONES);


	/**
	 * Método estático privado de inicio de sesión
	 *
	 * @return										int								ID del usuario (-1 si no ha iniciado sesión)
	 */

	static private int iniciarSesion() {
		char					confirmacion	= 'S';
		int						idUsuario		= -1;

		while((Character.toUpperCase(confirmacion) == 'S') && ((idUsuario = MenuPrincipal._au.iniciarSesion()) == -1)) {
			System.out.println("Ha introducido unas credenciales de usuario no válidas");
			System.out.print("¿Desea volverlo a intentar? [S/n]: ");

			confirmacion = Character.toUpperCase(MenuPrincipal._entrada.next().charAt(0));
		}

		return idUsuario;
	}


	/**
	 * Método estático main de la clase (y del programa)
	 *
	 * @param		args							String							Argumentos recibidos por la línea de comandos
	 */

	static public void main(String[] args) {
		boolean					salir		= false;
		char					operacion;
		int						idUsuario	= -1;
		String					operaciones;

		System.out.println("Bienvenido al Gestor de salas");

		if((idUsuario = MenuPrincipal.iniciarSesion()) == -1) {
			salir = true;
		}

		if(!salir) {
			System.out.println("¡Bienvenido, " + MenuPrincipal._au.nombre(idUsuario) + "!");

			while(!salir) {
				operaciones = MenuPrincipal.menu(idUsuario);

				salir = ("".equals(operaciones));

				if(!salir) {
					System.out.print("Por favor, seleccione una operación para continuar [" + operaciones + "]: ");

					operacion = Character.toUpperCase(MenuPrincipal._entrada.next().charAt(0));

					if(operaciones.toUpperCase().indexOf(operacion) != -1) {
						switch(operacion) {
						case 'M':
							MenuPrincipal.modificarReserva(idUsuario);

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
	 * Método estático privado para mostrar el menú del programa
	 *
	 * @param		idUsuario						int								ID del usuario que utiliza el programa
	 *
	 * @return										String							Operaciones disponibles para ese usuario ("" si ninguna)
	 */

	static String menu(int idUsuario) {
		int		idSancion;
		String	operaciones	= "";

		if(MenuPrincipal._au.alumno(idUsuario)) {
			if((idSancion = MenuPrincipal._aa.comprobarSancion(idUsuario)) == -1) {
				System.out.println("El menú de operaciones es el siguiente:");
				System.out.println("Introduzca 'r' para realizar una reserva");

				operaciones += 'r';

				if(MenuPrincipal._gs.buscarReservas(idUsuario, false) != null) {
					System.out.println("Introduzca 'm' para modificar una reserva ya existente");

					operaciones += 'm';
				}

				System.out.println("Introduzca 's' para salir");

				operaciones += 's';
			}
			else {
				System.out.println(MenuPrincipal._aa.notificarAlumnoSancionado(idUsuario, idSancion));
			}
		}
		else if(MenuPrincipal._au.empleado(idUsuario)) {
			System.out.println("El menú de operaciones es el siguiente:");
			System.out.println("Introduzca 's' para salir");

			operaciones += 's';
		}
		else {
			System.out.println("Lo sentimos, su perfil de usuario no le permite utilizar este servicio");
		}

		return operaciones;
	}


	/**
	 * Método estático privado para modificar una reserva
	 *
	 * @param		idAlumno						int								ID del alumno que solicita modificar la reserva
	 */

	static private void modificarReserva(int idAlumno) {
		int idReserva = MenuPrincipal.seleccionarReserva(MenuPrincipal._gs.buscarReservas(idAlumno, false));

		if(idReserva != -1) {
			switch(MenuPrincipal._gs.suspenderReserva(idAlumno, idReserva)) {
			default:
				if(MenuPrincipal.reservar(idAlumno)) {
					MenuPrincipal._gs.eliminarReserva(idReserva);

					System.out.println("La reserva ha sido modificada satisfactoriamente");
				}
				else {
					MenuPrincipal._gs.reanudarReserva(idAlumno, idReserva);

					System.out.println("La reserva no ha sido modificada");
				}

				break;
			case -1:
				System.out.println("La reserva seleccionada (" + idReserva + ") no existe");

				break;
			case -2:
				System.out.println("La reserva seleccionada (" + idReserva + ") no es de su propiedad");

				break;
			}
		}
		else {
			System.out.println("Operación cancelada");
		}
	}


	/**
	 * Método estático privado para seleccionar una reserva
	 *
	 * @param		idsReservas						ArrayList&lt;Integer&gt;		Lista de IDs de reservas asociadas al alumno
	 *
	 * @return										int								ID de la reserva seleccionada (-1 si no)
	 */

	static private int seleccionarReserva(ArrayList<Integer> idsReservas) {
		String opcion;

		System.out.println("A continuación se le presentarán todas las reservas pendientes que ha realizado");

		for(int idReserva: idsReservas) {
			System.out.println("Reserva " + idReserva + ":");
			System.out.println(MenuPrincipal._gs.mostrarReserva(idReserva));
		}

		System.out.print("Seleccione la que desea modificar (enter para cancelar): ");

		MenuPrincipal._entrada.nextLine();

		opcion = MenuPrincipal._entrada.nextLine();

		if(!"".equals(opcion)) {
			return Integer.parseInt(opcion);
		}
		else {
			return -1;
		}
	}


	/**
	 * Método estático privado para reservar una sala
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
		StringTokenizer		stLinea;


		System.out.println("Para realizar una reserva es necesario introducir datos que se le irán solicitando uno por uno");
		System.out.print("En primer lugar, introduzca las personas que ocuparán la sala reservada: ");

		alumnos = MenuPrincipal._entrada.nextInt();

		System.out.println("A continuación se le presentarán una serie de recursos disponibles");

		idsRecursos = MenuPrincipal._gs.obtenerRecursos();

		for(int idRecurso: idsRecursos) {
			System.out.println(MenuPrincipal._gs.mostrarRecurso(idRecurso));
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

		idsSalasCandidatas = MenuPrincipal._gs.buscarSala(alumnos, idsRecursosPedidos);

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

			if((idReserva = MenuPrincipal._gs.elegirSala(alumnos, idAlumno, asignatura, duracion, fechaYHora, idsSalasCandidatas)) != -1) {
				System.out.println("La sala más adecuada para sus requisitos es:" + System.getProperty("line.separator") + MenuPrincipal._gs.mostrarReserva(idReserva));
				System.out.print("¿Está de acuerdo? [S/n]: ");

				if(Character.toUpperCase(MenuPrincipal._entrada.next().charAt(0)) != 'N') {
					MenuPrincipal._gs.confirmarReserva(idReserva);

					System.out.println("La reserva se ha confirmado");

					return true;
				}
				else {
					MenuPrincipal._gs.eliminarReserva(idReserva);

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
