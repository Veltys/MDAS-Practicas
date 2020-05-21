package mdas.p2.gestorusuariomgr;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UsuarioMgr implements IUsuarioMgt {

	private ArrayList <Alumno> _alumnos;

	public UsuarioMgr() {
		this._alumnos = new ArrayList<Alumno>();
	}

	@Override
	public int iniciarSesion() {
		// TODO Auto-generated method stub
		return 0;
	}

	public ArrayList <Alumno> getAlumnos() {
		return this._alumnos;
	}

	@Override
	public Alumno buscaralumno(int id_alumno) {

		Alumno res = new Alumno();

		for(Alumno i : this._alumnos) {
			if(i.id() == id_alumno) {
				res = i;
			}
		}

		return res;
	}

	@Override
	public boolean enviarnotificacion(int id_usuario, String Mensaje) {
		// TODO Auto-generated method stub
		//Para facilitar, a cada alumno añadir una variable mensaje.
		return false;
	}

	@Override
	public void mostrarmensaje(String mensaje) {
		// TODO Auto-generated method stub
		System.out.println(mensaje);
	}

	@Override
	public boolean cargar(String ficherousuarios) {
		File f1 = new File(ficherousuarios);
		if(f1.exists()) {
			Scanner fich = null;
			try {
				fich = new Scanner(f1);
				while(fich.hasNext()) {
					String[] linea = fich.nextLine().split(",");
					if(linea.length != 2) {
						this._alumnos.add(new Alumno(Integer.parseInt(linea[0]), linea[1], linea[2]));
					}
				}
			}catch(IOException e) {
				System.out.println(e);
			}finally{
				fich.close();
			}
		}
		return (this._alumnos.size() != 0);
	}
}

