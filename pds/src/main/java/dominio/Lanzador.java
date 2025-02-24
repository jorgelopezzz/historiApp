package dominio;

import java.awt.EventQueue;

import gui.ventanas.SelectorVentana;

public class Lanzador {
	public static void main(final String[] args){
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectorVentana selector = new SelectorVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}