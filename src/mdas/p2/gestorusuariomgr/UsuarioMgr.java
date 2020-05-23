package mdas.p2.gestorusuariomgr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UsuarioMgr implements IUsuarioMgt {
	final boolean DEBUG = true;

	private ArrayList <Alumno> _alumnos;

	public UsuarioMgr() {
		this._alumnos = new ArrayList<Alumno>();
	}

	@Override
	public int iniciarSesion() {
		// TODO Auto-generated method stub

		return 0;
	}

	@Override
	public Alumno buscarAlumno(int idAlumno) {
		Alumno res = null;

		for(Alumno a : this._alumnos) {
			if(a.id() == idAlumno) {
				res = a;
			}
		}

		return res;
	}

	@Override
	public boolean enviarNotificacion(int idUsuario, String mensaje) {
		// TODO Auto-generated method stub

		return false;
	}

	@Override
	public void mostrarMensaje(String mensaje) {
		if(this.DEBUG) {
			System.out.println(mensaje);
		}
		else {
			// TODO: Mandar correo electrónico
		}
	}

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
						this._alumnos.add(new Alumno(Integer.parseInt(linea[0]), linea[1], linea[2]));
					}
					else {
						// FIXME: ¿Y qué pasa cuando no es un alumno?
					}
				}
			}
			catch(IOException e) {
				System.out.println(e);
			}
			finally{
				fich.close();
			}
		}

		return (this._alumnos.size() != 0);
	}
}

