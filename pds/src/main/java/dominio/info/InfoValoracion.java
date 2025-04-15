package dominio.info;

public class InfoValoracion extends Info {

	private int puntuacion;
	
	public InfoValoracion(String comentario, int puntuacion) {
		super(comentario);
		this.puntuacion = puntuacion;
	}
	
	public int getPuntuacion() {
		return puntuacion;
	}

}
