package gui.emergentes;

import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import gui.GestorGUI;
import gui.campos.CampoContrasena;
import gui.campos.CampoPredeterminado;
import gui.campos.CampoTexto;

public class EmergenteRegistro extends Emergente {
	
	/* Componentes de organización */
	private JPanel panelPrincipal;
	private JPanel panelBotones;
	private JPanel panelEnvolvente;
	private JPanel panelCampos;
	private JPanel panelImagen;
	
	/* Campos de datos */
	private CampoPredeterminado campoNombre;
	private CampoPredeterminado campoCorreo;
	private CampoPredeterminado campoSaludo;
	private CampoPredeterminado campoContrasena;
	private CampoPredeterminado campoRepetirContrasena;
	private JLabel etiquetaImagen;
	
	/* Botones */
	private JButton botonImagen;
	private JButton botonRegistrar;
	private JButton botonSalir;
	
	/* Dimensiones */
	private static final int ANCHO_PANEL = (int) (ANCHO_EMERGENTE * 4.0/5.0);
	private static final int ALTO_PANEL = (int) (ALTO_EMERGENTE * 2.0/3.0);
	private static final int MARGEN = 5;
	private static final int ANCHO_CAMPOS = 175;
	private static final int ALTO_CAMPOS = 25;
	private static final int ANCHO_ETIQUETAS = 150;
	private static final int LADO_IMAGEN = 100;
	
	public EmergenteRegistro(JFrame ventanaMadre) {
		super("Registro", GestorGUI.getInstancia().getColorClaro(), ventanaMadre);
		construir();
	}
	
	protected void construir() {
		/* Configuración del panel principal */
		panelPrincipal = new JPanel();
		GestorGUI.configurarPanel(panelPrincipal, false, new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
		GestorGUI.fijarTamano(ANCHO_PANEL, ALTO_PANEL, panelPrincipal);
		GestorGUI.centrarPanel(panelPrincipal, this);
		
		/* Construcción */
		construirPanelCampos();
		construirPanelBotones();
		
		/* Montaje */
		panelPrincipal.add(panelEnvolvente);
		panelPrincipal.add(panelBotones);		
		
		this.add(panelPrincipal);
		
	}
	
	private void construirPanelCampos() {
		
		/* Panel envolvente */
		panelEnvolvente = new JPanel();
		panelEnvolvente.setBackground(GestorGUI.getInstancia().getColorBlanco());
		panelEnvolvente.setBorder(new EmptyBorder(MARGEN, MARGEN, MARGEN, MARGEN));
		//GestorGUI.fijarTamano(ANCHO_PANEL, ALTO_PANEL, panelEnvolvente);
		
		/* Panel de campos */
		panelCampos = new JPanel();
		GestorGUI.configurarPanel(panelCampos, false, new BoxLayout(panelCampos, BoxLayout.Y_AXIS));
		panelEnvolvente.add(panelCampos);
		
		/* Borde con texto */
		TitledBorder bordeTexto = new TitledBorder(null, "Datos de registro", TitledBorder.LEADING, TitledBorder.TOP,
				GestorGUI.getInstancia().getFuenteTexto(), GestorGUI.getInstancia().getColorOscuro());
		bordeTexto.setBorder(BorderFactory.createLineBorder(GestorGUI.getInstancia().getColorOscuro()));
		panelCampos.setBorder(bordeTexto);
				
		/* Construcción de campos */
		campoNombre = new CampoTexto("Nombre:", ANCHO_CAMPOS, ALTO_CAMPOS, ANCHO_ETIQUETAS);
		campoCorreo = new CampoTexto("Correo:", ANCHO_CAMPOS, ALTO_CAMPOS, ANCHO_ETIQUETAS);
		campoSaludo = new CampoTexto("Saludo:", ANCHO_CAMPOS, ALTO_CAMPOS, ANCHO_ETIQUETAS);
		campoContrasena = new CampoContrasena("Contraseña:", ANCHO_CAMPOS, ALTO_CAMPOS, ANCHO_ETIQUETAS);
		campoRepetirContrasena = new CampoContrasena("Repetir contraseña:", ANCHO_CAMPOS, ALTO_CAMPOS, ANCHO_ETIQUETAS);
		
		/* Imagen */
		panelImagen = construirPanelImagen(GestorGUI.IMAGEN_PREDET_OSC);
		
		/* Montaje */
		panelCampos.add(Box.createVerticalStrut(MARGEN));
		panelCampos.add(campoNombre.getPanel());
		panelCampos.add(campoCorreo.getPanel());
		panelCampos.add(campoSaludo.getPanel());
		panelCampos.add(campoContrasena.getPanel());
		panelCampos.add(campoRepetirContrasena.getPanel());
		panelCampos.add(Box.createVerticalStrut(MARGEN));
		panelCampos.add(panelImagen);
		
	}
	

	
	private void construirPanelBotones() {
		/* Configuración del panel de botones */
		panelBotones = new JPanel();
		GestorGUI.configurarPanel(panelBotones, false, new BoxLayout(panelBotones, BoxLayout.X_AXIS));
		GestorGUI.fijarTamano(ANCHO_PANEL, GestorGUI.ALTO_BOTON + GestorGUI.SEPARACION_BOTONES, panelBotones);
		
		/* Creación de botones y establecimiento de manejadores */
		botonRegistrar = GestorGUI.getBotonPredeterminado("Registrarme");
		manejadorRegistrar();
		
		botonSalir = GestorGUI.getBotonPredeterminado("Salir");
		manejadorSalir(botonSalir);
		
		/* Montaje */
		panelBotones.add(Box.createHorizontalStrut((int)((ANCHO_PANEL-GestorGUI.SEPARACION_BOTONES)/2.0)-GestorGUI.ANCHO_BOTON));
		panelBotones.add(botonRegistrar);
		panelBotones.add(Box.createHorizontalStrut(GestorGUI.SEPARACION_BOTONES));
		panelBotones.add(botonSalir);
		
	}
	
	private void manejadorRegistrar() {
		
	}
	
}
