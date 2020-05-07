package mdas.usuarios;


import java.time.LocalDate;


/**
 * Clase Usuario
 * Almacena datos de un usuario del sistema
 * Implementa la interfaz comparable, para facilitar su ordenación
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			07/05/2020
 * @version			2.0.4
 */

public abstract class Usuario implements Comparable<Usuario> {
	private int			_dni;
	private String		_nombre;
	private String		_alias;
	private LocalDate	_fNacimiento;


	/**
	 * Calculadora de letra del DNI
	 *
	 * @param		dni								int								DNI sin letra a calcular
	 *
	 * @return										char							Letra calculada
	 */

	public static char calcularLetraDni(int dni) {
		char[] letras = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

		return letras[dni % 23];
	}


	/**
	 * Generador de un alias a partir de un nombre
	 * TODO: Eliminar caracteres no ASCII del alias
	 *
	 * @param		nombre							String							Nombre
	 *
	 * @return										String							Alias
	 */

	public static String generateAlias(String nombre) {
		int			tam_vector;
		String[]	nombre_y_apellidos	= nombre.split(" ");

		tam_vector = nombre_y_apellidos.length;

		return (nombre_y_apellidos[tam_vector - 2].substring(0, 2)).toLowerCase() + (nombre_y_apellidos[tam_vector - 1].substring(0, 2)).toLowerCase() + (nombre_y_apellidos[0].substring(0, 1)).toLowerCase();
	}


	/**
	 * Validador de DNI
	 *
	 * @param		dni								String							DNI a validar
	 *
	 * @return										boolean							Si el DNI es válido o no
	 */

	public static boolean validarDni(String dni) {
		boolean res;

		int i;

		if((dni.length() == 9) && Character.isLetter(dni.charAt(8))) {
			res = true;

			for(i = 0; i < 8; i++) {
				if(!(Character.isDigit(dni.charAt(i)))) {
					res = false;

					break;
				}
			}

			return res && (Usuario.calcularLetraDni(Integer.parseInt(dni.substring(0, 8))) == Character.toUpperCase(dni.charAt(8)));
		}
		else {
			return false;
		}
	}

	/**
	 * Constructor de clase
	 * Crea un usuario a partir de su DNI y nombre
	 *
	 * @param		dni								String							DNI (con letra)
	 * @param		nombre							String							Nombre
	 *
	 * @exception									RuntimeException				Cuando el DNI no cumple los criterios de validación
	 */

	public Usuario(String dni, String nombre) {
		if(this.dni(dni)) {
			this._nombre		= nombre;
			this._alias			= Usuario.generateAlias(nombre);
			this._fNacimiento	= LocalDate.MIN;
		}
		else {
			throw new RuntimeException("El DNI no es válido");
		}
	}


	/**
	 * Constructor de clase
	 * Crea un usuario a partir de su DNI, nombre y fecha de nacimiento
	 *
	 * @param		dni								String							DNI (con letra)
	 * @param		nombre							String							Nombre
	 * @param		fNacimiento						LocalDate						Fecha de nacimiento
	 *
	 * @exception									RuntimeException				Cuando el DNI no cumple los criterios de validación
	 */

	public Usuario(String dni, String nombre, LocalDate fNacimiento) {
		if(this.dni(dni)) {
			this._nombre		= nombre;
			this._alias			= Usuario.generateAlias(nombre);
			this._fNacimiento	= fNacimiento;
		}
		else {
			throw new RuntimeException("El DNI no es válido");
		}
	}


	/**
	 * Observador de la variable privada _alias
	 *
	 * @return										String							Alias
	 */

	public String alias() {
		return this._alias;
	}


	/**
	 * Modificador de la variable privada _alias
	 *
	 * @param	alias								String							Alias
	 */

	public void alias(String alias) {
		this._alias = alias;
	}


	/**
	 * Método de comparación de usuarios
	 *
	 * @param	otro								Usuario							Usuario a comparar
	 *
	 * @return										int								El resultado de la comparación
	 */

	@Override
	public int compareTo(Usuario otro) {
		return this._dni - otro.dni();
	}


	/**
	 * Observador de la variable privada _dni
	 *
	 * @return										int								DNI
	 */

	public int dni() {
		return this._dni;
	}


	/**
	 * Modificador de la variable privada _dni
	 *
	 * @param		dni								int								DNI
	 *
	 * @return										bool							Si el DNI es válido y, por tanto, se ha guardado
	 */

	public boolean dni(String dni) {
		if(Usuario.validarDni(dni)) {
			this._dni = Integer.parseInt(dni.substring(0, 8));

			return true;
		}
		else {
			return false;
		}
	}


	/**
	 * Método de comprobación de usuarios duplicados
	 *
	 * @param		otro							Usuario							Usuario a comprobar
	 *
	 * @return										boolean							Si son o no duplicados
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
	 * Observador de la variable privada _fNacimiento
	 *
	 * @return										LocalDate						Fecha de nacimiento
	 */

	public LocalDate fNacimiento() {
		return this._fNacimiento;
	}


	/**
	 * Modificador de la variable privada _fNacimiento
	 *
	 * @param		fNacimiento						LocalDate						Fecha de nacimiento
	 */

	public void fNacimiento(LocalDate fNacimiento) {
		this._fNacimiento = fNacimiento;
	}


	/**
	 * Observador de la variable privada _nombre
	 *
	 * @return										String							Nombre
	 */

	public String nombre() {
		return this._nombre;
	}


	/**
	 * Modificador de la variable privada _nombre
	 *
	 * @param		nombre							String							Nombre
	 */

	public void nombre(String nombre) {
		this._nombre = nombre;
	}


	/**
	 * Método "mágico" cuando una clase es usada como String
	 *
	 * @return										String							Representación en texto de los datos del usuario
	 */

	@Override
	public String toString() {
		String salida = "";

		salida = "[";
		salida += String.format("%08d", this._dni) + ", ";
		salida += this._nombre + ", ";
		salida += this._alias + ", ";
		salida += this._fNacimiento;
		salida += "]";

		return salida;
	}
}
