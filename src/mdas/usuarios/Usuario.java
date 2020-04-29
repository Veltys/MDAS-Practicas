package mdas.usuarios;

import java.time.LocalDate;


/**
 * Clase Usuario
 * ...
 * 
 * @author	Rafael Carlos Méndez Rodríguez (i82meror)
 * @date	29/04/2020
 * @version	0.1.0
 */

public abstract class Usuario {
	private int _dni;
	private String _nombre;
	private String _alias;
	private LocalDate _f_nacimiento;

	/**
	 * Constructor de clase
	 * Crea un usuario a partir de su DNI y nombre
	 * 
	 * @param	dni								int								DNI
	 * @param	nombre							String							Nombre
	 */

	public Usuario(int dni, String nombre) {
		this._dni			= dni;
		this._nombre		= nombre;
		this._alias			= "";
		this._f_nacimiento	= LocalDate.MIN;
	}


	/**
	 * Constructor de clase
	 * Crea un usuario a partir de su DNI, nombre y fecha de nacimiento
	 * 
	 * @param	dni								int								DNI
	 * @param	nombre							String							Nombre
	 * @param	f_nacimiento					Date							Fecha de nacimiento
	 */

	public Usuario(int dni, String nombre, LocalDate f_nacimiento) {
		this._dni			= dni;
		this._nombre		= nombre;
		this._alias			= "";
		this._f_nacimiento	= f_nacimiento;
	}


	/**
	 * Observador de la variable privada _dni
	 * 
	 * @return									int								DNI
	 */

	public int dni() {
		return this._dni;
	}


	/**
	 * Modificador de la variable privada _dni
	 * 
	 * @param	dni								int								DNI
	 */

	public void dni(int dni) {
		// FIXME: Comprobar DNI

		this._dni = dni;
	}


	/**
	 * Observador de la variable privada _nombre
	 * 
	 * @return									String							Nombre
	 */

	public String nombre() {
		return this._nombre;
	}


	/**
	 * Modificador de la variable privada _nombre
	 * 
	 * @param	nombre							String							Nombre
	 */

	public void nombre(String nombre) {
		this._nombre = nombre;
	}


	/**
	 * Observador de la variable privada _alias
	 * 
	 * @return									String							Alias
	 */

	public String alias() {
		return this._alias;
	}


	/**
	 * Modificador de la variable privada _alias
	 * 
	 * @param	alias							String							Alias
	 */

	public void alias(String alias) {
		// FIXME: Generar alias

		this._alias = alias;
	}


	/**
	 * Observador de la variable privada _f_nacimiento
	 * 
	 * @return									LocalDate						Fecha de nacimiento
	 */

	public LocalDate f_nacimiento() {
		return this._f_nacimiento;
	}


	/**
	 * Modificador de la variable privada _f_nacimiento
	 * 
	 * @param	f_nacimiento					LocalDate						Fecha de nacimiento
	 */

	public void f_nacimiento(LocalDate f_nacimiento) {
		this._f_nacimiento = f_nacimiento;
	}


	/**
	 * Método de comprobación de usuarios duplicados
	 * 
	 * @param	otro							Usuario							Usuario a comprobar
	 * 
	 * @return									boolean							Si son o no duplicados
	 */

	@Override
	public boolean equals(Object otro) {
		Usuario otro_usuario;

		if (this == otro) {
			return true;
		}
		else if(otro == null) {
			return false;
		}
		else if(this.getClass() != otro.getClass()) {
			return false;
		}
		else {
			otro_usuario = (Usuario) otro;

			return otro_usuario.dni() == this._dni;
		}
	}


	/**
	 * Método "mágico" cuando una clase es usada como String
	 * 
	 * @return									String							Representación en texto de los datos del usuario
	 */

	@Override
	public String toString() {
		String salida = "";

		salida = "[";
		salida += String.format("%08d", this._dni) + ", ";
		salida += this._nombre + " ";
		salida += this._alias + ", ";
		salida += this._f_nacimiento;
		salida += "]";

		return salida;
	}
}
