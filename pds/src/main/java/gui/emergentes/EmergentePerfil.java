package gui.emergentes;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dominio.HistoriApp;
import dominio.info.usuario.infoPerfilUsuario;
import gui.GestorGUI;
import gui.campos.CampoTexto;

@SuppressWarnings("serial")
public class EmergentePerfil extends Emergente {

    /* Componentes de organización */
    private JPanel panelPrincipal;
    private JPanel panelBotones;
    private JPanel panelEnvolvente;
    private JPanel panelCampos;
    private JPanel panelImagen;

    /* Subpaneles */
    private JPanel panelNombre;
    private JPanel panelCorreo;
    private JPanel panelSaludo;
    private JPanel panelNuevoSaludo;
    private JPanel panelRol;

    /* Datos de Usuario */
    private static final String RUTA_PERFIL_PREDETERMINADO = "/perfil.png";
    private infoPerfilUsuario datos;

    private JLabel etiquetaNombreEtiqueta;
    private JLabel etiquetaCorreoEtiqueta;
    private JLabel etiquetaSaludoEtiqueta;
    private JLabel etiquetaRolEtiqueta;

    private JLabel etiquetaNombre;
    private JLabel etiquetaCorreo;
    private JLabel etiquetaSaludo;
    private JLabel etiquetaRol;

    /* Nuevos datos de Usuario */

    private CampoTexto campoNuevoSaludo;

    private JLabel etiquetaImagen;

    /* Atributos */
    private String rutaImagen = null;
    private boolean hayCambios = false;

    /* Botones */
    private JButton botonSeleccionarImagen;
    private JButton botonBorrarImagen;
    private JButton botonConfirmar;
    private JButton botonCerrar;

    /* Dimensiones */
    private static final int ANCHO_PANEL = (int) (ANCHO_EMERGENTE_PREDET * 2.0/3.0);
	private static final int ALTO_PANEL = (int) (ALTO_EMERGENTE_PREDET * 2.0/3.0);
    private static final int MARGEN = 5;
    private static final int FACTOR_MARGEN_PANEL_ENVOLVENTE = 17;
    private static final int ESPACIADO_ENTRE_CAMPOS = 1;
    private static final int ESPACIADO_ENTRE_CAMPOS_FINAL = 21;
    private static final int ANCHO_CAMPOS = 175;
    private static final int ALTO_CAMPOS = 25;
    private static final int ANCHO_ETIQUETAS = 150;
    private static final int LADO_IMAGEN = 75;
    private static final int ANCHO_PANEL_IMAGEN = ANCHO_PANEL - 60;
    private static final int ALTO_PANEL_IMAGEN = 120;
    private static final int ANCHO_BOTON_GRANDE = 140;
    private static final int ANCHO_NUEVO_SALUDO = ANCHO_ETIQUETAS + ANCHO_CAMPOS + 10;
    private static final int ALTO_NUEVO_SALUDO = ALTO_CAMPOS + 7;

    public EmergentePerfil(JFrame ventanaMadre) {
        super("Perfil", GestorGUI.getInstancia().getColorClaro(), ventanaMadre);
        construir();
    }

