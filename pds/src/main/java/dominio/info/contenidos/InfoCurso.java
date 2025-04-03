package dominio.info.contenidos;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import dominio.curso.Curso;

public final class InfoCurso extends InfoContenidos {
	
	/* Atributos específicos de curso */
	private final boolean estaMatriculado;
	
	public InfoCurso(String tituloCurso, String descripcionCurso, String imagenCurso, boolean estaMatriculado){
		super(tituloCurso, descripcionCurso, imagenCurso);
		this.estaMatriculado = estaMatriculado;
	}
	
	public InfoCurso(Curso curso) {
		this(curso.getTitulo(), curso.getDescripcion(), curso.getRutaImagen(),
				curso.getMatricula() == null ? false : true);
	}
	
	public static List<InfoCurso> getListInfoCurso(List<Curso> cursos) {
		return cursos.stream() 
					 .map(InfoCurso::new)
					 .collect(Collectors.toList());
	}
	
	public boolean estaMatriculado() {
		return estaMatriculado;
	}

}
