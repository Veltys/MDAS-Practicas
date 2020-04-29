package mdas.usuarios;

import java.time.LocalDate;


/**
 * Clase Usuario
 * ...
 * 
 * @author	Rafael Carlos Méndez Rodríguez (i82meror)
 * @date	29/04/2020
 * @version	0.0.2
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
}