    @Override
    protected void construir() {
        panelPrincipal = new JPanel();
        GestorGUI.configurarPanel(panelPrincipal, new BorderLayout(), false);
        GestorGUI.centrarPanel(panelPrincipal, this);

        construirPanelCampos();
        construirPanelBotones();

        panelPrincipal.add(panelEnvolvente, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        this.add(panelPrincipal);
    }

    private void construirPanelCampos() {
        panelEnvolvente = new JPanel();
        GestorGUI.configurarPanel(panelEnvolvente, new BorderLayout(), GestorGUI.getInstancia().getColorBlanco());
        panelEnvolvente.setBorder(new EmptyBorder(MARGEN, MARGEN, MARGEN, MARGEN));
        GestorGUI.fijarTamano(ANCHO_PANEL - MARGEN, ALTO_PANEL - FACTOR_MARGEN_PANEL_ENVOLVENTE*MARGEN, panelEnvolvente);

        panelCampos = new JPanel();
        GestorGUI.configurarPanel(panelCampos, new BoxLayout(panelCampos, BoxLayout.Y_AXIS), false);
        panelEnvolvente.add(panelCampos, BorderLayout.CENTER);

        panelCampos.setBorder(GestorGUI.bordeTexto("Datos de perfil", GestorGUI.getInstancia().getFuenteTexto(),
                GestorGUI.getInstancia().getColorOscuro(), GestorGUI.getInstancia().getColorOscuro()));

        datos = HistoriApp.INSTANCE.pedirDatosUsuario();
        
        // Nombre
        panelNombre = new JPanel();
        etiquetaNombreEtiqueta = new JLabel("Nombre:");
        etiquetaNombre = new JLabel(datos.getNombre());
        montarEtiquetas(panelNombre, etiquetaNombreEtiqueta, etiquetaNombre);

        // Correo
        panelCorreo = new JPanel();
        etiquetaCorreoEtiqueta = new JLabel("Correo:");
        etiquetaCorreo = new JLabel(datos.getCorreo());
        montarEtiquetas(panelCorreo, etiquetaCorreoEtiqueta, etiquetaCorreo);

        // Saludo
        panelSaludo = new JPanel();
        etiquetaSaludoEtiqueta = new JLabel("Saludo:");
        etiquetaSaludo = new JLabel(datos.getSaludo());
        montarEtiquetas(panelSaludo, etiquetaSaludoEtiqueta, etiquetaSaludo);

        // Nuevo saludo
        campoNuevoSaludo = new CampoTexto("Nuevo saludo:", ANCHO_CAMPOS, ALTO_CAMPOS, ANCHO_ETIQUETAS);
        panelNuevoSaludo = new JPanel();
        GestorGUI.configurarPanel(panelNuevoSaludo, new BoxLayout(panelNuevoSaludo, BoxLayout.X_AXIS), false);
        GestorGUI.fijarTamano(ANCHO_NUEVO_SALUDO, ALTO_NUEVO_SALUDO, panelNuevoSaludo);
        panelNuevoSaludo.add(Box.createHorizontalGlue());
        panelNuevoSaludo.add(campoNuevoSaludo.getPanel());
        panelNuevoSaludo.add(Box.createHorizontalGlue());

        // Rol
        panelRol = new JPanel();
        etiquetaRolEtiqueta = new JLabel("Rol:");
        etiquetaRol = new JLabel(datos.getRol());
        montarEtiquetas(panelRol, etiquetaRolEtiqueta, etiquetaRol);

        // Imagen
        construirPanelImagen(HistoriApp.INSTANCE.getImagenUsuario());

        panelCampos.add(Box.createVerticalStrut(MARGEN));
        panelCampos.add(panelNombre);
        panelCampos.add(Box.createVerticalStrut(ESPACIADO_ENTRE_CAMPOS));
        panelCampos.add(panelCorreo);
        panelCampos.add(Box.createVerticalStrut(ESPACIADO_ENTRE_CAMPOS));
        panelCampos.add(panelSaludo);
        panelCampos.add(Box.createVerticalStrut(ESPACIADO_ENTRE_CAMPOS));
        panelCampos.add(panelNuevoSaludo);
        panelCampos.add(Box.createVerticalStrut(ESPACIADO_ENTRE_CAMPOS_FINAL));
        panelCampos.add(panelRol);
        panelCampos.add(Box.createVerticalStrut(MARGEN));

        panelEnvolvente.add(panelImagen, BorderLayout.SOUTH);
    }

    private void montarEtiquetas(JPanel panel, JLabel etiquetaEtiqueta, JLabel etiqueta){
        GestorGUI.configurarPanel(panel, new BoxLayout(panel, BoxLayout.X_AXIS), false);
        
        GestorGUI.configurarEtiqueta(etiquetaEtiqueta, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteTexto()));
        GestorGUI.fijarTamano(ANCHO_ETIQUETAS, ALTO_CAMPOS, etiquetaEtiqueta);
    
        GestorGUI.configurarEtiqueta(etiqueta, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.getInstancia().getFuenteTexto());
        GestorGUI.fijarTamano(ANCHO_CAMPOS, ALTO_CAMPOS, etiqueta);

        panel.add(etiquetaEtiqueta);
        panel.add(etiqueta);
    }

