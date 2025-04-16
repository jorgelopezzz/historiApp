package dominio.info;

import dominio.curso.Valoracion;

public class InfoValoracion extends Info {

	private int puntuacion;
	
	public InfoValoracion(String comentario, int puntuacion) {
		super(comentario);
		this.puntuacion = puntuacion;
	}
	
	public InfoValoracion(Valoracion valoracion) {
		this(valoracion.getComentario(), valoracion.getPuntuacion());
	}
	
	public int getPuntuacion() {
		return puntuacion;
	}

}
