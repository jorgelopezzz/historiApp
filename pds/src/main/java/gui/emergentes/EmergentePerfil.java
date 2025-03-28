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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

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

    /* Campos de datos */ 
    private JLabel etiquetaNombre;
    private JLabel etiquetaCorreo;
    private JLabel etiquetaSaludo;
    private CampoTexto campoNuevoSaludo;
    private JLabel etiquetaRol;

    private JLabel etiquetaImagen;

    /* Botones */
    private JButton botonSeleccionarImagen;
    private JButton botonBorrarImagen;
    private JButton botonConfirmar;
    private JButton botonCerrar;

    /* Atributos */
    private String rutaImagen;

    /* Dimensiones */
    private static final int ANCHO_PANEL = (int) (ANCHO_EMERGENTE_PREDET * 2.0 / 3.0);
	private static final int ALTO_PANEL = (int) (ALTO_EMERGENTE_PREDET * 2.0 / 3.0);

    private static final int MARGEN = 5;
    private static final int ESPACIADO_ENTRE_CAMPOS = 1;
    private static final int ANCHO_CAMPOS = 175;
    private static final int ALTO_CAMPOS = 25;
    private static final int ANCHO_ETIQUETAS = 150;
    private static final int LADO_IMAGEN = 75;
    private static final int ANCHO_BOTON_GRANDE = 140;

    public EmergentePerfil(JFrame ventanaMadre) {
        super("Perfil", GestorGUI.getInstancia().getColorClaro(), ventanaMadre);
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
        GestorGUI.fijarTamano(ANCHO_PANEL - MARGEN, ALTO_PANEL - MARGEN - 40 - 40, panelEnvolvente);

        /* Panel de campos */
        panelCampos = new JPanel();
        GestorGUI.configurarPanel(panelCampos, new BoxLayout(panelCampos, BoxLayout.Y_AXIS), false);
        panelEnvolvente.add(panelCampos, BorderLayout.CENTER);

        /* Borde */
        panelCampos.setBorder(GestorGUI.bordeTexto("Datos de perfil", GestorGUI.getInstancia().getFuenteTexto(),
                GestorGUI.getInstancia().getColorOscuro(), GestorGUI.getInstancia().getColorOscuro()));

		// Nombre /* ESTOS LOS PODRÍA LLEVAR A UNA FUNCIÓN COMÚN */
        JPanel panelNombre = new JPanel();
        GestorGUI.configurarPanel(panelNombre, new BoxLayout(panelNombre, BoxLayout.X_AXIS), false);
        JLabel etiquetaNombreEtiqueta = new JLabel("Nombre:");
        GestorGUI.configurarEtiqueta(etiquetaNombreEtiqueta, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteTexto()));
        GestorGUI.fijarTamano(ANCHO_ETIQUETAS, ALTO_CAMPOS, etiquetaNombreEtiqueta);
        etiquetaNombre = new JLabel("Juan Pérez");
        GestorGUI.configurarEtiqueta(etiquetaNombre, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.getInstancia().getFuenteTexto());
        GestorGUI.fijarTamano(ANCHO_CAMPOS, ALTO_CAMPOS, etiquetaNombre);
        panelNombre.add(etiquetaNombreEtiqueta);
        panelNombre.add(etiquetaNombre);

        // Correo
        JPanel panelCorreo = new JPanel();
        GestorGUI.configurarPanel(panelCorreo, new BoxLayout(panelCorreo, BoxLayout.X_AXIS), false);
        JLabel etiquetaCorreoEtiqueta = new JLabel("Correo:");
        GestorGUI.configurarEtiqueta(etiquetaCorreoEtiqueta, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteTexto()));
        GestorGUI.fijarTamano(ANCHO_ETIQUETAS, ALTO_CAMPOS, etiquetaCorreoEtiqueta);
        etiquetaCorreo = new JLabel("juan.perez@example.com");
        GestorGUI.configurarEtiqueta(etiquetaCorreo, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.getInstancia().getFuenteTexto());
        GestorGUI.fijarTamano(ANCHO_CAMPOS, ALTO_CAMPOS, etiquetaCorreo);
        panelCorreo.add(etiquetaCorreoEtiqueta);
        panelCorreo.add(etiquetaCorreo);

        // Saludo
        JPanel panelSaludo = new JPanel();
        GestorGUI.configurarPanel(panelSaludo, new BoxLayout(panelSaludo, BoxLayout.X_AXIS), false);
        JLabel etiquetaSaludoEtiqueta = new JLabel("Saludo:");
        GestorGUI.configurarEtiqueta(etiquetaSaludoEtiqueta, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteTexto()));
        GestorGUI.fijarTamano(ANCHO_ETIQUETAS, ALTO_CAMPOS, etiquetaSaludoEtiqueta);
        etiquetaSaludo = new JLabel("¡Hola a todos!");
        GestorGUI.configurarEtiqueta(etiquetaSaludo, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.getInstancia().getFuenteTexto());
        GestorGUI.fijarTamano(ANCHO_CAMPOS, ALTO_CAMPOS, etiquetaSaludo);
        panelSaludo.add(etiquetaSaludoEtiqueta);
        panelSaludo.add(etiquetaSaludo);

        // Nuevo saludo
        campoNuevoSaludo = new CampoTexto("Nuevo saludo:", ANCHO_CAMPOS, ALTO_CAMPOS, ANCHO_ETIQUETAS);
        JPanel panelNuevoSaludo = new JPanel();
        GestorGUI.configurarPanel(panelNuevoSaludo, new BoxLayout(panelNuevoSaludo, BoxLayout.X_AXIS), false);


        GestorGUI.fijarTamano(ANCHO_ETIQUETAS + ANCHO_CAMPOS, ALTO_CAMPOS, panelNuevoSaludo);
        panelNuevoSaludo.add(Box.createHorizontalGlue());
        

        panelNuevoSaludo.add(campoNuevoSaludo.getPanel());
        panelNuevoSaludo.add(Box.createHorizontalGlue());

        // Rol
        JPanel panelRol = new JPanel();
        GestorGUI.configurarPanel(panelRol, new BoxLayout(panelRol, BoxLayout.X_AXIS), false);
        JLabel etiquetaRolEtiqueta = new JLabel("Rol:");
        GestorGUI.configurarEtiqueta(etiquetaRolEtiqueta, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteTexto()));
        GestorGUI.fijarTamano(ANCHO_ETIQUETAS, ALTO_CAMPOS, etiquetaRolEtiqueta);
        etiquetaRol = new JLabel("Alumno");
        GestorGUI.configurarEtiqueta(etiquetaRol, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.getInstancia().getFuenteTexto());
        GestorGUI.fijarTamano(ANCHO_CAMPOS, ALTO_CAMPOS, etiquetaRol);
        panelRol.add(etiquetaRolEtiqueta);
        panelRol.add(etiquetaRol);

        /* Imagen */
        construirPanelImagen(GestorGUI.IMAGEN_PREDET_OSC);

        /* Montaje */
        panelCampos.add(Box.createVerticalStrut(MARGEN));
        panelCampos.add(panelNombre);
        panelCampos.add(Box.createVerticalStrut(ESPACIADO_ENTRE_CAMPOS));
        panelCampos.add(panelCorreo);
        panelCampos.add(Box.createVerticalStrut(ESPACIADO_ENTRE_CAMPOS));
        panelCampos.add(panelSaludo);
        panelCampos.add(Box.createVerticalStrut(ESPACIADO_ENTRE_CAMPOS));
        panelCampos.add(panelNuevoSaludo);
        panelCampos.add(Box.createVerticalStrut(ESPACIADO_ENTRE_CAMPOS + 20));
        panelCampos.add(panelRol);
        panelCampos.add(Box.createVerticalStrut(MARGEN));

        panelEnvolvente.add(panelImagen, BorderLayout.SOUTH);
    }

    private void construirPanelImagen(String ruta) {
        panelImagen = new JPanel();
        GestorGUI.configurarPanel(panelImagen, new BoxLayout(panelImagen, BoxLayout.X_AXIS), false);
        GestorGUI.fijarTamano(ANCHO_PANEL - 60, 120, panelImagen);

        etiquetaImagen = new JLabel();
        establecerImagenActual();
        etiquetaImagen.setAlignmentX(Component.LEFT_ALIGNMENT);

        botonSeleccionarImagen = GestorGUI.getBotonPredeterminado("Seleccionar imagen", ANCHO_BOTON_GRANDE);
        manejadorSeleccionarImagen();

        botonBorrarImagen = GestorGUI.getBotonPredeterminado("Borrar imagen", ANCHO_BOTON_GRANDE);
        manejadorBorrarImagen();

        /* Montaje */
        panelImagen.add(Box.createHorizontalStrut((int) (ANCHO_PANEL / 2.0) - LADO_IMAGEN - ANCHO_BOTON_GRANDE));
        panelImagen.add(etiquetaImagen);
        panelImagen.add(Box.createHorizontalStrut(GestorGUI.SEPARACION_BOTONES));
        panelImagen.add(botonSeleccionarImagen);
        panelImagen.add(Box.createHorizontalStrut(GestorGUI.SEPARACION_BOTONES));
        panelImagen.add(botonBorrarImagen);
    }

    private void establecerImagenActual() {
        rutaImagen = null;
        etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoDeRecursos(GestorGUI.IMAGEN_PREDET_OSC, LADO_IMAGEN, LADO_IMAGEN));
		// Aquí sería la imagen actual del usuario
    }

	private void establecerImagenPredeterminada() {
        rutaImagen = null;
        etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoDeRecursos(GestorGUI.IMAGEN_PREDET_OSC, LADO_IMAGEN, LADO_IMAGEN));
		// Aquí sería la imagen actual del usuario
    }

    private void manejadorSeleccionarImagen() {
        botonSeleccionarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* Lanzar la ventana emergente */
                EmergenteFichero emergente = new EmergenteFichero(ventanaMadre);
                emergente.mostrar();

                /* Obtener resultado */
                emergente.obtenerFichero().ifPresentOrElse((ruta) -> {
                    rutaImagen = ruta;
                    etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoAbsoluto(rutaImagen, LADO_IMAGEN, LADO_IMAGEN));
                }, () -> {
                    return;
                });
            }
        });
    }

    private void manejadorBorrarImagen() {
        botonBorrarImagen.addActionListener(new ActionListener() {
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
        botonConfirmar = GestorGUI.getBotonPredeterminado("Confirmar");
        botonConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostrar mensaje de confirmación
                EmergenteMensaje emergente = new EmergenteMensaje(ventanaMadre, "Cambios guardados correctamente.");
                emergente.mostrar();
            }
        });

        botonCerrar = GestorGUI.getBotonPredeterminado("Cerrar");
        botonCerrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana
            }
        });

        /* Montaje */
        panelBotones.add(Box.createHorizontalStrut((int) ((ANCHO_PANEL - (2 * GestorGUI.ANCHO_BOTON_PREDET + GestorGUI.SEPARACION_BOTONES)) / 2.0)));
        panelBotones.add(botonConfirmar);
        panelBotones.add(Box.createHorizontalStrut(GestorGUI.SEPARACION_BOTONES));
        panelBotones.add(botonCerrar);
    }
}