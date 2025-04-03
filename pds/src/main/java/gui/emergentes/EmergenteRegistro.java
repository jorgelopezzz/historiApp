package gui.emergentes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.GestorGUI;
import gui.campos.CampoContrasena;
import gui.campos.CampoDesplegable;
import gui.campos.CampoPredeterminado;
import gui.campos.CampoTexto;

@SuppressWarnings("serial")
public class EmergenteRegistro extends Emergente {
	
	/* Componentes de organización */
	private JPanel panelPrincipal;
	private JPanel panelBotones;
	private JPanel panelEnvolvente;
	private JPanel panelCampos;
	private JPanel panelImagen;
	
	/* Campos de datos */
	private List<CampoPredeterminado> campos;
	private CampoPredeterminado campoNombre;
	private CampoPredeterminado campoCorreo;
	private CampoPredeterminado campoSaludo;
	private CampoPredeterminado campoContrasena;
	private CampoPredeterminado campoRepetirContrasena;
	private CampoPredeterminado campoRol;
	
	private JLabel etiquetaImagen;
	
	/* Botones */
	private JButton botonImagen;
	private JButton botonBorrar;
	private JButton botonRegistrar;
	private JButton botonSalir;
	
	/* Atributos a recabar */
	private String rutaImagen;

	/* Dimensiones */
	private static final int ANCHO_PANEL = (int) (ANCHO_EMERGENTE_PREDET * 2.0/3.0);
	private static final int ALTO_PANEL = (int) (ALTO_EMERGENTE_PREDET * 2.0/3.0);
	private static final int MARGEN = 5;
	private static final int ANCHO_CAMPOS = 175;
	private static final int ALTO_CAMPOS = 25;
	private static final int ANCHO_ETIQUETAS = 150;
	private static final int LADO_IMAGEN = 75;
	private static final int ANCHO_BOTON_GRANDE = 140;
	
	public EmergenteRegistro(JFrame ventanaMadre) {
		super("Registro", GestorGUI.getInstancia().getColorClaro(), ventanaMadre);
		
		construir();
	}
	
	@Override
	protected void construir() {
		/* Configuración del panel principal */
		panelPrincipal = new JPanel();
		GestorGUI.configurarPanel(panelPrincipal, new BorderLayout(), false);
		GestorGUI.centrarPanel(panelPrincipal, this);
		
		/* Construcción */
		construirPanelCampos();
		construirPanelBotones();
		
		/* Montaje */
		panelPrincipal.add(panelEnvolvente, BorderLayout.CENTER);
		panelPrincipal.add(panelBotones, BorderLayout.SOUTH);		
		
		this.add(panelPrincipal);
		
	}
	
	private void construirPanelCampos() {
		
		/* Panel envolvente */
		panelEnvolvente = new JPanel();
		GestorGUI.configurarPanel(panelEnvolvente, new BorderLayout(), GestorGUI.getInstancia().getColorBlanco());
		panelEnvolvente.setBorder(new EmptyBorder(MARGEN, MARGEN, MARGEN, MARGEN));
		GestorGUI.fijarTamano(ANCHO_PANEL-MARGEN, ALTO_PANEL-MARGEN, panelEnvolvente);
		
		/* Panel de campos */
		panelCampos = new JPanel();
		GestorGUI.configurarPanel(panelCampos, new BoxLayout(panelCampos, BoxLayout.Y_AXIS), false);
		panelEnvolvente.add(panelCampos, BorderLayout.CENTER);
		
		/* Borde con texto */
		panelCampos.setBorder(GestorGUI.bordeTexto("Datos de registro", GestorGUI.getInstancia().getFuenteTexto(),
				GestorGUI.getInstancia().getColorOscuro(), GestorGUI.getInstancia().getColorOscuro()));
				
		/* Construcción de campos */
		campoNombre = new CampoTexto("Nombre:", ANCHO_CAMPOS, ALTO_CAMPOS, ANCHO_ETIQUETAS);
		campoCorreo = new CampoTexto("Correo:", ANCHO_CAMPOS, ALTO_CAMPOS, ANCHO_ETIQUETAS);
		campoSaludo = new CampoTexto("Saludo:", ANCHO_CAMPOS, ALTO_CAMPOS, ANCHO_ETIQUETAS);
		campoContrasena = new CampoContrasena("Contraseña:", ANCHO_CAMPOS, ALTO_CAMPOS, ANCHO_ETIQUETAS);
		campoRepetirContrasena = new CampoContrasena("Repetir contraseña:", ANCHO_CAMPOS, ALTO_CAMPOS, ANCHO_ETIQUETAS);
		
		/* Campo roles */
		campoRol = new CampoDesplegable("Rol:", ANCHO_CAMPOS, ALTO_CAMPOS, ANCHO_ETIQUETAS);
		((CampoDesplegable)campoRol).addElementos("Alumno", "Profesor");
		
		/* Agrupación de campos */
		campos = List.of(campoNombre, campoCorreo, campoSaludo, campoContrasena, campoRepetirContrasena, campoRol);
		
		
		/* Imagen */
		construirPanelImagen(GestorGUI.IMAGEN_PREDET_OSC);
		
		/* Montaje */
		panelCampos.add(Box.createVerticalStrut(MARGEN));
		for(CampoPredeterminado campo : campos) {
			panelCampos.add(campo.getPanel());
		}
		panelCampos.add(Box.createVerticalStrut(MARGEN));
		
		panelEnvolvente.add(panelImagen, BorderLayout.SOUTH);
		
	}
	
