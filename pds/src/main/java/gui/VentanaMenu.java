package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.HistoriApp;
import utils.GestorGUI;

@SuppressWarnings("serial")
public abstract class VentanaMenu extends Ventana {
	
	/* Componentes de organización */
	protected JPanel panelPrincipal;
	private JPanel barraSuperior;
	protected JPanel panelMenu;
	protected JPanel panelInicial;
	
	/* Componentes scrolleables */
	
	protected JComponent scroll; // Será Scroll en VentanaCursos y VentanaBloques y será JPanel en VentanaTareas
	
	/* Botones */
	private JButton botonClasificacion;
	private JButton botonEstadisticas;
	private JButton botonPerfil;
	private JButton botonCerrar;

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
		super(selector, GestorGUI.NOMBRE_APP + " Menu", GestorGUI.getInstancia().getColorClaro(), JFrame.DO_NOTHING_ON_CLOSE);
		gestionarCierre();
	}
	
	private void gestionarCierre() {
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                HistoriApp.INSTANCE.cerrarSesionUsuario();
            	dispose();
            }
        });
	}
	
	@Override
	protected void construir() {
		/* Configuración del panel principal */
		panelPrincipal = new JPanel();
		GestorGUI.configurarPanel(panelPrincipal, new BorderLayout(), false);
		GestorGUI.fijarTamano(ANCHO_VENTANA, ALTO_VENTANA, panelPrincipal);
		
		/* Construcción de subpaneles */
		construirBarraSuperior();
		construirInicio();
		construirScrollMenu();
		
		/* Montaje */
		panelPrincipal.add(barraSuperior, BorderLayout.NORTH);
		panelPrincipal.add(panelInicial, BorderLayout.CENTER);
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
		String puntuacion = String.valueOf(HistoriApp.INSTANCE.getPuntuacionUsuario());
		etiquetaPuntuacion = new JLabel(puntuacion, imagenPuntuacion, JLabel.LEFT);
		GestorGUI.configurarEtiqueta(etiquetaPuntuacion, false, GestorGUI.getInstancia().getColorClaro() , GestorGUI.getInstancia().getFuenteTexto());
		barraSuperior.add(etiquetaPuntuacion);
		
		///
		barraSuperior.add(Box.createHorizontalStrut(ESPACIO_HORIZONTAL_PEQUENO));
		///
				
		ImageIcon imagenRacha = GestorGUI.getInstancia().iconoDeRecursos(RUTA_RACHA, ANCHO_IMAGEN, ALTO_IMAGEN);
		String racha = String.valueOf(HistoriApp.INSTANCE.getMaxRachaUsuario());
		etiquetaRacha = new JLabel(racha, imagenRacha, JLabel.LEFT);
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
		
		botonCerrar = GestorGUI.getBotonPredeterminado("Cerrar sesión");
		GestorGUI.fijarTamano(GestorGUI.ANCHO_BOTON_PREDET+10, GestorGUI.ALTO_BOTON_PREDET, botonCerrar);
		barraSuperior.add(botonCerrar);
		manejadorCerrar();
		
		///
		barraSuperior.add(Box.createHorizontalStrut(ESPACIO_HORIZONTAL_PEQUENO));
		///
		
		String nombrePerfil = HistoriApp.INSTANCE.getNombreUsuario();
		
		String rutaPerfil = HistoriApp.INSTANCE.getImagenUsuario();
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
	
	protected abstract void construirInicio();
	
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
	
	private void manejadorCerrar() {
		botonCerrar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HistoriApp.INSTANCE.cerrarSesionUsuario();
				selector.cambiarVentana(new VentanaLogin(selector));
			}
		});
	}
	
	private void manejadorPerfil() {
		botonPerfil.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EmergentePerfil emergente = new EmergentePerfil(VentanaMenu.this);
				emergente.mostrar();
				
				if(emergente.hayCambioImagen()) {
					botonPerfil.setIcon(GestorGUI.getInstancia().iconoAbsoluto(HistoriApp.INSTANCE.getImagenUsuario(), GestorGUI.ANCHO_BOTON_IMAGEN, GestorGUI.ALTO_BOTON_IMAGEN));
					barraSuperior.revalidate();
					barraSuperior.repaint();
				}
				
			}
		});
	}
	
	
	
}
