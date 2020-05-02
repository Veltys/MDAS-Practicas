package mdas.usuarios;


import java.time.LocalDate;

import mdas.usuarios.Usuario;

/**
 * Clase Alumno
 * Extiende a la clase Usuario para almacenar datos concretos de un alumno
 * 
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			01/05/2020
 * @version			1.0.2
 */

public class Alumno extends Usuario {
	private int _curso;
	private String _titulacion;


	/**
	 * Constructor de clase
	 * Crea un alumno a partir de su DNI, nombre, titulación y curso
	 * 
	 * @param		dni								String							DNI (con letra)
	 * @param		nombre							String							Nombre
	 * @param		titulacion						String							Titulación
	 * @param		curso							int								Curso
	 * 
	 * @exception									RuntimeException				Cuando el DNI no cumple los criterios de validación
	 */

	public Alumno(String dni, String nombre, String titulacion, int curso) {
		super(dni, nombre);

		this._titulacion = titulacion;
		this._curso = curso;
	}


	/**
	 * Constructor de clase
	 * Crea un alumno a partir de su DNI, nombre, fecha de nacimiento, titulación y curso
	 * 
	 * @param		dni								String							DNI (con letra)
	 * @param		nombre							String							Nombre
	 * @param		fNacimiento						LocalDate						Fecha de nacimiento
	 * @param		titulacion						String							Titulación
	 * @param		curso							int								Curso
	 * 
	 * @exception									RuntimeException				Cuando el DNI no cumple los criterios de validación
	 */

	public Alumno(String dni, String nombre, LocalDate fNacimiento, String titulacion, int curso) {
		super(dni, nombre, fNacimiento);

		this._titulacion = titulacion;
		this._curso = curso;
	}


	/**
	 * Observador de la variable privada _curso
	 * 
	 * @return										int								Curso
	 */

	public int curso() {
		return this._curso;
	}


	/**
	 * Modificador de la variable privada _curso
	 * 
	 * @param		curso							int								Curso
	 */

	public void curso(int curso) {
		this._curso = curso;
	}


	/**
	 * Observador de la variable privada _titulacion
	 * 
	 * @return										String							Titulación
	 */

	public String titulacion() {
		return this._titulacion;
	}


	/**
	 * Modificador de la variable privada _titulacion
	 * 
	 * @param		titulacion						String							Titulación
	 */

	public void titulacion(String titulacion) {
		this._titulacion = titulacion;
	}


/**
 * Método "mágico" cuando una clase es usada como String
 * 
 * @return										String							Representación en texto de los datos del usuario
 */

	@Override
	public String toString() {
		String salida = super.toString();
	
		salida = salida.substring(0,-2) + ", ";
		salida += this._titulacion + ", ";
		salida += this._curso + "º";
		salida += "]";
	
		return salida;
	}
}
