package modelo;

import java.util.List;
import java.util.stream.Collectors;

import controlador.HistoriApp;

public final class InfoCurso extends InfoContenidos {
	
	/* Atributos específicos de curso */
	private final boolean estaMatriculado;
	
	public InfoCurso(String tituloCurso, String descripcionCurso, String imagenCurso, boolean estaMatriculado){
		super(tituloCurso, descripcionCurso, imagenCurso);
		this.estaMatriculado = estaMatriculado;
	}
	
	public InfoCurso(Curso curso) {
		this(curso.getTitulo(), curso.getDescripcion(), curso.getRutaImagen(),
				HistoriApp.INSTANCE.usuarioMatriculado(curso.getTitulo()));
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
