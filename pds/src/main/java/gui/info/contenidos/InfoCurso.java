package gui.info.contenidos;

public final class InfoCurso extends InfoContenidos {
	
	/* Atributos específicos de curso */
	private final boolean estaMatriculado;
	
	public InfoCurso(String tituloCurso, String descripcionCurso, String imagenCurso, boolean estaMatriculado){
		super(tituloCurso, descripcionCurso, imagenCurso);
		this.estaMatriculado = estaMatriculado;
	}
	
	public boolean estaMatriculado() {
		return estaMatriculado;
	}

}
