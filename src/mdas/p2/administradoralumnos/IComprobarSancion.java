package mdas.p2.administradoralumnos;

public interface IComprobarSancion {
	abstract public int ComprobarSancion(int id_alumno);

	static public String DescribirSancion(int codigo_sancion) {
		String mensaje;

		switch (codigo_sancion) {
		case 1:
			mensaje = "";

			break;

		case 2:
			mensaje = "";

			break;

		case 3:
			mensaje = "";

			break;


		case 4:
			mensaje = "";

			break;

		default:
			mensaje = "";

			break;
		}

		return mensaje;
	}
}
