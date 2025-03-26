package gui.componentes.tarea;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import gui.GestorGUI;
import gui.campos.CampoDesplegable;
import gui.info.tarea.InfoTipoTest;

public class ComponenteTipoTest extends ComponentePregunta {

	/* Cadenas por defecto */
	private static final String CABECERA_OPCIONES = "Selecciona una respuesta:";
	
	/* Códigos para información */
	private static final int SIN_RESPUESTA = -1;
	
	/* Atributos de información */
	private final int indiceCorrecta;
	
	/* Componentes gráficos */
	private ButtonGroup grupoOpciones;
	private JLabel cabeceraOpciones;
	private List<String> opciones;
	private List<JRadioButton> botones;
	
	public ComponenteTipoTest(InfoTipoTest info) {
		super(info);
		this.indiceCorrecta = info.getCorrecta();
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
	public boolean evaluar() {
		return botones.get(indiceCorrecta).isSelected();
	}

}
