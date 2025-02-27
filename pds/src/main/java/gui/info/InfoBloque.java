package gui.info;

public class InfoBloque extends Info {

	/* Atributos específicos de curso */
	private final boolean estaCompletado;
	
	public InfoBloque(String tituloCurso, String descripcionCurso, String imagenCurso, boolean estaCompletado){
		super(tituloCurso, descripcionCurso, imagenCurso);
		this.estaCompletado = estaCompletado;
	}
	
	public boolean estaCompletado() {
		return estaCompletado;
	}
}
