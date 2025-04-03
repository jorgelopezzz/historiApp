package dominio.info.tarea;

import java.util.Optional;

import dominio.info.Info;
import dominio.tarea.Tip;

public class InfoTip extends Info {

	private final String rutaImagen;
	
	public InfoTip(String texto, Optional<String> rutaImagen) {
		super(texto);
		this.rutaImagen = rutaImagen.orElse(null);
	}

	public InfoTip(Tip tip) {
		this(tip.getEnunciado(), tip.getRutaImagen());
	}
	
	public Optional<String> getRutaImagen() {
		return Optional.ofNullable(rutaImagen);
	}

}
