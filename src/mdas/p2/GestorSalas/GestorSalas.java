package mdas.p2.GestorSalas;

import java.util.ArrayList;
import java.util.List;

import mdas.p2.gestorreservamgr.Reserva;
import mdas.p2.gestorusuariomgr.Usuario;

/**
 * Clase GestorSalas
 *
 * @author			Javier Ortiz Aragones
 * @date			19/05/2020
 * @version			1.0.0
 */

public class GestorSalas implements IReserva, ISala{
	
	
	//IReserva

	public List<Integer> BuscarReservas(int id_alumno) {
		
		List<Integer> reservas=new ArrayList<Integer>();
		reservas=Reserva.ObtenerReservas();
		List<Integer> reservas_alumno=new ArrayList<Integer>();
		for(int i=0;i<reservas.size();i++) {
			if(id_alumno==Reserva.ObtenerAlumnoPorReserva(reservas.get(i))) {
				reservas_alumno.add(reservas.get(i));
			}
		}		
		return reservas_alumno;
	}	
	
	public Boolean ConfirmarReserva(int id_reserva) {
		
		return Reserva.ConfirmarReserva(id_reserva);
	}	
	
	public Boolean EliminarReserva(int id_reserva) {
		
		return Reserva.EliminarReserva(id_reserva);
	
	}
	
	//ISala
	
	public List<Integer> BuscarSala(int aforo, List<Integer> id_recursos) {
		
		
		List<Integer> salas=new ArrayList<Integer>();
		List<Integer> salas_disponibles=new ArrayList<Integer>();
		salas=Reserva.ObtenerSalas();
		for(int i=0;i<salas.size();i++) {
			if(aforo<=Reserva.ObtenerAforoSala(salas.get(i))) {
				List<Integer> recursos_sala=new ArrayList<Integer>();
				recursos_sala=Reserva.ObtenerRecursosSala(salas.get(i));
				for(int j=0;j<recursos_sala.size();j++) {
					int recursos_encontrados=0;
					for(int k=0;k<id_recursos.size();k++) {
						if(recursos_sala.get(j)==id_recursos.get(k)) {
							recursos_encontrados++;
						}
					}
					if(recursos_encontrados==recursos_sala.size()) {
						salas_disponibles.add(salas.get(i));
					}
				}
				
			}
		}
		
		return salas_disponibles;
	}
	
	public Boolean ConfirmarRegistro(int id_sala) {
		
		return Reserva.ConfirmarRegistro(id_sala);

	}
	
	public int ElegirSala(int aforo, List<Integer> id_salas) {
		
		for(int i=0;i<id_salas.size();i++) {
			if(aforo==Reserva.ObtenerAforoSala(id_salas.get(i))) {
				return id_salas.get(i);
			}
		}
		
		return 0;
	}
	
	public Boolean ValidarDatos(String nombre, int aforo, int tipo, String ubicacion, List<Integer> recursos) {
		
		if(aforo<1) {
			return false;
		}
		
		return true;
	}
	
}
