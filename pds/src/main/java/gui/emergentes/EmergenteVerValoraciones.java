package gui.emergentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dominio.HistoriApp;
import dominio.info.InfoValoracion;
import dominio.info.contenidos.InfoBloque;
import gui.GestorGUI;
import gui.componentes.Componente;
import gui.componentes.ComponenteValoracion;
import gui.componentes.contenidos.ComponenteBloque;
import gui.scroll.ScrollBloques;
import gui.scroll.ScrollValoracion;
import gui.ventanas.VentanaBloques;

public class EmergenteVerValoraciones extends Emergente {

	public EmergenteVerValoraciones(JFrame ventanaMadre) {
		super(" Ver Valoraciones", GestorGUI.getInstancia().getColorBlanco(), ventanaMadre,
				3*ComponenteValoracion.ANCHO_PREDETERMINADO-105, ComponenteValoracion.ALTO_PREDETERMINADO*5);
		construir();
	}

	@Override
	protected void construir() {
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		
		List<InfoValoracion> infoBloques = /*HistoriApp..*/ List.of( 
				new InfoValoracion("El curso no me ha gustado nada. No lo recomiendo.", 1),
				new InfoValoracion("Un curso genial!", 7),
				new InfoValoracion("rev1", 4),
				new InfoValoracion("rev1", 2)); 
    	
		List<Componente> componentes = infoBloques.stream()
			    .map(ComponenteValoracion::new)
			    .collect(Collectors.toList());
		
		JScrollPane scroll = new ScrollValoracion(ventanaMadre, componentes);
		panelPrincipal.add(scroll, BorderLayout.CENTER);
		this.add(panelPrincipal);
	}
	
}
