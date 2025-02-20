package gui;

import gui.estilo.Tema;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.Window;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.RootPaneContainer;

import gui.estilo.ArchivoTemas;

public class GestorGUI {
	
	/* Básicos */
	public static final String NOMBRE_APP = "HistoriApp";
	
	/* Fuentes y temas */
	public static final int TAM_TEXTO = 13;
	public static final int TAM_TITULO = 20;
	
	/* Atributos de utilidad para la GUI */
	public static Tema temaActual;
	
	/* Patrón Singleton */
	private static GestorGUI instancia;
	
	private GestorGUI() {
		temaActual = ArchivoTemas.GRANATE.getTema();
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
	
	public static void configurarPanel(JPanel panel, Color colorFondo, LayoutManager layout) {
		panel.setBackground(colorFondo);
		panel.setLayout(layout);
	}
	
	public static void configurarPanel(JPanel panel, Color colorFondo, LayoutManager layout, int ancho, int alto) {
		configurarPanel(panel, colorFondo, layout);
		fijarTamano(ancho, alto, panel);
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

	public Font getFuenteTexto() {
		return temaActual.getFuenteTexto();
	}

	public Font getFuenteTitulo() {
		return temaActual.getFuenteTitulo();
	}
	
	
	
}
