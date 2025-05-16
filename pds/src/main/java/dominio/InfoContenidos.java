package dominio;

public abstract class InfoContenidos extends Info {

	private final String descripcion;
	private final String rutaImagen;
	
	protected InfoContenidos(String titulo, String descripcion, String rutaImagen) {
		super(titulo);
		this.descripcion = descripcion;
		this.rutaImagen = rutaImagen;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public String getRutaImagen() {
		return rutaImagen;
	}

}
