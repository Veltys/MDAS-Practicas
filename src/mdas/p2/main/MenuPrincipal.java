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
import mdas.p2.gestorreservamgr.Recurso;
import mdas.p2.gestorsalas.GestorSalas;


/**
 * Clase MenuPrincipal
 * Clase MenuPrincipal del programa
 *
 * @author		Rafael Carlos Méndez Rodríguez (i82meror)
 * @date		27/05/2020
 * @version		0.4.0
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
	 */

	public static void main(String[] args) {
		boolean					salir		= false;
		char					operacion;
		int						idUsuario	= -1;
		int						idSancion;
		AdministradorAlumnos	aa			= new AdministradorAlumnos(MenuPrincipal.ARCHIVOINCIDENCIAS, MenuPrincipal.ARCHIVORECURSOS, MenuPrincipal.ARCHIVORESERVAS, MenuPrincipal.ARCHIVOSALAS, MenuPrincipal.ARCHIVOSALASYRECURSOS, MenuPrincipal.ARCHIVOSANCIONES, MenuPrincipal.ARCHIVOUSUARIOS);
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

		if(!salir) {
			System.out.println("¡Bienvenido, " + au.nombre(idUsuario) + "!");

			while(!salir) {
				if(au.alumno(idUsuario)) {
					if((idSancion = aa.comprobarSancion(idUsuario)) == -1) {
						System.out.println("El menú de operaciones es el siguiente:");

						System.out.println("Introduzca 'r' para realizar una reserva");
						System.out.println("Introduzca 's' para salir");

						System.out.print("Por favor, seleccione una operación para continuar [rs]: ");
					}
					else {
						System.out.println(aa.notificarAlumnoSancionado(idUsuario, idSancion));

						salir = true;
					}
				}
				else if(au.empleado(idUsuario)) {
					System.out.println("El menú de operaciones es el siguiente:");

					System.out.println("Introduzca 's' para salir");

					System.out.print("Por favor, seleccione una operación para continuar [s]: ");
				}
				else {
					System.out.println("Lo sentimos, su perfil de usuario no le permite utilizar este servicio");

					salir = true;
				}

				if(!salir) {
					operacion = Character.toUpperCase(MenuPrincipal._entrada.next().charAt(0));

					switch(operacion) {
					case 'R':
						MenuPrincipal.reserva(idUsuario);

						break;

					case 'S':
						salir = true;

						break;

					default:
						System.out.println("La operación seleccionada (" + operacion + ") es incorrecta");
						System.out.println("Por favor, seleccione una válida del menú");

						break;
					}
				}
			}
		}

		System.out.println("Gracias por utilizar nuestro sistema");
		System.out.println("Saliendo...");
	}


	// TODO: Comentar

	static private void reserva(int idAlumno) {
		/*
		private int				_idSala;
		private int				_duracion;
		private String			_asignatura;
		private LocalDateTime	_fechaYHora;
		 */

		boolean				okFecha					= false;
		int					alumnos;
		int					duracion;
		int					idReserva;
		ArrayList<Integer>	idsRecursos				= new ArrayList<Integer>();
		ArrayList<Integer>	idsRecursosIncorrectos	= new ArrayList<Integer>();
		ArrayList<Integer>	idsRecursosPedidos		= new ArrayList<Integer>();
		ArrayList<Integer>	idsSalasCandidatas;
		ArrayList<Recurso>	recursos;
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

		recursos = gs.obtenerRecursos();

		for(Recurso r: recursos) {
			System.out.println(r.id() + ": " + r.nombre() + " (" + r.descripcion() + ")");

			idsRecursos.add(r.id());
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
				}
				else {
					gs.eliminarReserva(idReserva);

					System.out.println("La reserva se ha cancelado");
				}
			}
			else {
				System.out.println("No ha sido posible encontrar una sala acorde a los requisitos solicitados");
			}
		}
		else {
			System.out.println("No ha sido posible encontrar una sala acorde a los requisitos solicitados");
		}
	}
}
