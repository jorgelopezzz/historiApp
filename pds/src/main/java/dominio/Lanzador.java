package dominio;

import java.awt.EventQueue;

import gui.SelectorVentana;
import jakarta.persistence.Persistence;
import repositorios.RepositorioCursos;
import repositorios.RepositorioUsuarios;

public class Lanzador {
	public static void main(final String[] args){
		
		RepositorioUsuarios.INSTANCE.init(Persistence.createEntityManagerFactory("usuarios"));
		RepositorioCursos.INSTANCE.init(new ServicioJSON());
		HistoriApp.INSTANCE.init(RepositorioUsuarios.INSTANCE, RepositorioCursos.INSTANCE);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new SelectorVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
								
			}
		});
	}
}