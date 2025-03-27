package dominio.tarea;

public class Tip extends Tarea {
	
	private String rutaImagen;
	
	public Tip(String enunciado, String rutaImagen) {
		super(enunciado);
		this.rutaImagen = rutaImagen;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}
	
}
