package dominio.tarea;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

public class Tip extends Tarea {
	
	@JsonProperty
	private static final String tipo = "Tip";
	
	private String rutaImagen;

	/* Constructor JSON */
	public Tip() {}
	
	/* Constructor por defecto */
	public Tip(String enunciado, String rutaImagen) {
		super(enunciado);
		this.rutaImagen = rutaImagen;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}
	

}
