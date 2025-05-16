package modelo;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Tip extends Tarea {
	
	@JsonProperty("tipo")
	public static final String tipo = "Tip";
	
	
	private String rutaImagen;

	/* Constructor JSON */
	public Tip() {}
	
	/* Constructor por defecto */
	public Tip(String enunciado, String rutaImagen) {
		super(enunciado);
		setRutaImagen(rutaImagen);
	}
	
    @JsonProperty("imagen")
    public void setRutaImagen(String rutaImagen) {
    	if(rutaImagen == null || rutaImagen.length() == 0) {
    		this.rutaImagen = null;
    	} else {
    		this.rutaImagen = rutaImagen;
    	}
    }
	public Optional<String> getRutaImagen() {
		return Optional.ofNullable(rutaImagen);
	}
	
	@Override
	public Info crearInfo() {
		return new InfoTip(this);
	}

}
