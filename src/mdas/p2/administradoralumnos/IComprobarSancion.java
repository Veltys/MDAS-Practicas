package mdas.p2.administradoralumnos;


/**
 * Interfaz IComprobarSancion
 * Interfaz del componente AdministradorAlumno
 *
 * @author			Herminio Rodríguez García (i72rogah)
 * @date			26/05/2020
 * @version			1.0.1
 *
 */

public interface IComprobarSancion {
	abstract public	int comprobarSancion(int idAlumno);
	abstract public	String describirSancion(int codigoSancion);
}
