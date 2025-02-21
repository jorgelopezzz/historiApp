package gui;

import gui.estilo.Tema;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;

import gui.estilo.ArchivoTemas;

public class GestorGUI {
	
	/* Básicos */
	public static final String NOMBRE_APP = "HistoriApp";
	
	/* Fuentes y temas */
	public static final int TAM_TEXTO = 12;
	public static final int TAM_TITULO = 40;
	
	/* Dimensiones de componentes por defecto */
	public static final int ANCHO_BOTON_PREDET = 100;
	public static final int ALTO_BOTON_PREDET = 30; 
	public static final int SEPARACION_BOTONES = 20;
	
	/* Atributos de utilidad para la GUI */
	public static Tema temaActual;
	
	/* Patrón Singleton */
	private static GestorGUI instancia;
	
	/* Rutas de imágenes */
	public static final String IMAGEN_PREDET_OSC = "/fotoPredeterminada.png";
	
	private GestorGUI() {
		temaActual = ArchivoTemas.CIELO.getTema();
	}
	
	public static GestorGUI getInstancia() {
		if(instancia == null)
			instancia = new GestorGUI();
		return instancia;
	}
	
	public void setTema(Tema nuevoTema) {
		temaActual = nuevoTema;
	}
	
	public static void fijarTamano(int ancho, int alto, Component componente) {
		Dimension dimension = new Dimension(ancho, alto);
		componente.setMaximumSize(dimension);
		componente.setMinimumSize(dimension);
		componente.setPreferredSize(dimension);
	}
		
	public static void centrarPanel(JPanel panel, RootPaneContainer marco) {
		Container contenedor = marco.getContentPane();
		
		GridBagLayout cuadricula = new GridBagLayout();
		contenedor.setLayout(cuadricula);
		
		GridBagConstraints posicion = new GridBagConstraints();
		posicion.gridx = 2; 
		posicion.gridy = 2;
		contenedor.add(panel, posicion);
	}
	
	public static void configurarEtiqueta(JLabel etiqueta, boolean esOpaca, Color colorLetra, Font fuente) {
		etiqueta.setForeground(colorLetra);
		etiqueta.setOpaque(esOpaca);
		etiqueta.setFont(fuente);
	}
	
	public static void configurarPanel(JPanel panel, boolean esOpaco, LayoutManager layout) {
		panel.setOpaque(esOpaco);
		panel.setLayout(layout);
	}
	
	public static void configurarPanel(JPanel panel, boolean esOpaco, LayoutManager layout, Color colorFondo) {
		panel.setOpaque(esOpaco);
		panel.setLayout(layout);
		panel.setBackground(colorFondo);
	}
	
	public static void configurarPanel(JPanel panel, boolean esOpaco, Color colorFondo, LayoutManager layout, int ancho, int alto) {
		configurarPanel(panel, esOpaco, layout, colorFondo);
		fijarTamano(ancho, alto, panel);
	}
	
	public static void configurarBoton(JButton boton, Color colorFondo, Color colorLetra, Font fuente, int ancho, int alto) {
		boton.setFont(fuente);
		boton.setBackground(colorFondo);
		boton.setForeground(colorLetra);
		GestorGUI.fijarTamano(ancho, alto, boton);	
	}
	
	private ImageIcon escalarIcono(ImageIcon icono, int ancho, int alto) {
		return new ImageIcon(icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH));		
	}
	
	public ImageIcon iconoDeRecursos(String ruta, int ancho, int alto) {
		return escalarIcono(new ImageIcon(getClass().getResource(ruta)), ancho, alto);
	}
	
	
	public ImageIcon iconoAbsoluto(String ruta, int ancho, int alto) {
		return escalarIcono(new ImageIcon(ruta), ancho, alto);
	}
	
	public static JButton getBotonPredeterminado(String texto) {
		
		JButton boton = new JButton(texto);
		GestorGUI.configurarBoton(boton, GestorGUI.getInstancia().getColorOscuro(), 
				GestorGUI.getInstancia().getColorBlanco(), GestorGUI.getInstancia().getFuenteTexto(), 
				ANCHO_BOTON_PREDET, ALTO_BOTON_PREDET);
		
		boton.setFocusPainted(false); 
		boton.setBorderPainted(false);
		
		return boton;
	}
	
	public static JButton getBotonPredeterminado(String texto, int ancho) {
		JButton boton = getBotonPredeterminado(texto);
		GestorGUI.fijarTamano(ancho, ALTO_BOTON_PREDET, boton);
		return boton;
	}
	
	public Color getColorBlanco() {
		return temaActual.getColorBlanco();
	}
	
	public Color getColorClaro() {
		return temaActual.getColorClaro();
	}

	public Color getColorMedio() {
		return temaActual.getColorMedio();
	}

	public Color getColorOscuro() {
		return temaActual.getColorOscuro();
	}
	
	public Color getColorError() {
		return temaActual.getColorError();
	}

	public Font getFuenteTexto() {
		return temaActual.getFuenteTexto();
	}

	public Font getFuenteTitulo() {
		return temaActual.getFuenteTitulo();
	}
	
	
	
}
