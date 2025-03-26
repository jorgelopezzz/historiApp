package dominio;

import java.awt.EventQueue;

import gui.ventanas.SelectorVentana;
import dominio.curso.RepositorioCursos;

public class Lanzador {
	public static void main(final String[] args){
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// SelectorVentana selector = new SelectorVentana();

					RepositorioCursos.INSTANCE.getCursos();
					new SelectorVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}