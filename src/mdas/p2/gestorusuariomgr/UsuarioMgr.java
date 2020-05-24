package mdas.p2.gestorusuariomgr;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Clase UsuarioMgr
 * Componente de gestión de usuarios del sistema
 * Implementa la interfaz IUsuarioMgt
 *
 * @author		Unai Friscia Pérez (unaif)
 * @date		24/05/2020
 * @version		1.0.4
 *
 */

public class UsuarioMgr implements IUsuarioMgt {
	static	private	Scanner				_entrada	= new Scanner(System.in);
	static	private	UsuarioMgr			_instance	= null;
	private			ArrayList<Usuario>	_usuarios;


	/**
	 * Constructor de clase
	 * Privado, requisito del patrón Singleton
	 * Inicializa las listas del gestor
	 */

	private UsuarioMgr() {
		this._usuarios	= new ArrayList<Usuario>();
	}


	/**
	 * Método estático para obtener la única instancia válida (o crearla si no existe) del gestor
	 *
	 * @return										UsuarioMgr						Instancia del gestor
	 */

	public static UsuarioMgr getInstance() {
		if(UsuarioMgr._instance == null) {
			UsuarioMgr._instance = new UsuarioMgr();
		}

		return UsuarioMgr._instance;
	}


	/**
	 * Metodo para obtener un Alumno de la lista de Usuarios a partir de su id
	 *
	 * @param		idAlumno						int								ID del alumno a buscar
	 *
	 * @return										Alumno							Alumno encontrado (null si no)
	 */

	@Override
	public Alumno buscarAlumno(int idAlumno) {
		Usuario res = this.buscarUsuario(idAlumno);

		if(res instanceof Alumno) {
			return (Alumno) res;
		}
		else {
			return null;
		}
	}


	/**
	 * Metodo para obtener un Alumno de la lista de usuarios a partir de su email
	 *
	 * @param		email							String							Email del alumno a buscar
	 *
	 * @return										Alumno							Alumno encontrado (null si no)
	 */

	private Alumno buscarAlumno(String email) {
		Alumno res = null;

		for(Usuario u : this._usuarios) {
			res = (Alumno) u;
			if(email.equals(res.correo())) {
				break;
			}
		}

		return res;
	}


	/**
	 * Metodo para obtener un Empleado de la lista de Usuarios a partir de su ID
	 *
	 * @param		idAlumno						int								ID del empleado a buscar
	 *
	 * @return										Empleado						Empleado encontrado (null si no)
	 */

	@Override
	public Empleado buscarEmpleado(int idEmpleado) {
		Usuario res = this.buscarUsuario(idEmpleado);

		if(res instanceof Empleado) {
			return (Empleado) res;
		}
		else {
			return null;
		}
	}


	/**
	 * Metodo para obtener un Usuario de la lista de Usuarios a partir de su ID
	 *
	 * @param		idUsuario						int								ID del usuario que buscamos en la lista de usuarios
	 *
	 * @return										Usuario							Objeto Usuario que buscamos
	 */

	private Usuario buscarUsuario(int idUsuario) {
		Usuario res = null;

		for(Usuario u : this._usuarios) {
			if(u.id() == idUsuario) {
				res = u;

				break;
			}
		}

		return res;
	}


	/**
	 * Metodo que carga en la lista de usuarios los usuarios que se encuentran guardados en un fichero
	 *
	 * @param		ficheroUsuarios					String							Ruta del fichero donde se encuentran los usuarios
	 *
	 * @return										Boolean							Inidicación si la carga de usuarios ha sido exitosa o erronea
	 */

	@Override
	public boolean cargar(String ficheroUsuarios) {
		// TODO: Scanner es muy lento, ¿cambiar a BufferedReader?

		File f1 = new File(ficheroUsuarios);

		if(f1.exists()) {
			Scanner fich = null;

			try {
				fich = new Scanner(f1);

				while(fich.hasNext()) {
					String[] linea = fich.nextLine().split(",");

					if(linea.length != 2) {
						this._usuarios.add(new Alumno(Integer.parseInt(linea[0]), linea[1], linea[2]));
					}
					else {
						this._usuarios.add(new Empleado(Integer.parseInt(linea[0]), linea[1]));
					}
				}

				fich.close();
			}
			catch(IOException e) {
				System.out.println(e);
			}
		}

		return (this._usuarios.size() != 0);
	}


	/**
	 * Metodo que envia un mensaje a un usuario, mostando el correo del usuario y el mensaje que se le envia
	 *
	 * @param		idUsuario						String							Identificador del usuario al que se le quiere enviar el mensaje
	 * @param		mensaje							String							Mensjae que se enviará al usuario
	 *
	 * @return										Boolean							Resultado de la operación
	 */

	@Override
	public boolean enviarNotificacion(int idUsuario, String mensaje) {

		Alumno aux = this.buscarAlumno(idUsuario);

		if(aux == null) {
			System.out.println("Error en el envío del mensaje");
			return false;
		}

		System.out.println("Email: " + aux.correo());

		System.out.println("Mensaje: ");

		System.out.println(mensaje);

		System.out.println("Mensaje enviado con exito");

		return true;

	}


	/**
	 * Metodo que permite al usuario iniciar su sesión
	 *
	 * @return										int								ID del usuario que ha inicado sesion
	 */

	@Override
	public int iniciarSesion() {
		// String contrasenya;
		String email;

		System.out.print("Introduzca su correo electrónico: ");
		email = UsuarioMgr._entrada.nextLine();

		System.out.print("Introduzca su contraseña: ");
		/* contrasenya = */ UsuarioMgr._entrada.nextLine();

		System.out.println("Enviando datos al gestor de sesiones de la UCO");
		System.out.println("Espere, por favor...");

		try {
			Thread.sleep(5 * 1000);
		} catch (InterruptedException e) {
			// No es necesario hacer nada
		}

		Alumno aux = this.buscarAlumno(email);

		if((aux != null) && email.equals(aux.correo())) {
			return aux.id();
		}
		else {
			return -1;
		}
	}


	/**
	 * Metodo que muestra un mensaje por pantalla
	 *
	 * @param		mensaje							String							Mensaje que se muestra por pantalla
	 *
	 */

	@Override
	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}


	/**
	 * Observador del nombre de un usuario
	 *
	 * @param		int								idUsuario						ID del usuario
	 *
	 * @return										String							Nombre del usuario (null si no encontrado)
	 */

	@Override
	public String nombre(int idUsuario) {
		String res = null;

		for(Usuario u: this._usuarios) {
			if(u.id() == idUsuario) {
				res = u.nombre();

				break;
			}
		}

		return res;
	}
}
