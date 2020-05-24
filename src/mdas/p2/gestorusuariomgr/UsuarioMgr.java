package mdas.p2.gestorusuariomgr;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


// TODO: Comentar

public class UsuarioMgr implements IUsuarioMgt {
	final private boolean		_DEBUG		= true;

	static private UsuarioMgr	_instance	= null;
	private ArrayList<Usuario>	_usuarios;


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


	// TODO: Comentar

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


	// TODO: Comentar

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


	// TODO: Comentar

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


	// TODO: Comentar

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


	// TODO: Comentar

	@Override
	public boolean enviarNotificacion(int idUsuario, String mensaje) {
		// TODO Auto-generated method stub

		return false;
	}


	// TODO: Comentar

	@Override
	public int iniciarSesion() {
		// TODO Auto-generated method stub

		return 0;
	}


	// TODO: Comentar

	@Override
	public void mostrarMensaje(String mensaje) {
		if(this._DEBUG) {
			System.out.println(mensaje);
		}
		else {
			// TODO: Mandar correo electrónico
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