	private void construirPanelImagen(String ruta) {
		/* Configuración panel */
		panelImagen = new JPanel();
		GestorGUI.configurarPanel(panelImagen, new BoxLayout(panelImagen, BoxLayout.X_AXIS), false);
		GestorGUI.fijarTamano(ANCHO_PANEL-60, 120, panelImagen);
		
		/* Vista previa */
		etiquetaImagen = new JLabel();
		establecerImagenPredeterminada();
		etiquetaImagen.setAlignmentX(Component.LEFT_ALIGNMENT);
		
		/* Creación de botones y establecimiento de manejadores */
		botonImagen = GestorGUI.getBotonPredeterminado("Seleccionar imagen", ANCHO_BOTON_GRANDE);
		manejadorSeleccionarImagen();
		
		botonBorrar = GestorGUI.getBotonPredeterminado("Borrar imagen", ANCHO_BOTON_GRANDE);
		manejadorBorrarImagen();
		
		/* Montaje */
		panelImagen.add(Box.createHorizontalStrut((int) (ANCHO_PANEL/2.0) - LADO_IMAGEN - ANCHO_BOTON_GRANDE));
		panelImagen.add(etiquetaImagen);
		panelImagen.add(Box.createHorizontalStrut(GestorGUI.SEPARACION_BOTONES));
		panelImagen.add(botonImagen);
		panelImagen.add(Box.createHorizontalStrut(GestorGUI.SEPARACION_BOTONES));
		panelImagen.add(botonBorrar);
		
	}

	private void establecerImagenPredeterminada(){
		rutaImagen = null;
		etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoDeRecursos(GestorGUI.IMAGEN_PREDET_OSC, LADO_IMAGEN, LADO_IMAGEN));
	}
	
	private void manejadorSeleccionarImagen() {
		botonImagen.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {		
				/* Lanzar la ventana emergente */
				EmergenteImagen emergente = new EmergenteImagen(ventanaMadre);
				emergente.mostrar();

				/* Obtener resultado */
				emergente.obtenerFichero().ifPresentOrElse( (ruta) -> {
					rutaImagen = ruta;
					etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoAbsoluto(rutaImagen, LADO_IMAGEN, LADO_IMAGEN));
				},
						() ->  {return;} 
				);
			}
		});
	}
	
	private void manejadorBorrarImagen() {
		botonBorrar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {		
				establecerImagenPredeterminada();
			}
		});
	}
	
	private void construirPanelBotones() {
		/* Configuración del panel de botones */
		panelBotones = new JPanel();
		GestorGUI.configurarPanel(panelBotones, new BoxLayout(panelBotones, BoxLayout.X_AXIS), false);
		GestorGUI.fijarTamano(ANCHO_PANEL, GestorGUI.ALTO_BOTON_PREDET + GestorGUI.SEPARACION_BOTONES, panelBotones);
		
		/* Creación de botones y establecimiento de manejadores */
		botonRegistrar = GestorGUI.getBotonPredeterminado("Registrarme");
		manejadorRegistrar();
		
		botonSalir = GestorGUI.getBotonPredeterminado("Salir");
		manejadorSalir(botonSalir);
		
		/* Montaje */
		panelBotones.add(Box.createHorizontalStrut((int)((ANCHO_PANEL-GestorGUI.SEPARACION_BOTONES)/2.0)-GestorGUI.ANCHO_BOTON_PREDET));
		panelBotones.add(botonRegistrar);
		panelBotones.add(Box.createHorizontalStrut(GestorGUI.SEPARACION_BOTONES));
		panelBotones.add(botonSalir);
		
	}
	
	private void manejadorRegistrar() {
		botonRegistrar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {	
				boolean camposValidos = true;
				
				/* Comprobamos que todos los campos obligatorios tengan texto */
				for( CampoPredeterminado campo : campos ) {
					camposValidos = camposValidos & campo.comprobarCampo();
				}
					
				/* Caso 1: Hay campos obligatorios no rellenados */
				if(!camposValidos) {
					EmergenteMensaje emergente = new EmergenteMensaje(ventanaMadre, "Debe rellenar todos los campos en rojo.");
					emergente.mostrar();
					return;
					
				/* Caso 2: Las contraseñas no coinciden */
				} else if( ! campoContrasena.getTexto().equals(campoRepetirContrasena.getTexto())) {
					EmergenteMensaje emergente = new EmergenteMensaje(ventanaMadre, "Las contraseñas no coinciden.");
					emergente.mostrar();
					return;
				}
				/* Controlador */

			}
		});
	}
	
		
	
}
