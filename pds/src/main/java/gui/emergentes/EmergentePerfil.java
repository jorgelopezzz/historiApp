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

    /* Botones */
    private JButton botonSeleccionarImagen;
    private JButton botonBorrarImagen;
    private JButton botonConfirmar;
    private JButton botonCerrar;

    /* Dimensiones */
    private static final int ANCHO_PANEL = 466;
    private static final int ALTO_PANEL = 400;
    private static final int MARGEN = 5;
    private static final int ESPACIADO_ENTRE_CAMPOS = 1;
    private static final int ANCHO_CAMPOS = 175;
    private static final int ALTO_CAMPOS = 25;
    private static final int ANCHO_ETIQUETAS = 150;
    private static final int LADO_IMAGEN = 75;
    private static final int ANCHO_BOTON_GRANDE = 140;

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
        GestorGUI.fijarTamano(ANCHO_PANEL - MARGEN, ALTO_PANEL - MARGEN - 40 - 40, panelEnvolvente);

        panelCampos = new JPanel();
        GestorGUI.configurarPanel(panelCampos, new BoxLayout(panelCampos, BoxLayout.Y_AXIS), false);
        panelEnvolvente.add(panelCampos, BorderLayout.CENTER);

        panelCampos.setBorder(GestorGUI.bordeTexto("Datos de perfil", GestorGUI.getInstancia().getFuenteTexto(),
                GestorGUI.getInstancia().getColorOscuro(), GestorGUI.getInstancia().getColorOscuro()));

        infoPerfilUsuario datos = HistoriApp.INSTANCE.pedirDatosUsuario();
        
        // Nombre
        panelNombre = new JPanel();
        GestorGUI.configurarPanel(panelNombre, new BoxLayout(panelNombre, BoxLayout.X_AXIS), false);
        etiquetaNombreEtiqueta = new JLabel("Nombre:");
        etiquetaNombre = new JLabel(datos.getNombre());
        
        GestorGUI.configurarEtiqueta(etiquetaNombreEtiqueta, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteTexto()));
        GestorGUI.fijarTamano(ANCHO_ETIQUETAS, ALTO_CAMPOS, etiquetaNombreEtiqueta);
    
        GestorGUI.configurarEtiqueta(etiquetaNombre, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.getInstancia().getFuenteTexto());
        GestorGUI.fijarTamano(ANCHO_CAMPOS, ALTO_CAMPOS, etiquetaNombre);

        panelNombre.add(etiquetaNombreEtiqueta);
        panelNombre.add(etiquetaNombre);

        // Correo
        panelCorreo = new JPanel();
        GestorGUI.configurarPanel(panelCorreo, new BoxLayout(panelCorreo, BoxLayout.X_AXIS), false);
        etiquetaCorreoEtiqueta = new JLabel("Correo:");
        etiquetaCorreo = new JLabel(datos.getCorreo());
        
        GestorGUI.configurarEtiqueta(etiquetaCorreoEtiqueta, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteTexto()));
        GestorGUI.fijarTamano(ANCHO_ETIQUETAS, ALTO_CAMPOS, etiquetaCorreoEtiqueta);
    
        GestorGUI.configurarEtiqueta(etiquetaCorreo, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.getInstancia().getFuenteTexto());
        GestorGUI.fijarTamano(ANCHO_CAMPOS, ALTO_CAMPOS, etiquetaCorreo);

        panelCorreo.add(etiquetaCorreoEtiqueta);
        panelCorreo.add(etiquetaCorreo);

        // Saludo
        panelSaludo = new JPanel();
        GestorGUI.configurarPanel(panelSaludo, new BoxLayout(panelSaludo, BoxLayout.X_AXIS), false);
        etiquetaSaludoEtiqueta = new JLabel("Saludo:");
        etiquetaSaludo = new JLabel(datos.getSaludo());
        
        GestorGUI.configurarEtiqueta(etiquetaSaludoEtiqueta, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteTexto()));
        GestorGUI.fijarTamano(ANCHO_ETIQUETAS, ALTO_CAMPOS, etiquetaSaludoEtiqueta);
    
        GestorGUI.configurarEtiqueta(etiquetaSaludo, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.getInstancia().getFuenteTexto());
        GestorGUI.fijarTamano(ANCHO_CAMPOS, ALTO_CAMPOS, etiquetaSaludo);

        panelSaludo.add(etiquetaSaludoEtiqueta);
        panelSaludo.add(etiquetaSaludo);

        // Nuevo saludo
        campoNuevoSaludo = new CampoTexto("Nuevo saludo:", ANCHO_CAMPOS, ALTO_CAMPOS, ANCHO_ETIQUETAS);
        panelNuevoSaludo = new JPanel();
        GestorGUI.configurarPanel(panelNuevoSaludo, new BoxLayout(panelNuevoSaludo, BoxLayout.X_AXIS), false);
        GestorGUI.fijarTamano(ANCHO_ETIQUETAS + ANCHO_CAMPOS, ALTO_CAMPOS, panelNuevoSaludo);
        panelNuevoSaludo.add(Box.createHorizontalGlue());
        panelNuevoSaludo.add(campoNuevoSaludo.getPanel());
        panelNuevoSaludo.add(Box.createHorizontalGlue());

        // Rol
        panelRol = new JPanel();
        GestorGUI.configurarPanel(panelRol, new BoxLayout(panelRol, BoxLayout.X_AXIS), false);
        etiquetaRolEtiqueta = new JLabel("Rol:");
        etiquetaRol = new JLabel(datos.getRol());
        
        GestorGUI.configurarEtiqueta(etiquetaRolEtiqueta, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteTexto()));
        GestorGUI.fijarTamano(ANCHO_ETIQUETAS, ALTO_CAMPOS, etiquetaRolEtiqueta);
    
        GestorGUI.configurarEtiqueta(etiquetaRol, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.getInstancia().getFuenteTexto());
        GestorGUI.fijarTamano(ANCHO_CAMPOS, ALTO_CAMPOS, etiquetaRol);
        
        panelRol.add(etiquetaRolEtiqueta);
        panelRol.add(etiquetaRol);

        // Imagen
        construirPanelImagen(datos.getImagen());

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
        etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoDeRecursos(GestorGUI.IMAGEN_PREDET_OSC, LADO_IMAGEN, LADO_IMAGEN));
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
        rutaImagen = null;
        etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoDeRecursos(GestorGUI.IMAGEN_PREDET_OSC, LADO_IMAGEN, LADO_IMAGEN));
    }

    private void manejadorSeleccionarImagen() {
        botonSeleccionarImagen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EmergenteFichero emergente = new EmergenteFichero(ventanaMadre);
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
            HistoriApp.INSTANCE.cambiarInformacionPerfil(rutaImagen, campoNuevoSaludo.getTexto());
            EmergenteMensaje emergente = new EmergenteMensaje(ventanaMadre, "Cambios guardados correctamente.");
            emergente.mostrar();
        });
    }

}