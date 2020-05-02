package mdas.usuarios;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mdas.usuarios.Alumno;
import mdas.usuarios.Profesor;
import mdas.usuarios.Categorias;


/**
 * Clase GestorUsuarios
 * Componente de gestión de usuarios del sistema
 * 
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			02/05/2020
 * @version			0.1.2
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
	 * Método para añadir un usuario a la lista correspondiente
	 */

	public void addUsuario() {
		char		tipo;																// Tipo de usuario a insertar
		int			i					= 0;
		int			insertar;															// Posición de insercción
		int			tamVector;															// Tamaño del vector en el que se insertará
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

		if(!("".contentEquals(str_fNacimiento))) {
			fNacimiento = LocalDate.parse(str_fNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}

		if(Character.toUpperCase(tipo) != 'P') {										// Cualquier cosa que no sea una "P" será tratada como un alumno (el tipo por defecto)
			int		curso;																// 	Curso del alumno a insertar
			String	titulacion;															// 	Titulación del alumno a insertar
			Alumno	nuevo;																// 	Alumno a insertar

			System.out.println("Introduzca la titulación: ");
			titulacion = entrada.next();

			System.out.println("Introduzca el curso: ");
			curso = entrada.nextInt();

			entrada.close();															// 	Se cierra el scanner, al no necesitarse más

			try {																		// 	Si el DNI no es válido, habrá una excepción... que se debe capturar
				if(fNacimiento != null) {												// 		Como la fecha de nacimiento es opcional, se llama al constructor adecuado en función de si se tiene el dato o no
					nuevo = new Alumno(dni, nombre, fNacimiento, titulacion, curso);
				}
				else {
					nuevo = new Alumno(dni, nombre, titulacion, curso);
				}

				tamVector = this._usuarios.size();										// 		Este cálculo se realia ahora porque el acceso al vector de alumnos no se hace con sistemas que garanticen la exclusión mutua; haciendo el cálculo lo más tarde posible se asegurará la certeza de lo datos
				do {
					insertar = this._usuarios.get(i).compareTo(nuevo);					// 			Es necesario buscar la posición de insercción, en aras de que el vector esté ordenado

					i++;
				} while(insertar < 0 || i + 1 == tamVector);							// 		Con la segunda parte de la comprobación se evita salirse del mismo por el final

				this._usuarios.add(insertar, nuevo);									// 		Por fin, la insercción

				System.out.println("El alumno ha sido agregado correctamente");			// 		Se informa del éxito de la operación
			}
			catch(RuntimeException e) {													// 	En caso de problemas...
				System.out.println("Error: " + e.getMessage());							// 		... también se informa
			}
		}
		else {																			// Operaciones equivalentes para la insercción de un profesor
			boolean		categoriaOk = false;											// 	Validador de la categoría profesional
			int			creditos;														// 	Créditos impartidos del profsor a insertar
			String		str_categoria;													// 	Categoría profesional del profsor a insertar antes de ser convertida al tipo Categorias
			Categorias	categoria = null;												// 	Categoría profesional del profsor a insertar ya convertida al tipo Categorias
			Profesor	nuevo;															// 	Profesor a insertar

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

			try {																		// 	Si el DNI no es válido, habrá una excepción... que se debe capturar
				if(fNacimiento != null) {												// 		Como la fecha de nacimiento es opcional, se llama al constructor adecuado en función de si se tiene el dato o no
					nuevo = new Profesor(dni, nombre, fNacimiento, creditos, categoria);
				}
				else {
					nuevo = new Profesor(dni, nombre, creditos, categoria);
				}

				tamVector = this._usuarios.size();										// 		Este cálculo se realia ahora porque el acceso al vectore de profesores no se hace con sistemas que garanticen la exclusión mutua; haciendo el cálculo lo más tarde posible se asegurará la certeza de lo datos
				do {
					insertar = this._usuarios.get(i).compareTo(nuevo);					// 			Es necesario buscar la posición de insercción, en aras de que el vector esté ordenado

					i++;
				} while(insertar < 0 || i + 1 == tamVector);							// 		Con la segunda parte de la comprobación se evita salirse del mismo por el final

				this._usuarios.add(insertar, nuevo);									// 		Por fin, la insercción

				System.out.println("El profesor ha sido agregado correctamente");		// 		Se informa del éxito de la operación
			}
			catch(RuntimeException e) {													// 	En caso de problemas...
				System.out.println("Error: " + e.getMessage());							// 		... también se informa
			}
		}
	}


	public int searchUsuario(IBuscadorUsuarios buscador, int dni) {
		int usuarios;

		// FIXME: Continuar aquí
		usuarios = buscador.buscarUsuario(this._usuarios);

		return usuarios;
	}
}
