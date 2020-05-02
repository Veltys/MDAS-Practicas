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
 * @date			01/05/2020
 * @version			0.1.0
 */

public class GestorUsuarios {
	private List<Alumno> _alumnos;
	private List<Profesor> _profesores;

	/**
	 * Constructor de clase
	 * Inicializa la lista de usuarios
	 */

	GestorUsuarios() {
		this._alumnos = new ArrayList<Alumno>();
		this._profesores = new ArrayList<Profesor>();
	}


	/**
	 * Método para añadir un usuario a la lista correspondiente
	 */

	public void addUsuario() {
		char		tipo;
		String		dni;
		String		nombre;
		String		str_fNacimiento;
		LocalDate	fNacimiento = null;

		Scanner entrada = new Scanner(System.in);

		System.out.println("¿Qué tipo de usuario se cargará? [A]lumno/[p]rofesor: ");
		tipo = entrada.next().charAt(0);

		System.out.println("Introduzca el DNI: ");
		dni = entrada.next();

		System.out.println("Introduzca el nombre: ");
		nombre = entrada.next();

		System.out.println("Introduzca la fecha de nacimiento en formato DD/MM/AAAA (opcional, dejar en blanco para continuar): ");
		str_fNacimiento = entrada.next();

		if(!str_fNacimiento.equals("")) {
			fNacimiento = LocalDate.parse(str_fNacimiento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		}

		if(tipo != 'p') {
			int		curso;
			String	titulacion;
			Alumno	nuevo;

			System.out.println("Introduzca la titulación: ");
			titulacion = entrada.next();

			System.out.println("Introduzca el curso: ");
			curso = entrada.nextInt();

			try {
				if(fNacimiento != null) {
					nuevo = new Alumno(dni, nombre, fNacimiento, titulacion, curso);
				}
				else {
					nuevo = new Alumno(dni, nombre, titulacion, curso);
				}

				this._alumnos.add(nuevo);

				System.out.println("El alumno ha sido agregado correctamente");
			}
			catch(RuntimeException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		else {
			boolean		categoriaOk = false;
			int			creditos;
			String		str_categoria;
			Categorias	categoria = null;
			Profesor	nuevo;

			System.out.println("Introduzca los créditos impartidos: ");
			creditos = entrada.nextInt();

			do {
				System.out.println("La lista de categorías profesionales disponible es la siguiente:");
				System.out.println(java.util.Arrays.asList(Categorias.values()));
				System.out.println("Introduzca la categoría profesional: ");
				str_categoria = entrada.next();

				for(Categorias c : Categorias.values()) {
					if(str_categoria.toUpperCase() == c.name()) {
						categoriaOk = true;

						categoria = c;

						break;
					}
				}

				if(!categoriaOk) {
					System.out.println("Error: La categoría profesional seleccionada es incorrecta");
				}
			} while(!categoriaOk);

			try {
				if(fNacimiento != null) {
					nuevo = new Profesor(dni, nombre, fNacimiento, creditos, categoria);
				}
				else {
					nuevo = new Profesor(dni, nombre, creditos, categoria);
				}

				this._profesores.add(nuevo);

				System.out.println("El profesor ha sido agregado correctamente");
			}
			catch(RuntimeException e) {
				System.out.println("Error: " + e.getMessage());
			}
		}

		entrada.close();
	}
}
