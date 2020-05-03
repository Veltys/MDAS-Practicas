package mdas.usuarios;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mdas.usuarios.Usuario;
import mdas.usuarios.Categorias;


/**
 * Clase GestorUsuarios
 * Componente de gestión de usuarios del sistema
 * 
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			03/05/2020
 * @version			0.5.0
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
	 * Método para añadir un usuario a la lista
	 */

	public void addUsuario() {
		char		tipo;																// Tipo de usuario a insertar
		String		dni;																// DNI del usuario a insertar
		String		nombre;																// Nombre del usuario a insertar
		String		str_fNacimiento;													// Fecha de nacimiento del usuario a insertar antes de ser convertida al tipo LocalDate
		LocalDate	fNacimiento			= null;											// Fecha de nacimiento del usuario a insertar ya convertida al tipo LocalDate

		Scanner entrada = new Scanner(System.in);										// Apertura del scanner para lectura por teclado de datos

		System.out.println("¿Qué tipo de usuario se cargará? [A]lumno/[p]rofesor: ");
		tipo = entrada.next().charAt(0);												// Con recuperar el primer caracter vale

		System.out.println("Introduzca el DNI: ");
		dni = entrada.next();

		System.out.println("Introduzca el nombre: ");
		nombre = entrada.next();

		System.out.println("Introduzca la fecha de nacimiento en formato DD/MM/AAAA (opcional, dejar en blanco para continuar): ");
		str_fNacimiento = entrada.next();

		if(!("".equals(str_fNacimiento))) {
			fNacimiento = LocalDate.parse(str_fNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}

		if(Character.toUpperCase(tipo) != 'P') {										// Cualquier cosa que no sea una "P" será tratada como un alumno (el tipo por defecto)
			int		curso;																// 	Curso del alumno a insertar
			String	titulacion;															// 	Titulación del alumno a insertar

			System.out.println("Introduzca la titulación: ");
			titulacion = entrada.next();

			System.out.println("Introduzca el curso: ");
			curso = entrada.nextInt();

			entrada.close();															// 	Se cierra el scanner, al no necesitarse más

			addAlumno(dni, nombre, fNacimiento, titulacion, curso);
		}
		else {																			// Operaciones equivalentes para la insercción de un profesor
			boolean		categoriaOk = false;											// 	Validador de la categoría profesional
			int			creditos;														// 	Créditos impartidos del profsor a insertar
			String		str_categoria;													// 	Categoría profesional del profsor a insertar antes de ser convertida al tipo Categorias
			Categorias	categoria = null;												// 	Categoría profesional del profsor a insertar ya convertida al tipo Categorias

			System.out.println("Introduzca los créditos impartidos: ");
			creditos = entrada.nextInt();

			do {																		// 	Validador de categorías profesionales
				System.out.println("La lista de categorías profesionales disponible es la siguiente:");
				System.out.println(java.util.Arrays.asList(Categorias.values()));
				System.out.println("Introduzca la categoría profesional: ");
				str_categoria = entrada.next();

				for(Categorias c : Categorias.values()) {								// 		Se recorre el enum de categorías para encontrar la proporcionada
					if(str_categoria.toUpperCase() == c.name()) {						// 			En caso de encontrarla
						categoriaOk = true;												// 				Se da por válida

						categoria = c;													// 				Se almacena

						break;															// 				El bucle de búsqueda termina
					}
				}

				if(!categoriaOk) {														// 		En caso de no ser válida se ha de informar
					System.out.println("Error: La categoría profesional seleccionada es incorrecta");
				}
			} while(!categoriaOk);														// 	El bucle validador sólo acabará cuando se introduzca una categoría válida

			entrada.close();															// 	Se cierra el scanner, al no necesitarse más

			addProfesor(dni, nombre, fNacimiento, creditos, categoria);
		}
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

	private void addAlumno(String dni, String nombre, LocalDate fNacimiento, String titulacion, int curso) {
		int		tamVector;																// Tamaño del vector en el que se insertará
		int		i			= 0;
		int		insertar;																// Posición de insercción
		Alumno	nuevo;																	// 	Alumno a insertar

		try {																			// 	Si el DNI no es válido, habrá una excepción... que se debe capturar
			if(fNacimiento != null) {													// 		Como la fecha de nacimiento es opcional, se llama al constructor adecuado en función de si se tiene el dato o no
				nuevo = new Alumno(dni, nombre, fNacimiento, titulacion, curso);
			}
			else {
				nuevo = new Alumno(dni, nombre, titulacion, curso);
			}
	
			tamVector = this._usuarios.size();											// 		Este cálculo se realia ahora porque el acceso al vector de alumnos no se hace con sistemas que garanticen la exclusión mutua; haciendo el cálculo lo más tarde posible se asegurará la certeza de lo datos
			do {
				insertar = this._usuarios.get(i).compareTo(nuevo);						// 			Es necesario buscar la posición de insercción, en aras de que el vector esté ordenado
	
				i++;
			} while(insertar < 0 || i + 1 == tamVector);								// 		Con la segunda parte de la comprobación se evita salirse del mismo por el final
	
			this._usuarios.add(insertar, nuevo);										// 		Por fin, la insercción
	
			System.out.println("El alumno ha sido agregado correctamente");				// 		Se informa del éxito de la operación
		}
		catch(RuntimeException e) {														// 	En caso de problemas...
			System.out.println("Error: " + e.getMessage());								// 		... también se informa
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

	private void addProfesor(String dni, String nombre, LocalDate fNacimiento, int creditos, Categorias categoria) {
		int			tamVector;															// Tamaño del vector en el que se insertará
		int			i			= 0;
		int			insertar;															// Posición de insercción
		Profesor	nuevo;																// 	Profesor a insertar

		try {																			// 	Si el DNI no es válido, habrá una excepción... que se debe capturar
			if(fNacimiento != null) {													// 		Como la fecha de nacimiento es opcional, se llama al constructor adecuado en función de si se tiene el dato o no
				nuevo = new Profesor(dni, nombre, fNacimiento, creditos, categoria);
			}
			else {
				nuevo = new Profesor(dni, nombre, creditos, categoria);
			}

			tamVector = this._usuarios.size();											// 		Este cálculo se realia ahora porque el acceso al vectore de profesores no se hace con sistemas que garanticen la exclusión mutua; haciendo el cálculo lo más tarde posible se asegurará la certeza de lo datos
			do {
				insertar = this._usuarios.get(i).compareTo(nuevo);						// 			Es necesario buscar la posición de insercción, en aras de que el vector esté ordenado

				i++;
			} while(insertar < 0 || i + 1 == tamVector);								// 		Con la segunda parte de la comprobación se evita salirse del mismo por el final

			this._usuarios.add(insertar, nuevo);										// 		Por fin, la insercción

			System.out.println("El profesor ha sido agregado correctamente");			// 		Se informa del éxito de la operación
		}
		catch(RuntimeException e) {														// 	En caso de problemas...
			System.out.println("Error: " + e.getMessage());								// 		... también se informa
		}
	}


	/**
	 * Método de muestra por pantalla de usuarios
	 * Muestra por pantalla el usuario de la posición dada
	 * 
	 * @param		posicion						int								Posición a mostrar
	 */

	public void printUsuario(int posicion) {
		System.out.println(this._usuarios.get(posicion));
	}


	/**
	 * Método de búsqueda de usuarios
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
	 * Método de eliminación de usuarios
	 * Elimina el usuario de la posición dada
	 * 
	 * @param		posicion						int								Posición a eliminar
	 */

	public void removeUsuario(int posicion) {
		this._usuarios.remove(posicion);
	}
}
