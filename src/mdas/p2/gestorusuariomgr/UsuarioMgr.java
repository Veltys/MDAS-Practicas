package mdas.p2.gestorusuariomgr;

import java.util.ArrayList;

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

}

