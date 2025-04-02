package gui.componentes.tarea;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import dominio.info.tarea.InfoTipoTest;
import gui.GestorGUI;

public class ComponenteTipoTest extends ComponentePregunta {

	/* Cadenas por defecto */
	private static final String CABECERA_OPCIONES = "Selecciona una respuesta:";
	
	/* Componentes gráficos */
	private ButtonGroup grupoOpciones;
	private JLabel cabeceraOpciones;
	private List<String> opciones;
	private List<JRadioButton> botones;
	
	public ComponenteTipoTest(InfoTipoTest info) {
		super(info);
		this.opciones = List.copyOf(info.getOpciones());
		this.botones = new ArrayList<JRadioButton>();
		
		/* Construcción de etiquetas */
		super.construir(CABECERA);
		
		cabeceraOpciones = construirEtiquetaCabecera(CABECERA_OPCIONES);
		add(cabeceraOpciones);
		
		/* Construcción de las opciones */
		grupoOpciones = new ButtonGroup();
		opciones.stream()
			.map( op -> new JRadioButton(op))
			.forEach( b -> {
				
				/* Agrupación */
				grupoOpciones.add(b); // Nos aseguramos de que sólo se pueda elegir uno
				botones.add(b);
				
				/* Estilo */
				b.setOpaque(false);
				b.setFont(GestorGUI.getInstancia().getFuenteGrande());
				b.setForeground(GestorGUI.getInstancia().getColorBlanco());	
				
				/* Montaje */
				b.setAlignmentX(Component.LEFT_ALIGNMENT);
				add(b);
			});
		
	}
	
	@Override
	public Optional<String> getRespuesta() {
		for(JRadioButton boton : botones) {
			if(boton.isSelected())
				return Optional.ofNullable(boton.getText());
		}
		return Optional.empty();
	}

}
