package mdas.usuarios;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import mdas.usuarios.Usuario;
import mdas.usuarios.Categorias;


/**
 * Clase GestorUsuarios
 * Componente de gestión de usuarios del sistema
 * 
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			07/05/2020
 * @version			1.2.3
 */

public class GestorUsuarios {
	private List<Usuario> _usuarios;

	/**
	 * Constructor de clase
	 * Inicializa la lista de usuarios
	 */

	GestorUsuarios() {
		this._usuarios = new ArrayList<Usuario>();
	}


	/**
	 * Método privado para añadir un alumno a la lista
	 * 
	 * @param		dni								String							DNI del alumno
	 * @param		nombre							String							Nombre del alumno
	 * @param		fNacimiento						LocalDate						Fecha de nacimiento del alumno
	 * @param		titulacion						String							Titulación del alumno
	 * @param		curso							int								Curso del alumno
	 */

	// FIXME: Método publico solamente para propósitos de depuración
	public boolean addAlumno(String dni, String nombre, LocalDate fNacimiento, String titulacion, int curso) {
		Alumno	nuevo;																		// 	Alumno a insertar

		try {																				// 	Si el DNI no es válido, habrá una excepción... que se debe capturar
			if(fNacimiento != null) {														// 		Como la fecha de nacimiento es opcional, se llama al constructor adecuado en función de si se tiene el dato o no
				nuevo = new Alumno(dni, nombre, fNacimiento, titulacion, curso);
			}
			else {
				nuevo = new Alumno(dni, nombre, titulacion, curso);
			}

			this.ordredInsertUser(nuevo);
	
			System.out.println("El alumno ha sido agregado correctamente");					// 		Se informa del éxito de la operación

			return true;
		}
		catch(RuntimeException e) {															// 	En caso de problemas...
			System.out.println("Error: " + e.getMessage());									// 		... también se informa

			return false;
		}

	}


	/**
	 * Método privado para añadir un profesor a la lista
	 * 
	 * @param		dni								String							DNI del profesor
	 * @param		nombre							String							Nombre del profesor
	 * @param		fNacimiento						LocalDate						Fecha de profesor del alumno
	 * @param		creditos						int								Créditos impartidos del profesor
	 * @param		categoria						Categorias						Categoría profesional del profesor
	 */

	// FIXME: Método publico solamente para propósitos de depuración
	public boolean addProfesor(String dni, String nombre, LocalDate fNacimiento, int creditos, Categorias categoria) {
		Profesor	nuevo;																	// 	Profesor a insertar

		try {																				// 	Si el DNI no es válido, habrá una excepción... que se debe capturar
			if(fNacimiento != null) {														// 		Como la fecha de nacimiento es opcional, se llama al constructor adecuado en función de si se tiene el dato o no
				nuevo = new Profesor(dni, nombre, fNacimiento, creditos, categoria);
			}
			else {
				nuevo = new Profesor(dni, nombre, creditos, categoria);
			}

			this.ordredInsertUser(nuevo);

			System.out.println("El profesor ha sido agregado correctamente");				// 		Se informa del éxito de la operación

			return true;
		}
		catch(RuntimeException e) {															// 	En caso de problemas...
			System.out.println("Error: " + e.getMessage());									// 		... también se informa

			return false;
		}
	}


	/**
	 * Método privado para buscar la categoría profesional de un profesor
	 * 
	 * @param		str_categoria					String							Categoría profesional del profesor a insertar antes de ser convertida al tipo Categorias
	 * 
	 * @return										Categorias						La categoría encontrada
	 */

	private Categorias buscarCategoriaProfesional(String str_categoria) {
		Categorias	categoria		= null;													// Categoría profesional del profesor a insertar ya convertida al tipo Categorias

		for(Categorias c : Categorias.values()) {											// Se itera el enum de categorías para encontrar la proporcionada
			if(str_categoria.equalsIgnoreCase(c.name().toString())) {						// 	En caso de encontrarla
				categoria = c;																// 		Se almacena

				break;																		// 		El bucle de búsqueda termina
			}
		}

		return categoria;
	}


