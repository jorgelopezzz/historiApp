package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class EmergenteFichero extends Emergente {
	
	/* Componentes de organización */
	private JPanel panelPrincipal;
	private JPanel panelElector;
	protected JPanel panelInferior;
	
	/* Componentes para seleccionar imágenes */
	private JEditorPane panelArrastre;
	protected JFileChooser panelFichero;
	
	/* Botones */
	private JButton botonAceptar;
	private JButton botonSalir;
	
	/* Atributos a obtener */
	protected String rutaFichero;
	
	/* Dimensiones */
	private static final int ANCHO_FICHERO = 400;
	private static final int ALTO_FICHERO = 250;
	private static final int ANCHO_ARRASTRE = 200;
	private static final int ALTO_ARRASTRE = ALTO_FICHERO;
	
	public EmergenteFichero(JFrame ventanaMadre, String tipoFichero) {
		super("Seleccionar " + tipoFichero, GestorGUI.getInstancia().getColorBlanco(), ventanaMadre);
		
		construir();
	}
	
	public EmergenteFichero(JFrame ventanaMadre) {
		this(ventanaMadre, "Fichero");
		
		construir();
	}
	
	@Override
	protected void construir() {
		/* Configuración de panel principal */
		panelPrincipal = new JPanel();
		panelPrincipal = new JPanel();
		GestorGUI.configurarPanel(panelPrincipal, new BorderLayout(), false);
		
		/* Imagen no cargada */
		rutaFichero = null;
		
		/* Construccion de explorador y panel de arrastre */
		construirPanelElector();
		construirpanelInferior(GestorGUI.IMAGEN_PREDET_OSC);
		
		/* Montaje */
		panelPrincipal.add(panelElector, BorderLayout.CENTER);
		panelPrincipal.add(panelInferior, BorderLayout.SOUTH);
		
		this.add(panelPrincipal);
	}
	
	private void construirPanelElector() {
		/* Configuración panel elector */
		panelElector = new JPanel();
		GestorGUI.configurarPanel(panelElector, new BoxLayout(panelElector, BoxLayout.X_AXIS), false);
		
		/* Construcción */
		construirPanelFichero();
		construirPanelArrastre();
		
		/* Montaje */
		panelElector.add(Box.createHorizontalStrut((int)(ANCHO_EMERGENTE_PREDET/15.0)));
		panelElector.add(panelFichero);
		panelElector.add(panelArrastre);
		
	}
	
	public Optional<String> obtenerFichero() {
		return Optional.ofNullable(rutaFichero);
	}
	
	private void construirPanelFichero() {
		/* Seleccionador de ficheros */
		panelFichero = new JFileChooser();
		GestorGUI.fijarTamano(ANCHO_FICHERO, ALTO_FICHERO, panelFichero);
		
		/* Comportamiento del seleccionador */
		panelFichero.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accion = e.getActionCommand();
                if (accion.equals(JFileChooser.APPROVE_SELECTION)) {
                	manejadorPanelFichero();
                }
            }
        });
	}
	
	protected void manejadorPanelFichero() {
		rutaFichero = panelFichero.getSelectedFile().getAbsolutePath();
	}
	
	private void construirPanelArrastre() {
		/* Configuración del panel de arrastre */
		panelArrastre = new JEditorPane();
		panelArrastre.setContentType("text/html");
		panelArrastre.setEditable(false);
		panelArrastre.setBorder(null);
		panelArrastre.setFocusable(false);
		GestorGUI.fijarTamano(ANCHO_ARRASTRE, ALTO_ARRASTRE, panelArrastre);
		
		/* Contenido desplegado */
		panelArrastre.setText("<html>" 
		        + "<body style='background-color: rgb(20,108,148); color: rgb(250,245,242); "
		        + "font-family: Helvetica; display: flex; justify-content: center; align-items: center; text-align: center; height: 100%;'>" 
		        + "<div>"
		        + "<p style='font-size: 20px; font-weight: lighter;'>O</p>"
		        + "<p style='font-size: 20px; font-weight: lighter;'>arrastra</p>"
		        + "<p style='font-size: 20px; font-weight: lighter;'>aquí</p>"
		        + "</div>"
		        + "</body>" 
		        + "</html>");
				
		/* Comportamiento del panel */
		panelArrastre.setDropTarget(new DropTarget() {
			public synchronized void drop(DropTargetDropEvent evt) {
				try {
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					@SuppressWarnings("unchecked")
					List<File> ficheros = (List<File>) evt.getTransferable().
					getTransferData(DataFlavor.javaFileListFlavor);
					if (!ficheros.isEmpty()) {
						manejadorPanelArrastre(ficheros);
					}
				} catch (Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
	}
	
	protected void manejadorPanelArrastre(List<File> ficheros) {
		rutaFichero = ficheros.get(0).getAbsolutePath();
	}
	
	protected void construirpanelInferior(String ruta) {
		
		/* Configuración panel */
		panelInferior = new JPanel();
		GestorGUI.configurarPanel(panelInferior, new BoxLayout(panelInferior, BoxLayout.X_AXIS), false);
		panelInferior.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		/* Montaje */
		panelInferior.add(Box.createHorizontalStrut(ANCHO_EMERGENTE_PREDET/3-GestorGUI.SEPARACION_BOTONES));
		montarBotones();
        panelInferior.setBorder(BorderFactory.createEmptyBorder(0, 0, ALTO_EMERGENTE_PREDET/5, 0)); // Espacio abajo


		
	}
	
	protected void montarBotones() {
		/* Construcción */
		botonAceptar = GestorGUI.getBotonPredeterminado("Aceptar");
		manejadorAceptar();
		botonSalir = GestorGUI.getBotonPredeterminado("Salir");
		manejadorSalir(botonSalir);
		
		/* Montaje */
		panelInferior.add(Box.createHorizontalStrut(GestorGUI.SEPARACION_BOTONES));
		panelInferior.add(botonAceptar);
		panelInferior.add(Box.createHorizontalStrut(GestorGUI.SEPARACION_BOTONES));
		panelInferior.add(botonSalir);
		
	}
	
	private void manejadorAceptar() {
		super.manejadorSalir(botonAceptar);
	}
	
	@Override
	protected void manejadorSalir(JButton botonSalir) {
		botonSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rutaFichero = null;
				cerrar();
			}
		});
	}
	
}
