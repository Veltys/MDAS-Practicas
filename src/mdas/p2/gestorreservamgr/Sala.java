package mdas.p2.gestorreservamgr;


import java.util.ArrayList;

import mdas.p2.gestorreservamgr.Recurso;


/**
 * Clase Sala
 * Almacena los datos de una sala
 *
 * @author			Rafael Carlos Méndez Rodríguez (i82meror)
 * @date			20/05/2020
 * @version			0.1.0
 */

public class Sala {
	private int					_id;
	private int					_aforo;
	private ArrayList<Recurso>	_recursos;											// FIXME: Reformar


	// TODO: Constructor


	// TODO: Comentar

	public int aforo() {
		return this._aforo;
	}


	// TODO: Comentar

	public int id() {
		return this._id;
	}


	// TODO: Comentar

	public int numRecursos() {
		return this._recursos.size();
	}

	// TODO: Comentar

	public Recurso recurso(int posicion) {
		return this._recursos.get(posicion);
	}


	// TODO: Comentar

	public void recurso(Recurso r) {
		this._recursos.add(r);
	}


	// TODO: Comentar

	public boolean tengoRecursos(ArrayList<Integer> idsRecursos) {
		// TODO Auto-generated method stub
		return false;
	}
}