	/**
	 * Método privado para realizar la insercción ordenada de cualquier usuario (sea éste alumno o profesor)
	 * Busca en la lista de usuarios la posición adecuada para insertar el nuevo y, una vez encontrada, lleva a cabo dicha insercción, garantizando así el orden de la lista
	 * 
	 * @param		nuevo							Usuario							Usuario nuevo a insertar
	 */

	private void ordredInsertUser(Usuario nuevo) {
		int			tamVector	= this._usuarios.size();									// Tamaño del vector en el que se insertará
		int			i			= 0;
		int			insertar;																// Posición de insercción

		if(tamVector > 0) {																	// 		Si el vector no está vacío es necesario insertarlo ordenadamente
			do {
				insertar = nuevo.compareTo(this._usuarios.get(i));							// 				Es necesario buscar la posición de insercción, en aras de que el vector esté ordenado
			} while(insertar > 0 && ++i < tamVector);										// 			Con la segunda parte de la comprobación se evita salirse del mismo por el final
		}

		this._usuarios.add(i, nuevo);														// 			Por fin, la insercción
	}


	/**
	 * Método privado para procesar la categoría profesional de un profesor
	 * 
	 * @param		entrada							Scanner							Scanner desde el que se leerán los datos
	 * 
	 * @return										Categorias						La categoría ya procesada
	 */

	private Categorias procesarCategoriaProfesional(Scanner entrada) {
		String		str_categoria;															// 	Categoría profesional del profesor a insertar antes de ser convertida al tipo Categorias
		Categorias	categoria		= null;													// 	Categoría profesional del profesor a insertar ya convertida al tipo Categorias

		do {																				// 	Validador de categorías profesionales
			System.out.println("La lista de categorías profesionales disponible es la siguiente:");
			System.out.println(java.util.Arrays.asList(Categorias.values()));

			System.out.print("Introduzca la categoría profesional: ");

			str_categoria = entrada.next();

			categoria = buscarCategoriaProfesional(str_categoria);

			if(categoria == null) {															// 		En caso de no ser válida se ha de informar
				System.out.println("Error: La categoría profesional seleccionada es incorrecta");
			}
		} while(categoria == null);															// 	El bucle validador sólo acabará cuando se introduzca una categoría válida

		return categoria;
	}


	/**
	 * Método para añadir un usuario a la lista
	 */

