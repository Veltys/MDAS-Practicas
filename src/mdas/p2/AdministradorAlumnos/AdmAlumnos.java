package mdas.p2.AdministradorAlumnos;

import java.util.List;

public class AdmAlumnos implements IInformarAlumno, IComprobarSancion {
	
	public void MostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}
	
	public int ComprobarSancion(int id_alumno) {
		List<int> ListReservas;
		LisReservas = new ArrayList<int>;
		
		List<int> ListIncidencias;
		LisIncidencias = new ArrayList<int>;
		
		ListReservas  = BuscarReservas(id_alumno);
		
		for (int i = 0; i < ListReservas.size(); i++) {
			ListIncidencias = BuscarIncidencia(reserva);
			
			for (int j = 0; j < ListIncidencias(); j++) {
				int sancion= BuscarSancion(incidencia);
			}
		
		}
		// Trabajamos todos con arraylist?
		// en la arquitectutra final pone que comprobar sancion devuelve una sancion pero duvuelve un int
		
		return sancion;
	}
	
	public String DescribirSancion(int codigo_sancion) {
		switch (codigo_sancion) {
		case 1:
			string mensaje=""
			break;
			
		case 2:
			string mensaje=""
			break;
			
		case 3:
			string mensaje=""
			break;
	
	
		case 4:
			string mensaje=""
			break;

		default:
			break;
		}
		
		return mensaje;
	}
	
}
