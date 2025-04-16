package dominio;

import java.awt.EventQueue;


import gui.ventanas.SelectorVentana;
import repositorios.RepositorioCursos;

public class Lanzador {
	public static void main(final String[] args){
		
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