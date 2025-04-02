package dominio.info.contenidos;

import dominio.curso.Curso;

public final class InfoCurso extends InfoContenidos {
	
	/* Atributos específicos de curso */
	private final boolean estaMatriculado;
	
	public InfoCurso(String tituloCurso, String descripcionCurso, String imagenCurso, boolean estaMatriculado){
		super(tituloCurso, descripcionCurso, imagenCurso);
		this.estaMatriculado = estaMatriculado;
	}
	
	public InfoCurso(Curso curso) {
		super(curso.getTitulo(), curso.getDescripcion(), curso.getRutaImagen());
		estaMatriculado = curso.getMatricula() == null ? false : true;
	}
	
	public boolean estaMatriculado() {
		return estaMatriculado;
	}

}
