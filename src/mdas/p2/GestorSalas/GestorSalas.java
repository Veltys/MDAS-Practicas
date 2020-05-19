package mdas.p2.GestorSalas;

import java.util.ArrayList;
import java.util.List;

import mdas.p2.gestorreservamgr.ReservaMgr;

/**
 * Clase GestorSalas
 *
 * @author			Javier Ortiz Aragones
 * @date			19/05/2020
 * @version			1.0.0
 */

public class GestorSalas implements IReserva, ISala{
	
	ReservaMgr _reservaMgr;
	
	public GestorSalas() {
		_reservaMgr=new ReservaMgr();
	}
	
	
	//IReserva

	public ArrayList<Integer> BuscarReservas(int id_alumno) {
		
		ArrayList<Integer> reservas=new ArrayList<Integer>();
		reservas=_reservaMgr.ObtenerReservas();
		ArrayList<Integer> reservas_alumno=new ArrayList<Integer>();
		for(int i=0;i<reservas.size();i++) {
			if(id_alumno==ReservaMgr.ObtenerAlumnoPorReserva(reservas.get(i))) {
				reservas_alumno.add(reservas.get(i));
			}
		}		
		return reservas_alumno;
	}	
	
	public Boolean ConfirmarReserva(int id_reserva) {
		
		return _reservaMgr.confirmarReserva(id_reserva);
	}	
	
	public Boolean EliminarReserva(int id_reserva) {
		
		return _reservaMgr.eliminarReserva(id_reserva);
	
	}
	
	//ISala
	
	public ArrayList<Integer> BuscarSala(int aforo, ArrayList<Integer> id_recursos) {
		
		
		ArrayList<Integer> salas=new ArrayList<Integer>();
		ArrayList<Integer> salas_disponibles=new ArrayList<Integer>();
		salas=_reservaMgr.obtenerSalas();
		for(int i=0;i<salas.size();i++) {
			if(aforo<=_reservaMgr.obtenerAforoSala(salas.get(i))) {
				ArrayList<Integer> recursos_sala=new ArrayList<Integer>();
				recursos_sala=_reservaMgr.obtenerRecursosSala(salas.get(i));
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
		
		return _reservaMgr.confirmarRegistro(id_sala);

	}
	
	public int ElegirSala(int aforo, ArrayList<Integer> id_salas) {
		
		for(int i=0;i<id_salas.size();i++) {
			if(aforo==_reservaMgr.obtenerAforoSala(id_salas.get(i))) {
				return id_salas.get(i);
			}
		}
		
		return 0;
	}
	
	public Boolean ValidarDatos(String nombre, int aforo, int tipo, String ubicacion, ArrayList<Integer> recursos) {
		
		if(aforo<1) {
			return false;
		}
		
		return true;
	}
	
}