    private void construirPanelImagen(String ruta) {
        panelImagen = new JPanel();
        GestorGUI.configurarPanel(panelImagen, new BoxLayout(panelImagen, BoxLayout.X_AXIS), false);
        GestorGUI.fijarTamano(ANCHO_PANEL_IMAGEN, ALTO_PANEL_IMAGEN, panelImagen);

        etiquetaImagen = new JLabel();
        etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoDeRecursos(HistoriApp.INSTANCE.getImagenUsuario(), LADO_IMAGEN, LADO_IMAGEN));
        etiquetaImagen.setAlignmentX(Component.LEFT_ALIGNMENT);

        botonSeleccionarImagen = GestorGUI.getBotonPredeterminado("Seleccionar imagen", ANCHO_BOTON_GRANDE);
        manejadorSeleccionarImagen();

        botonBorrarImagen = GestorGUI.getBotonPredeterminado("Borrar imagen", ANCHO_BOTON_GRANDE);
        manejadorBorrarImagen();

        panelImagen.add(Box.createHorizontalStrut((int) (ANCHO_PANEL / 2.0) - LADO_IMAGEN - ANCHO_BOTON_GRANDE));
        panelImagen.add(etiquetaImagen);
        panelImagen.add(Box.createHorizontalStrut(GestorGUI.SEPARACION_BOTONES));
        panelImagen.add(botonSeleccionarImagen);
        panelImagen.add(Box.createHorizontalStrut(GestorGUI.SEPARACION_BOTONES));
        panelImagen.add(botonBorrarImagen);
    }
    

    private void construirPanelBotones() {
        panelBotones = new JPanel();
        GestorGUI.configurarPanel(panelBotones, new BoxLayout(panelBotones, BoxLayout.X_AXIS), false);
        GestorGUI.fijarTamano(ANCHO_PANEL, GestorGUI.ALTO_BOTON_PREDET + GestorGUI.SEPARACION_BOTONES, panelBotones);

        botonConfirmar = GestorGUI.getBotonPredeterminado("Confirmar");
        manejadorConfirmar();

        botonCerrar = GestorGUI.getBotonPredeterminado("Cerrar");
        manejadorSalir(botonCerrar);

        panelBotones.add(Box.createHorizontalStrut((int) ((ANCHO_PANEL - (2 * GestorGUI.ANCHO_BOTON_PREDET + GestorGUI.SEPARACION_BOTONES)) / 2.0)));
        panelBotones.add(botonConfirmar);
        panelBotones.add(Box.createHorizontalStrut(GestorGUI.SEPARACION_BOTONES));
        panelBotones.add(botonCerrar);
    }

    private void establecerImagenPredeterminada() {
        rutaImagen = RUTA_PERFIL_PREDETERMINADO;
        etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoDeRecursos(GestorGUI.IMAGEN_PREDET_OSC, LADO_IMAGEN, LADO_IMAGEN));
        etiquetaImagen.validate();
        etiquetaImagen.repaint();
    }

    private void manejadorSeleccionarImagen() {
        botonSeleccionarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmergenteImagen emergente = new EmergenteImagen(ventanaMadre);
                emergente.mostrar();

                emergente.obtenerFichero().ifPresentOrElse((ruta) -> {
                    rutaImagen = ruta;
                    etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoAbsoluto(rutaImagen, LADO_IMAGEN, LADO_IMAGEN));
                }, () -> {});
            }
        });
    }

    private void manejadorBorrarImagen() {
        botonBorrarImagen.addActionListener(e -> establecerImagenPredeterminada());
    }

    private void manejadorConfirmar() {
        botonConfirmar.addActionListener(e -> {
        	if(rutaImagen != null) {
        		HistoriApp.INSTANCE.cambiarImagen(rutaImagen);
        		hayCambios = true;
        	}
        	if(campoNuevoSaludo.campoValido()) {
        		HistoriApp.INSTANCE.cambiarSaludo(campoNuevoSaludo.getTexto());
        		hayCambios = true;
        	}
            if(hayCambios) {
            	EmergenteMensaje emergente = new EmergenteMensaje(ventanaMadre, "Cambios guardados correctamente.");
                emergente.mostrar();
            }
            cerrar();
        });
    }
    
    public boolean hayCambioImagen() {
    	return hayCambios && rutaImagen != null;
    }
}