package gui.ventanas;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import gui.GestorGUI;
import gui.campos.CampoContrasena;
import gui.campos.CampoPredeterminado;
import gui.campos.CampoTexto;

public class VentanaLogin extends Ventana {

	/* Componentes de organización */
	private JPanel panelPrincipal; // Agrupa central y botones
	private JPanel panelCentral;
	private JPanel panelBotones;
	
	/* Componentes de datos */
	private CampoPredeterminado campoNombre;
	private CampoPredeterminado campoContrasena;
	
	/* Botones */
	private JButton botonAcceder;
	private JButton botonRegistrar;
	private JButton botonSalir;
	
	/* Etiquetas */
	private JLabel etiquetaTitulo;
	private JLabel etiquetaNombre;
	private JLabel etiquetaContrasena;
	
	/* Atributos a recabar */
	private String nombre;
	private String contrasena;
	
	/* Dimensiones */
	private static final int ANCHO_PANEL = (int) ANCHO_VENTANA / 2;
	private static final int ALTO_PANEL = (int) ALTO_VENTANA / 2;
	private static final double FRAC_PANEL_CENTRAL = 4.0/5.0;
	private static final double FRAC_ANCHO_CAMPOS = 0.5;
	private static final double FRAC_ALTO_CAMPOS = 0.1; 
	
	public VentanaLogin(SelectorVentana selector) {
		super(selector, GestorGUI.NOMBRE_APP + " Login", GestorGUI.getInstancia().getColorClaro());
		construir();
	}
	
	protected void construir() {
		/* Configuración del panel principal */
		panelPrincipal = new JPanel();
		GestorGUI.configurarPanel(panelPrincipal, false , new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		GestorGUI.fijarTamano(ANCHO_PANEL, ALTO_PANEL, panelPrincipal);
		GestorGUI.centrarPanel(panelPrincipal, this);
		
		/* Construcción de subpaneles */
		construirPanelCentral();
		construirPanelBotones();
		
		/* Montaje */
		panelPrincipal.add(panelCentral);
		panelPrincipal.add(panelBotones);
		
		this.add(panelPrincipal);
		
	}
	
	private void construirPanelCentral() {
		/* Configuración del panel central */
		panelCentral = new JPanel();
		GestorGUI.configurarPanel(panelCentral, true, GestorGUI.getInstancia().getColorBlanco(), 
				new BoxLayout(panelCentral, BoxLayout.Y_AXIS), ANCHO_PANEL, (int) (ALTO_PANEL *FRAC_PANEL_CENTRAL));
		
		/* Etiqueta título */
		etiquetaTitulo = new JLabel(GestorGUI.NOMBRE_APP);
		GestorGUI.configurarEtiqueta(etiquetaTitulo, 
				false, GestorGUI.getInstancia().getColorOscuro(), GestorGUI.getInstancia().getFuenteTitulo());
		etiquetaTitulo.setAlignmentX(CENTER_ALIGNMENT);
		
		/* Campo nombre */
		campoNombre = new CampoTexto("Usuario:     ", 
				(int) (FRAC_ANCHO_CAMPOS*ANCHO_PANEL), (int) (FRAC_ALTO_CAMPOS*ALTO_PANEL));
		campoNombre.getPanel().setAlignmentX(Component.CENTER_ALIGNMENT);

		/* Campo contraseña */
		campoContrasena = new CampoContrasena("Contraseña:", (int) (FRAC_ANCHO_CAMPOS*ANCHO_PANEL), (int) (FRAC_ALTO_CAMPOS*ALTO_PANEL));
		campoContrasena.getPanel().setAlignmentX(Component.CENTER_ALIGNMENT);
		
		/* Montaje */
		panelCentral.add(Box.createVerticalStrut((int) ALTO_VENTANA / 15)); // Para evitar que el título esté pegado
		panelCentral.add(etiquetaTitulo);
		
		panelCentral.add(Box.createVerticalStrut((int) ALTO_VENTANA / 20));
		panelCentral.add(campoNombre.getPanel());
		
		//panelCentral.add(Box.createVerticalStrut((int) ALTO_VENTANA / 20));
		panelCentral.add(campoContrasena.getPanel());
		
		
	}
	
	private void construirPanelBotones() {
		/* Configuración del panel de botones */
		panelBotones = new JPanel();
		GestorGUI.configurarPanel(panelBotones, false, new BoxLayout(panelBotones, BoxLayout.X_AXIS));
		GestorGUI.fijarTamano(ANCHO_PANEL, (int) (ALTO_PANEL*(1-FRAC_PANEL_CENTRAL)), panelBotones);

		/* Creación de botones y establecimiento de manejadores */
		botonAcceder = GestorGUI.getBotonPredeterminado("Acceder");
		manejadorAcceder();
		
		botonRegistrar = GestorGUI.getBotonPredeterminado("Registrarme");
		manejadorRegistrar();
		
		botonSalir = GestorGUI.getBotonPredeterminado("Salir");
		manejadorSalir();
		
		/* Montaje */
		panelBotones.add(Box.createHorizontalStrut((int) (ANCHO_PANEL/12)));
		panelBotones.add(botonAcceder);
		panelBotones.add(Box.createHorizontalStrut(GestorGUI.SEPARACION_BOTONES));
		panelBotones.add(botonRegistrar);
		panelBotones.add(Box.createHorizontalStrut(GestorGUI.SEPARACION_BOTONES));
		panelBotones.add(botonSalir);
		
	}
	
	private void manejadorAcceder() {
		botonAcceder.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* Comprobación de campos */
				if( (!campoNombre.comprobarCampo()) | (!campoContrasena.comprobarCampo())) { // Sin cortocircuito para que muestre todo campo vacío en rojo
					return;
				} 
				
				/* Recogida de datos */
				nombre = campoNombre.getTexto();
				contrasena = campoContrasena.getTexto();
				
				/* Controlador */
				
			}
		});
	}
	
	private void manejadorSalir() {
		botonSalir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrar();
				System.exit(0);
			}
		});
	}
	
	private void manejadorRegistrar() {
		botonRegistrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//selector.cambiarVentana(new VentanaRegistro(selector));
			}
		});
	}
	
}
