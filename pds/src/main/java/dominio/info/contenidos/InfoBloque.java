package dominio.info.contenidos;

import dominio.curso.BloqueContenidos;

public class InfoBloque extends InfoContenidos {

	/* Atributos específicos de curso */
	private final boolean estaCompletado;
	
	public InfoBloque(String tituloCurso, String descripcionCurso, String imagenCurso, boolean estaCompletado){
		super(tituloCurso, descripcionCurso, imagenCurso);
		this.estaCompletado = estaCompletado;
	}
	
	public InfoBloque(BloqueContenidos bloque) {
		super(bloque.getTitulo(), bloque.getDescripcion(), bloque.getRutaImagen());
		estaCompletado = bloque.getCertBloque() == null ? false : true;
	}
	
	public boolean estaCompletado() {
		return estaCompletado;
	}
}
