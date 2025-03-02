package gui.ventanas;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.GestorGUI;
import gui.componentes.Componente;
import gui.componentes.ComponenteBloque;
import gui.componentes.ComponenteCurso;
import gui.info.InfoBloque;
import gui.info.InfoCurso;
import gui.scroll.Scroll;
import gui.scroll.ScrollBloques;

@SuppressWarnings("serial")
public class VentanaBloques extends VentanaMenu {

	private ComponenteCurso cursoSeleccionado;
		
	public VentanaBloques(SelectorVentana selector, ComponenteCurso cursoSeleccionado) {
	        super(selector);
	        
	        this.cursoSeleccionado = cursoSeleccionado;
	    }

    @Override
    protected void construir() {
        super.construir();
    }

	@Override
	protected void construirScrollMenu() {
		
		//controlador
		
		ComponenteBloque[] componentes = new ComponenteBloque[5];
		for (int i = 1; i < 5; i++) {
	        componentes[i] = new ComponenteBloque(new InfoBloque(
	                "Arte renacentista", "Descubre el esplendor del Renacimiento, una era de innovación artística y cultural que transformó la historia del arte. Exploraremos las obras maestras de artistas como Leonardo da Vinci, Miguel Ángel y Rafael, analizando sus técnicas, influencias y el contexto histórico que dio forma a este movimiento. Ideal para apasionados del arte y la historia.", 
	                //"C:/Users/aleja/git/historiApp/pds/resources/" + (new Random().nextInt(5)+1) + ".png", true));
	                "C:\\Users\\jorge\\git\\repository\\proyectoPDS\\historiApp\\pds\\resources\\" + (new Random().nextInt(5)+1) + ".png", true));
	    }
		componentes[0] = new ComponenteBloque(new InfoBloque(
                "Arte moderno", "Explora las revoluciones artísticas desde finales del siglo XIX hasta el siglo XX, con movimientos como el impresionismo, cubismo, surrealismo y expresionismo. Analizaremos las obras de artistas como Van Gogh, Picasso y Dalí, comprendiendo su impacto en la evolución del arte. Ideal para quienes desean adentrarse en la creatividad y las vanguardias modernas.", 
                //"C:/Users/aleja/git/historiApp/pds/resources/" + (new Random().nextInt(5)+1) + ".png", true));
                "C:\\Users\\jorge\\git\\repository\\proyectoPDS\\historiApp\\pds\\resources\\" + (new Random().nextInt(5)+1) + ".png", true));
		scroll = new ScrollBloques(VentanaBloques.this, selector, componentes);
		
	}
		
}