	public void addUsuario() {
		boolean		ok_fNacimiento;															// "Bandera" de validación de la fecha de nacimiento
		char		tipo;																	// Tipo de usuario a insertar
		String		dni;																	// DNI del usuario a insertar
		String		nombre;																	// Nombre del usuario a insertar
		String		str_fNacimiento;														// Fecha de nacimiento del usuario a insertar antes de ser convertida al tipo LocalDate
		LocalDate	fNacimiento			= null;												// Fecha de nacimiento del usuario a insertar ya convertida al tipo LocalDate

		@SuppressWarnings("resource")														// Eliminación del warning de Eclipse por no cerrar el Scanner
		Scanner		entrada				= new Scanner(System.in);							// Scanner para lectura por teclado de datos

		System.out.print("¿Qué tipo de usuario se cargará? [A]lumno/[p]rofesor: ");
		tipo = entrada.next().charAt(0);													// Con recuperar el primer caracter vale

		System.out.print("Introduzca el DNI: ");
		dni = entrada.next();

		entrada.nextLine();																	// Avance del Scanner para evitar leer ""

		System.out.print("Introduzca el nombre: ");
		nombre = entrada.nextLine();

		do {
			System.out.print("Introduzca la fecha de nacimiento en formato DD/MM/AAAA (opcional, dejar en blanco para continuar): ");
			str_fNacimiento = entrada.nextLine();

			if(!("".equals(str_fNacimiento))) {
				try {
					fNacimiento = LocalDate.parse(str_fNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

					ok_fNacimiento = true;
				}
				catch(DateTimeParseException e) {
					System.out.println("La fecha de nacimiento introducida (" + str_fNacimiento + ") no es válida");

					System.out.print("¿Desea volver a intentarlo? [s/N]: ");

					str_fNacimiento = entrada.next();

					entrada.nextLine();														// Avance del Scanner para evitar leer ""

					ok_fNacimiento = (Character.toUpperCase(str_fNacimiento.charAt(0)) == 'S') ? (false) : (true);
				}
			}
			else {
				ok_fNacimiento = true;
			}
		} while(!ok_fNacimiento);

		if(Character.toUpperCase(tipo) != 'P') {											// Cualquier cosa que no sea una "P" será tratada como un alumno (el tipo por defecto)
			int		curso		= -1;														// 	Curso del alumno a insertar
			String	titulacion;																// 	Titulación del alumno a insertar

			System.out.print("Introduzca la titulación: ");
			titulacion = entrada.nextLine();

			do {
				System.out.print("Introduzca el curso: ");

				try {
					curso = entrada.nextInt();
				}
				catch(InputMismatchException e) {
					entrada.nextLine();														// Avance del Scanner para permitir otra lectura

					System.out.println("El curso introducido no es válido");
					System.out.println("Por favor, inténtelo de nuevo e introduzca un número");
				}
			} while(curso == -1);

			while(!addAlumno(dni, nombre, fNacimiento, titulacion, curso)) {
				System.out.println("El DNI debe estar en el formato 00000000A, siendo A la letra de control y siendo ésta correcta");
				System.out.print("Introduzca el DNI: ");
				dni = entrada.next();
			}
		}
		else {																				// Operaciones equivalentes para la insercción de un profesor
			int			creditos	= -1;													// 	Créditos impartidos del profesor a insertar
			Categorias	categoria;															// 	Categoría profesional del profesor a insertar ya convertida al tipo Categorias

			do {
				System.out.print("Introduzca los créditos impartidos: ");

				try {
					creditos = entrada.nextInt();
				}
				catch(InputMismatchException e) {
					entrada.nextLine();														// Avance del Scanner para permitir otra lectura

					System.out.println("Los créditos introducidos no son");
					System.out.println("Por favor, inténtelo de nuevo e introduzca un número");
				}
			} while(creditos == -1);

			categoria = procesarCategoriaProfesional(entrada);

			while(!addProfesor(dni, nombre, fNacimiento, creditos, categoria)) {
				System.out.println("El DNI debe estar en el formato 00000000A, siendo A la letra de control y siendo ésta correcta");
				System.out.print("Introduzca el DNI: ");
				dni = entrada.next();
			}
		}

		// entrada.close();																	// 	No se debe cerrar un Scanner(System.in) o se cerrará el propio System.in 🤷🏼‍♂️
	}


	/**
	 * Método para cargar usuarios de archivos
	 * Carga alumnos y profesores en la lista desde sendos archivos CSV
	 * 
	 * @param		archivo_alumnos					String							Ruta del archivo de alumnos
	 * @param		archivo_profesores				String							Ruta del archivo de profesores
	 */

	public void loadUsuarios(String archivo_alumnos, String archivo_profesores) {
		int i;
		BufferedReader	buffer;																// Buffer de lectura
		Categorias		categoria;															// Categoría profesional del profesor a insertar ya convertida al tipo Categorias
		List<String>	campos;																// Campos de la línea leída
		LocalDate		fNacimiento	= null;													// Fecha de nacimiento del usuario a insertar ya convertida al tipo LocalDate
		String			linea;																// Línea leída
		String			str_fNacimiento;													// Fecha de nacimiento del usuario a insertar antes de ser convertida al tipo LocalDate
		StringTokenizer	st_linea;															// Particionador de línea

		for(i = 0; i < 2; i++) {															// Es necesario realizar el proceso dos veces, para las dos listas: alumnos y profesores
			try {																			// 	En caso de haber problemas con los archivos será necesario capturar las excepciones que lancen
				buffer = new BufferedReader(												// 		Se inicializa un buffer de lectura (más eficiente)...
							new FileReader(													// 		... de un lector de archivos en el cual...
								new File(													// 		... se abrirá un archivo u otro...
									(i == 0) ? (archivo_alumnos) : (archivo_profesores)		// 		... en función de la iteración en que se encuentre el bucle
								)
							)
						 );

				campos = new ArrayList<String>();											// 		También se inicializa un ArrayList para contener posteriormente cada campo del archivo

				while((linea = buffer.readLine()) != null) {								// 		Mientras que haya más líneas que leer
					st_linea = new StringTokenizer(linea, ",");								// 			Se inicializa el "troceador" de ésta

					while(st_linea.hasMoreTokens()) {										// 			Mientras haya más campos que leer
						campos.add(st_linea.nextToken());									// 				Se van almacenando
					}

					if(!("".equals(str_fNacimiento = campos.get(2)))) {						// 				Procesamiento de la fecha de nacimiento, de no estar vacía
						fNacimiento = LocalDate.parse(str_fNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
					}

					if(i == 0) {															// 				En la primera iteración se añaden alumnos
						this.addAlumno(campos.get(0), campos.get(1), fNacimiento, campos.get(3), Integer.parseInt(campos.get(4)));
					}
					else {																	// 				En la segunda, profesores
						categoria = buscarCategoriaProfesional(campos.get(4));

						this.addProfesor(campos.get(0), campos.get(1), fNacimiento, Integer.parseInt(campos.get(3)), categoria);
					}
				}

				buffer.close();																// 		Una vez acabada la lectura del buffer, es necesario cerrarlo
			}
			catch(FileNotFoundException e) {												// 	Si se produce una excepción
				System.out.println("Error: " + e.getMessage());								// 		Se habrá de informar al usuario
			}
			catch(IOException e) {															// 	Si se produce una excepción
				System.out.println("Error: " + e.getMessage());								// 		Se habrá de informar al usuario
			}
		}
	}


	/**
	 * Método para sacar por pantalla estadísticas de los usuarios
	 * Recopila y saca por pantalla estadísticas de los usuarios
	 */

	public void printEstadisticas() {
		int						i;
		int						tam_lista;													// Tamaño de una lista
		int						aux_creditos;												// Auxiliar que contendrá los créditos de un profesor
		int						aux_posicion;												// Auxiliar que contendrá una posición en un ArrayList
		int						max_creditos		= 0;									// Número máximo de créditos
		int						max_titulacion		= 0;									// Titulación con mayor frecuencia
		int						suma_creditos		= 0;									// Suma total de créditos (para luego hacer la media)
		int						total_profesores	= 0;									// Total de profesores existentes  (para luego hacer la media)
		Alumno					aux_alumno;													// Auxiliar que contendrá alumnos para su posterior escritura
		Profesor				aux_profesor;												// Auxiliar que contendrá profesores para su posterior escritura
		String					aux_titulacion		= "";									// Auxiliar que contendrá la titulación
		String					nombre_profesor		= "";									// Nombre del profesor con más créditos
		List<String>			titulaciones		= new ArrayList<String>();				// Lista de titulaciones que se irán recuperando
		List<Integer>			frec_titulaciones	= new ArrayList<Integer>();				// Frecuencia de aparición de cada titulación

		tam_lista			= this._usuarios.size();

		for(i = 0; i < tam_lista; i++) {													// 	Iteración de la lista de usuarios
			if(this._usuarios.get(i) instanceof Alumno) {									// 		Si se trata de un alumno  FIXME: Me da que esto va a fallar
				aux_alumno = (Alumno) this._usuarios.get(i);								// 			Se almacena para su posterior escritura

				aux_titulacion = aux_alumno.titulacion();

				if(!titulaciones.contains(aux_titulacion)) {
					titulaciones.add(aux_titulacion);

					frec_titulaciones.add(1);
				}
				else {
					aux_posicion = titulaciones.indexOf(aux_titulacion);
					frec_titulaciones.set(aux_posicion, frec_titulaciones.get(aux_posicion) + 1);
				}
			}
			else {
				aux_profesor	= (Profesor) this._usuarios.get(i);							// 			Se almacena para su posterior escritura
				aux_creditos	= aux_profesor.creditos();

				if(max_creditos < aux_creditos) {
					max_creditos = aux_creditos;

					nombre_profesor = aux_profesor.nombre();
				}

				suma_creditos += aux_creditos;

				total_profesores++;
			}
		}

		tam_lista			= titulaciones.size();											// Tamaño de la lista de titulaciones

		for(i = 0; i < tam_lista; i++) {
			if(max_titulacion < frec_titulaciones.get(i)) {
				max_titulacion = frec_titulaciones.get(i);

				aux_titulacion = titulaciones.get(i);
			}
		}

		System.out.println("Estadísticas de los usuarios:");
		System.out.println("\tTitulación más cursada por los alumnos: " + aux_titulacion + ", con " + max_titulacion + " alumnos matriculados");
		System.out.println("\tProfesor con más créditos: " + nombre_profesor + ", con" + max_creditos + " créditos impartidos");
		System.out.println("\tNúmero medio de créditos por profesor: " + (suma_creditos / total_profesores) + " créditos");
}


	/**
	 * Método para mostrar por pantalla usuarios
	 * Muestra por pantalla el usuario de la posición dada
	 * 
	 * @param		posicion						int								Posición a mostrar
	 */

	public void printUsuario(int posicion) {
		System.out.println(this._usuarios.get(posicion));
	}


	/**
	 * Método para guardar usuarios en archivos
	 * Guarda alumnos y profesores de la lista en sendos archivos CSV
	 * 
	 * @param		archivo_alumnos					String							Ruta del archivo de alumnos
	 * @param		archivo_profesores				String							Ruta del archivo de profesores
	 */

	public void saveUsuarios(String archivo_alumnos, String archivo_profesores) {
		int						i;
		int						tam_lista		= this._usuarios.size();					// Tamaño de la lista de usuarios
		List<BufferedWriter>	buffer			= new ArrayList<BufferedWriter>();			// Búferes de escritura
		Alumno					aux_alumno;													// Auxiliar que contendrá alumnos para su posterior escritura
		Profesor				aux_profesor;												// Auxiliar que contendrá profesores para su posterior escritura

		try {																				// En caso de haber problemas con los archivos será necesario capturar las excepciones que lancen
			buffer.add(new BufferedWriter(new FileWriter(new File(archivo_alumnos))));		// 	Inicialización del buffer de escritura del archivo de alumnos
			buffer.add(new BufferedWriter(new FileWriter(new File(archivo_profesores))));	// 	Inicialización del buffer de escritura del archivo de profesores

			for(i = 0; i < tam_lista; i++) {												// 	Iteración de la lista de usuarios
				if(this._usuarios.get(i) instanceof Alumno) {								// 		Si se trata de un alumno  FIXME: Me da que esto va a fallar
					aux_alumno = (Alumno) this._usuarios.get(i);							// 			Se almacena para su posterior escritura

					buffer.get(0).write(String.format("%08d",aux_alumno.dni()) + ',' + aux_alumno.nombre() + ',' + aux_alumno.alias() + ',' + aux_alumno.fNacimiento().toString() + ',' + Integer.toString(aux_alumno.curso()) + ',' + aux_alumno.titulacion() + System.getProperty("line.separator"));
				}
				else {																		// 		En caso contrario, será un profesor
					aux_profesor = (Profesor) this._usuarios.get(i);						// 			Se almacena para su posterior escritura

					buffer.get(1).write(String.format("%08d", aux_profesor.dni()) + ',' + aux_profesor.nombre() + ',' + aux_profesor.alias() + ',' + aux_profesor.fNacimiento().toString() + ',' + Integer.toString(aux_profesor.creditos()) + ',' + aux_profesor.categoria().toString() + System.getProperty("line.separator"));
				}
			}

			buffer.get(0).close();															// Cierre de los búferes de escritura
			buffer.get(1).close();
		}
		catch(FileNotFoundException e) {													// 	Si se produce una excepción
			System.out.println("Error: " + e.getMessage());									// 		Se habrá de informar al usuario
		}
		catch(IOException e) {																// 	Si se produce una excepción
			System.out.println("Error: " + e.getMessage());									// 		Se habrá de informar al usuario
		}
	}


	/**
	 * Método para buscar usuarios
	 * Utiliza el buscador de usuarios para, en la lista de la clase, encontrar el usuario recibido
	 * 
	 * @param		buscador						IBuscadorUsuarios				Buscador de usuarios
	 * @param		dni								int								DNI a buscar
	 * 
	 * @return										int								Su posición en la lista (-1 si no se ha encontrado)
	 */

	public int searchUsuario(IBuscadorUsuarios buscador, int dni) {
		return buscador.buscarUsuario(this._usuarios, dni);
	}


	/**
	 * Método para eliminar usuarios
	 * Elimina el usuario de la posición dada
	 * 
	 * @param		posicion						int								Posición a eliminar
	 */

	public void removeUsuario(int posicion) {
		this._usuarios.remove(posicion);
	}
}
