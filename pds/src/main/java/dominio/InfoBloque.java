package dominio;

import java.util.List;
import java.util.stream.Collectors;

public class InfoBloque extends InfoContenidos {

	/* Atributos específicos de curso */
	private final boolean estaCompletado;
	
	public InfoBloque(String tituloCurso, String descripcionCurso, String imagenCurso, boolean estaCompletado){
		super(tituloCurso, descripcionCurso, imagenCurso);
		this.estaCompletado = estaCompletado;
	}
	
	public InfoBloque(BloqueContenidos bloque) {
		this(bloque.getTitulo(), bloque.getDescripcion(), bloque.getRutaImagen(), 
				HistoriApp.INSTANCE.bloqueCompletado(bloque.getTitulo()));
	}
	
	public boolean estaCompletado() {
		return estaCompletado;
	}

	public static List<InfoBloque> getListInfoBloque(List<BloqueContenidos> bloques) {
		return bloques.stream() 
				 .map(InfoBloque::new)
				 .collect(Collectors.toList());
	}
}
