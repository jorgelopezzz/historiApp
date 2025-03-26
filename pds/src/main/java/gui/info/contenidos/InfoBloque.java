package gui.info.contenidos;

public class InfoBloque extends InfoContenidos {

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
