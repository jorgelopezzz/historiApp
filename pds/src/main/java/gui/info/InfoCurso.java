package gui.info;

public final class InfoCurso extends Info {
	
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
