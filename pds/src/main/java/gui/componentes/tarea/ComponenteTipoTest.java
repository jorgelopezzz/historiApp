package gui.componentes.tarea;

import java.awt.Component;
import java.util.List;

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
	
	public ComponenteTipoTest(InfoTipoTest info) {
		super(info);
		this.indiceCorrecta = info.getCorrecta();
		this.opciones = List.copyOf(info.getOpciones());
		
		/* Construcción de etiquetas */
		super.construir(CABECERA);
		
		cabeceraOpciones = construirEtiquetaCabecera(CABECERA_OPCIONES);
		add(cabeceraOpciones);
		
		/* Construcción de las opciones */
		grupoOpciones = new ButtonGroup();
		opciones.stream()
			.map( op -> new JRadioButton(op))
			.forEach( button -> {
				
				/* Agrupación */
				grupoOpciones.add(button); // Nos aseguramos de que sólo se pueda elegir uno
				
				/* Estilo */
				button.setOpaque(false);
				button.setFont(GestorGUI.getInstancia().getFuenteGrande());
				button.setForeground(GestorGUI.getInstancia().getColorBlanco());	
				
				/* Montaje */
				button.setAlignmentX(Component.LEFT_ALIGNMENT);
				add(button);
			});
		
	}
	
	private int getRespuesta() {
		ButtonModel botonSeleccionado = grupoOpciones.getSelection();
		if(botonSeleccionado == null) {
			return SIN_RESPUESTA;
		} else {
			String respuesta = ((JRadioButton) botonSeleccionado.getGroup().getElements().nextElement()).getText();
			return opciones.indexOf(respuesta);
		}
	}
	
	@Override
	public boolean evaluar() {
		return getRespuesta() == indiceCorrecta;
	}

}
