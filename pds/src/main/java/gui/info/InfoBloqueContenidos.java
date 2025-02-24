package gui.info;

public class InfoBloqueContenidos extends Info {

	/* Atributos específicos de curso */
	private final boolean estaCompletado;
	
	public InfoBloqueContenidos(String tituloCurso, String descripcionCurso, String imagenCurso, boolean estaCompletado){
		super(tituloCurso, descripcionCurso, imagenCurso);
		this.estaCompletado = estaCompletado;
	}
	
	public boolean estaCompletado() {
		return estaCompletado;
	}
}
