package gui.ventanas;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.GestorGUI;
import gui.componentes.Componente;
import gui.componentes.ComponenteCurso;
import gui.emergentes.EmergenteClasificacion;
import gui.emergentes.EmergenteEstadisticas;
import gui.emergentes.EmergentePerfil;
import gui.emergentes.EmergenteRegistro;
import gui.info.InfoCurso;
import gui.scroll.Scroll;

@SuppressWarnings("serial")
public abstract class VentanaMenu extends Ventana {
	
	/* Componentes de organización */
	private JPanel panelPrincipal;
	private JPanel barraSuperior;
	protected JPanel panelMenu;
	protected JPanel panelBienvenida;
	
	/* Componentes scrolleables */
	
	protected Scroll scroll;
	
	/* Botones */
	private JButton botonClasificacion;
	private JButton botonEstadisticas;
	private JButton botonPerfil;

	/* Etiquetas */
	private JLabel etiquetaApp;
	private JLabel etiquetaPuntuacion;
	private JLabel etiquetaRacha;
	private JLabel etiquetaPerfil;
	
	/* Dimensiones */
	protected static final int ALTO_BARRA = (int) (ALTO_VENTANA / 10);
	private static final int ANCHO_IMAGEN = 30;
	private static final int ALTO_IMAGEN = 30;
	protected static final int ESPACIO_HORIZONTAL_PEQUENO = 15;
	protected static final int ESPACIO_HORIZONTAL_GRANDE = ESPACIO_HORIZONTAL_PEQUENO * 7;
	
	/* Rutas */
	private static final String RUTA_PUNTUACION = "/puntuacion.png";
	private static final String RUTA_RACHA = "/racha.png";
	private static final String RUTA_PERFIL_PREDETERMINADO = "/perfil.png";
	
	public VentanaMenu(SelectorVentana selector) {
		super(selector, GestorGUI.NOMBRE_APP + " Menu", GestorGUI.getInstancia().getColorClaro());
	}
	
	@Override
	protected void construir() {
		/* Configuración del panel principal */
		panelPrincipal = new JPanel();
		GestorGUI.configurarPanel(panelPrincipal, new BorderLayout(), false);
		GestorGUI.fijarTamano(ANCHO_VENTANA, ALTO_VENTANA, panelPrincipal);
		
		/* Construcción de subpaneles */
		construirBarraSuperior();
		construirBienvenida();
		construirScrollMenu();
		
		/* Montaje */
		panelPrincipal.add(barraSuperior, BorderLayout.NORTH);
		panelPrincipal.add(panelBienvenida, BorderLayout.CENTER);
		panelPrincipal.add(scroll, BorderLayout.SOUTH);
		
		this.add(panelPrincipal);
	}
	
	private void construirBarraSuperior() {
		barraSuperior = new JPanel();
		GestorGUI.configurarPanel(barraSuperior,  new BoxLayout(barraSuperior, BoxLayout.X_AXIS),
				GestorGUI.getInstancia().getColorOscuro(), ANCHO_VENTANA, ALTO_BARRA);
		
		///
		barraSuperior.add(Box.createHorizontalStrut(ESPACIO_HORIZONTAL_PEQUENO));
		///
		
		etiquetaApp = new JLabel(GestorGUI.NOMBRE_APP);
		GestorGUI.configurarEtiqueta(etiquetaApp, false, GestorGUI.getInstancia().getColorClaro() , GestorGUI.getInstancia().getFuenteTexto());
		barraSuperior.add(etiquetaApp);
		
		///
		barraSuperior.add(Box.createHorizontalStrut(ESPACIO_HORIZONTAL_PEQUENO));
		///
		
		ImageIcon imagenPuntuacion = GestorGUI.getInstancia().iconoDeRecursos(RUTA_PUNTUACION, ANCHO_IMAGEN, ALTO_IMAGEN);
		etiquetaPuntuacion = new JLabel("Puntuación", imagenPuntuacion, JLabel.LEFT);
		GestorGUI.configurarEtiqueta(etiquetaPuntuacion, false, GestorGUI.getInstancia().getColorClaro() , GestorGUI.getInstancia().getFuenteTexto());
		barraSuperior.add(etiquetaPuntuacion);
		
		///
		barraSuperior.add(Box.createHorizontalStrut(ESPACIO_HORIZONTAL_PEQUENO));
		///
				
		ImageIcon imagenRacha = GestorGUI.getInstancia().iconoDeRecursos(RUTA_RACHA, ANCHO_IMAGEN, ALTO_IMAGEN);
		etiquetaRacha = new JLabel("Racha", imagenRacha, JLabel.LEFT);
		GestorGUI.configurarEtiqueta(etiquetaRacha, false, GestorGUI.getInstancia().getColorClaro() , GestorGUI.getInstancia().getFuenteTexto());
		barraSuperior.add(etiquetaRacha);
		
		///
		barraSuperior.add(Box.createHorizontalStrut(ESPACIO_HORIZONTAL_GRANDE));
		///
				
		botonClasificacion = GestorGUI.getBotonPredeterminado("Clasificación");
		barraSuperior.add(botonClasificacion);
		manejadorClasificacion();
		
		botonEstadisticas = GestorGUI.getBotonPredeterminado("Estadísticas");
		barraSuperior.add(botonEstadisticas);
		manejadorEstadisticas();
		
		///
		barraSuperior.add(Box.createHorizontalStrut(ESPACIO_HORIZONTAL_GRANDE));
		///
		
		String nombrePerfil = "el_rey67";
		String rutaPerfil = RUTA_PERFIL_PREDETERMINADO;
		etiquetaPerfil = new JLabel(nombrePerfil);
		GestorGUI.configurarEtiqueta(etiquetaPerfil, false, GestorGUI.getInstancia().getColorClaro() , GestorGUI.getInstancia().getFuenteTexto());
		barraSuperior.add(etiquetaPerfil);
		
		///
		barraSuperior.add(Box.createHorizontalStrut(ESPACIO_HORIZONTAL_PEQUENO));
		///
				
		botonPerfil = GestorGUI.getBotonPredeterminadoImagen(rutaPerfil);
		barraSuperior.add(botonPerfil);
		manejadorPerfil();
	}
	
	protected abstract void construirBienvenida();
	
	protected abstract void construirScrollMenu();
	
	private void manejadorClasificacion() {
		botonClasificacion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EmergenteClasificacion emergente = new EmergenteClasificacion(VentanaMenu.this);
				emergente.mostrar();
			}
		});
	}
	
	private void manejadorEstadisticas() {
		botonEstadisticas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EmergenteEstadisticas emergente = new EmergenteEstadisticas(VentanaMenu.this);
				emergente.mostrar();
			}
		});
	}
	
	private void manejadorPerfil() {
		botonPerfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EmergentePerfil emergente = new EmergentePerfil(VentanaMenu.this);
				emergente.mostrar();
			}
		});
	}
	
	
}
