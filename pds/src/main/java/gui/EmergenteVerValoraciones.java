package gui;

import java.awt.BorderLayout;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dominio.HistoriApp;
import dominio.InfoValoracion;

@SuppressWarnings("serial")
public class EmergenteVerValoraciones extends Emergente {

	private String cursoTitulo;
	
	public EmergenteVerValoraciones(JFrame ventanaMadre, String cursoTitulo) {
		super(" Ver Valoraciones", GestorGUI.getInstancia().getColorBlanco(), ventanaMadre,
				3*ComponenteValoracion.ANCHO_PREDETERMINADO-105, ComponenteValoracion.ALTO_PREDETERMINADO*5);
		this.cursoTitulo = cursoTitulo;
		construir();
	}

	@Override
	protected void construir() {
		
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		
		List<InfoValoracion> infoBloques = HistoriApp.INSTANCE.obtenerValoracionesPorCursoNombre(cursoTitulo);
    	
		List<Componente> componentes = infoBloques.stream()
			    .map(ComponenteValoracion::new)
			    .collect(Collectors.toList());
		
		JScrollPane scroll = new ScrollValoracion(ventanaMadre, componentes);
		panelPrincipal.add(scroll, BorderLayout.CENTER);
		this.add(panelPrincipal);
	}
	
}
