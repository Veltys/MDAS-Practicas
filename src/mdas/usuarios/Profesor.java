package mdas.usuarios;


import java.time.LocalDate;

import mdas.usuarios.Categorias;
import mdas.usuarios.Usuario;


/**
 * Clase Profesor
 * Extiende a la clase Usuario para almacenar datos concretos de un profesor
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			07/05/2020
 * @version			1.0.2
 */

public class Profesor extends Usuario {
	private int _creditos;
	private Categorias _categoria;


	/**
	 * Constructor de clase
	 * Crea un profesor a partir de su DNI, nombre, créditos impartidos y categoría profesional
	 *
	 * @param		dni								String							DNI (con letra)
	 * @param		nombre							String							Nombre
	 * @param		creditos						int								Créditos impartidos
	 * @param		categoria						Categoria						Categoría profesional
	 *
	 * @exception									RuntimeException				Cuando el DNI no cumple los criterios de validación
	 */

	public Profesor(String dni, String nombre, int creditos, Categorias categoria) {
		super(dni, nombre);

		this._creditos = creditos;
		this._categoria = categoria;
	}


	/**
	 * Constructor de clase
	 * Crea un profesor a partir de su DNI, nombre, fecha de nacimiento, créditos impartidos y categoría profesional
	 *
	 * @param		dni								String							DNI (con letra)
	 * @param		nombre							String							Nombre
	 * @param		fNacimiento						LocalDate						Fecha de nacimiento
	 * @param		creditos						int								Créditos impartidos
	 * @param		categoria						Categoria						Categoría profesional
	 *
	 * @exception									RuntimeException				Cuando el DNI no cumple los criterios de validación
	 */

	public Profesor(String dni, String nombre, LocalDate fNacimiento, int creditos, Categorias categoria) {
		super(dni, nombre, fNacimiento);

		this._creditos = creditos;
		this._categoria = categoria;
	}


	/**
	 * Observador de la variable privada _categoria
	 *
	 * @return										Categorias						Categoría profesional
	 */

	public Categorias categoria() {
		return this._categoria;
	}


	/**
	 * Modificador de la variable privada _categoria
	 *
	 * @param		categoria						Categorias						Categoría profesional
	 */

	public void categoria(Categorias categoria) {
		this._categoria = categoria;
	}


	/**
	 * Observador de la variable privada _creditos
	 *
	 * @return										int								Créditos impartidos
	 */

	public int creditos() {
		return this._creditos;
	}


	/**
	 * Modificador de la variable privada _creditos
	 *
	 * @param		creditos						int								Créditos impartidos
	 */

	public void creditos(int creditos) {
		this._creditos = creditos;
	}


	/**
	 * Método "mágico" cuando una clase es usada como String
	 *
	 * @return										String							Representación en texto de los datos del usuario
	 */

	@Override
	public String toString() {
		String salida = super.toString();

		salida = salida.substring(0, salida.length() - 3) + ", ";				// Es necesario recortar los dos caracteres finales para añadir más contenido
		salida += this._creditos + ", ";
		salida += this._categoria;
		salida += "]";

		return salida;
	}
}
