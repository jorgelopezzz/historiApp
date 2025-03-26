package gui.info.tarea;

import java.util.Optional;

import gui.info.Info;

public class InfoTip extends Info {

	private final String rutaImagen;
	
	public InfoTip(String texto, Optional<String> rutaImagen) {
		super(texto);
		this.rutaImagen = rutaImagen.orElse(null);
	}

	public Optional<String> getRutaImagen() {
		return Optional.ofNullable(rutaImagen);
	}

}
