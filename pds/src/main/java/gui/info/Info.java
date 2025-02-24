package gui.info;

public abstract class Info {

	private final String titulo;
	private final String descripcion;
	private final String rutaImagen;
	
	protected Info(String titulo, String descripcion, String rutaImagen) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.rutaImagen = rutaImagen;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public String getRutaImagen() {
		return rutaImagen;
	}

}
