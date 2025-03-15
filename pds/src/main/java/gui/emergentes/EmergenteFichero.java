package gui.emergentes;

import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import gui.GestorGUI;

@SuppressWarnings("serial")
public class EmergenteFichero extends Emergente {
	
	/* Componentes de organización */
	private JPanel panelPrincipal;
	private JPanel panelElector;
	private JPanel panelImagen;
	
	/* Componentes para seleccionar imágenes */
	private JEditorPane panelArrastre;
	private JFileChooser panelFichero;
	
	/* Vista previa */
	private JLabel etiquetaImagen;
	
	/* Botones */
	private JButton botonAceptar;
	private JButton botonSalir;
	
	/* Atributos a obtener */
	private String rutaFichero;
	
	/* Dimensiones */
	private static final int LADO_IMAGEN = 150;
	private static final int ANCHO_FICHERO = 400;
	private static final int ALTO_FICHERO = 250;
	private static final int ANCHO_ARRASTRE = 200;
	private static final int ALTO_ARRASTRE = ALTO_FICHERO;
	
	public EmergenteFichero(JFrame ventanaMadre) {
		super("Seleccionar Fichero", GestorGUI.getInstancia().getColorBlanco(), ventanaMadre);
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
		construirPanelImagen(GestorGUI.IMAGEN_PREDET_OSC);
		
		/* Montaje */
		panelPrincipal.add(panelElector, BorderLayout.CENTER);
		panelPrincipal.add(panelImagen, BorderLayout.SOUTH);
		
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
                	rutaFichero = panelFichero.getSelectedFile().getAbsolutePath();
                    establecerImagen(rutaFichero);
                }
            }
        });
	}
	
	private void establecerImagen(String ruta) {
		// Restaurar la etiqueta a su estado original
	    etiquetaImagen.setIcon(null);
	    etiquetaImagen.setText("");
	    etiquetaImagen.setOpaque(false);
	    etiquetaImagen.setBorder(null);
	    
		ImageIcon icono = GestorGUI.getInstancia().iconoAbsoluto(ruta, LADO_IMAGEN, LADO_IMAGEN);
	    if (icono != null && icono.getIconWidth() > 0) {
	        etiquetaImagen.setIcon(icono);
	    } else {
	    	etiquetaImagen.setIcon(null);
	    	etiquetaImagen.setText(new File(ruta).getName());
	        etiquetaImagen.setHorizontalAlignment(SwingConstants.CENTER);
	        etiquetaImagen.setVerticalAlignment(SwingConstants.CENTER);
	        etiquetaImagen.setForeground(GestorGUI.getInstancia().getColorBlanco());
	        etiquetaImagen.setOpaque(true);
	        etiquetaImagen.setBackground(GestorGUI.getInstancia().getColorOscuro());
	        etiquetaImagen.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLineBorder(GestorGUI.getInstancia().getColorOscuro(), 2),
	            BorderFactory.createEmptyBorder(10, 10, 10, 10)
	        ));
	    }
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
						rutaFichero = ficheros.get(0).getAbsolutePath();
						establecerImagen(rutaFichero);
					}
				} catch (Exception ex){
					ex.printStackTrace();
				}
			}
		});
		
	}
	
	private void construirPanelImagen(String ruta) {
		
		/* Configuración panel */
		panelImagen = new JPanel();
		GestorGUI.configurarPanel(panelImagen, new BoxLayout(panelImagen, BoxLayout.X_AXIS), false);
		
		/* Vista previa */
		etiquetaImagen = new JLabel();
		etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoDeRecursos(GestorGUI.IMAGEN_PREDET_OSC, LADO_IMAGEN, LADO_IMAGEN));
		etiquetaImagen.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		/* Creación de botones y establecimiento de manejadores */
		botonAceptar = GestorGUI.getBotonPredeterminado("Aceptar");
		manejadorAceptar();
		
		botonSalir = GestorGUI.getBotonPredeterminado("Salir");
		manejadorSalir(botonSalir);
		
		/* Montaje */
		panelImagen.add(Box.createHorizontalStrut( (int) ((ANCHO_EMERGENTE_PREDET - GestorGUI.ANCHO_BOTON_PREDET)/ 4.0) ));
		panelImagen.add(etiquetaImagen);
		panelImagen.add(Box.createHorizontalStrut(GestorGUI.SEPARACION_BOTONES));
		panelImagen.add(botonAceptar);
		panelImagen.add(Box.createHorizontalStrut(GestorGUI.SEPARACION_BOTONES));
		panelImagen.add(botonSalir);
		
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
