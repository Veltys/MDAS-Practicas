package mdas.p2.gestorusuariomgr;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Clase UsuarioMgr
 * Componente de gestiÃ³n de usuarios del sistema
 * Implementa la interfaz IUsuarioMgt
 * 
 * @author unaif
 *
 */

public class UsuarioMgr implements IUsuarioMgt {
	final private boolean		_DEBUG		= true;

	static private UsuarioMgr	_instance	= null;
	private ArrayList<Usuario>	_usuarios;


	/**
	 * Constructor de clase
	 * Privado, requisito del patrÃ³n Singleton
	 * Inicializa las listas del gestor
	 */

	private UsuarioMgr() {
		this._usuarios	= new ArrayList<Usuario>();
	}


	/**
	 * MÃ©todo estÃ¡tico para obtener la Ãºnica instancia vÃ¡lida (o crearla si no existe) del gestor
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
 * Metodo estatico para obtener un Alumno de la lista de Usuarios a partir de su id
 * 
 * @param idAlumno: id del Alumno que buscamos
 * @return Alumno 	Objeto  Alumno que buscamos
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
	 * Metodo estatico para obtener un Empleado de la lista de Usuarios a partir de su id
	 * 
	 * @param idAlumno: id del Empleado que buscamos
	 * @return Empleado 	Objeto Empleado que buscamos
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
	 * Metodo estatico para obtener un Usuario de la lista de Usuarios a partir de si id
	 * 
	 * @param idUsuario: id del usuario que buscamos en la lista de usuarios
	 * @return Usuario	Objeto Usuario que buscamos
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
	 * Metodo estatico para obtener un Alumno de la lista de usuarios a partir de su email
	 * 
	 * @param email: Email del Alumno que buscamos
	 * @return Alumno 	Objeto Alumno que buscabamos
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
	 * Metodo estatico que carga en la lista de usuarios los usuarios que se encuentran guardados en un fichero
	 * 
	 * @param ficheroUsuarios: Ruta del fichero donde se encuentran los usuarios
	 * @return Boolean 	Inidicación si la carga de usuarios ha sido exitosa o erronea
	 */

	@Override
	public boolean cargar(String ficheroUsuarios) {
		// TODO: Scanner es muy lento, Â¿cambiar a BufferedReader?

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
	 * Metodo estatico que envia un mensaje a un usuario, mostando el correo del usuario y el mensaje que se le envia
	 * 
	 * @param idUsuario: Identificador del usuario al que se le quiere enviar el mensaje
	 * @param mensaje: Mensjae que se enviará al usuario
	 */

	@Override
	public boolean enviarNotificacion(int idUsuario, String mensaje) {
		
		Alumno aux = this.buscarAlumno(idUsuario);
		
		if(aux == null) {
			System.out.println("Error en el envia del mensaje");
			return false;
		}
		
		System.out.println("Email: " + aux.correo());
		
		System.out.println("Mensaje: ");
		
		System.out.println(mensaje);
		
		System.out.println("Mensaje enviado con exito");
		
		return true;
		
	}


	/**
	 * Metodo estatico que permite al usuario iniciar su sesión
	 * 
	 * @return int	Id del usuario que ha inicado sesion
	 */

	@Override
	public int iniciarSesion() {
		/*Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca su correo");
		String us = sc.nextLine();
		sc.nextLine();
		System.out.println("Introduzca su contraseña");
		String pass = sc.nextLine();
		
		System.out.println("Enviando datos al gestor de sesiones de la UCO");
		
		try {
            Thread.sleep(5*1000);
         } catch (Exception e) {
            System.out.println(e);
         }
		
		Alumno aux = this.buscarAlumno(us);
		
		if(aux.correo().equals(us)) {
			System.out.println("Exito en el inicio de sesión");
			return aux.id();
		}
		
		System.out.println("Error en el inicio de sesión");
		return -1;*/
		return (int) Math.random();
	}


	/**
	 * Metodo estatico que muestra un mensaje por pantalla 
	 * 
	 * @param mensaje: Mensaje que se muestra por pantalla
	 * 
	 */

	@Override
	public void mostrarMensaje(String mensaje) {
		if(this._DEBUG) {
			System.out.println(mensaje);
		}
		else {
			//this.enviarNotificacion(, mensaje); A que usuario se lo envio
		}
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
