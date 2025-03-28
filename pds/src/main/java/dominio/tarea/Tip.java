package dominio.tarea;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

public class Tip extends Tarea {
	
	@JsonProperty
	private static final String tipo = "Tip";
	
	private String rutaImagen;

	/* Constructor JSON */
	public Tip() {}
	
	/* Constructor por defecto */
	public Tip(String enunciado, Optional<String> rutaImagen) {
		super(enunciado);
		if(rutaImagen == null) {
			throw new IllegalArgumentException(getClass().toString() + ": La ruta imagen no se ha recibido correctamente.");
		}
		this.rutaImagen = rutaImagen.orElse(null);
	}

	public Optional<String> getRutaImagen() {
		return Optional.ofNullable(rutaImagen);
	}
	

}
