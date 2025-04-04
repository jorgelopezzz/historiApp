package gui.emergentes;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Optional;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import gui.GestorGUI;
import dominio.HistoriApp;
import dominio.metodoAprendizaje.MetodoAprendizaje;


@SuppressWarnings("serial")
public class EmergenteMetodoAprendizaje extends EmergenteBotones {

    /* Componentes de organización */
    private JPanel panelEnvolvente;
    private JPanel panelContenido;
    private JPanel panelBotones;
    private JPanel panelOpciones;

    /* Componentes de información */
    private JLabel etiquetaImagen;
    private JLabel etiquetaTitulo;
    private JComboBox<MetodoAprendizaje> comboEstrategias;

    /* Botones de acción */
    private JButton botonConfirmar;
    private JButton botonSalir;

    /* Atributo a devolver */
    private MetodoAprendizaje estrategiaSeleccionada;

    /* Constructor */
    public EmergenteMetodoAprendizaje(JFrame ventanaMadre) {
        super(ventanaMadre, "Método de Aprendizaje");

        gestionarCierre();
		
		construir();
    }

    @Override
    protected void construir() {
        /* Panel envolvente */
        panelEnvolvente = new JPanel();
        GestorGUI.centrarPanel(panelEnvolvente, this);
        GestorGUI.fijarTamano(ANCHO_CUADRO, ALTO_CUADRO, panelEnvolvente);

        /* Margen */
        panelEnvolvente.setBorder(new EmptyBorder(MARGEN, MARGEN, MARGEN, MARGEN));

        /* Panel Contenido */
        panelContenido = new JPanel();
        GestorGUI.configurarPanel(panelContenido, new BoxLayout(panelContenido, BoxLayout.Y_AXIS), false);

        /* Etiqueta Imagen */
        etiquetaImagen = new JLabel();
        etiquetaImagen.setIcon(GestorGUI.getInstancia().iconoDeRecursos(RUTA_ALERTA, LADO_IMAGEN, LADO_IMAGEN));
        etiquetaImagen.setAlignmentX(Component.CENTER_ALIGNMENT);

		/* Etiqueta Título */
        etiquetaTitulo = new JLabel("Escoja estrategia de aprendizaje");
        GestorGUI.configurarEtiqueta(etiquetaTitulo, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteTexto()));
		etiquetaTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        /* Montaje */
        panelContenido.add(etiquetaImagen);
        panelContenido.add(Box.createVerticalStrut(3 * MARGEN));
        panelContenido.add(etiquetaTitulo);
        panelContenido.add(Box.createVerticalStrut(MARGEN));

        botones_montaje();

        panelEnvolvente.add(panelContenido);
        this.add(panelEnvolvente);
    }

    @Override
    protected void botones_montaje() {
        panelOpciones = new JPanel();
        GestorGUI.configurarPanel(panelOpciones, new BoxLayout(panelOpciones, BoxLayout.X_AXIS), false);

        comboEstrategias = new JComboBox<>(MetodoAprendizaje.values());
        comboEstrategias.setFont(GestorGUI.getInstancia().getFuenteTexto());
        comboEstrategias.setForeground(GestorGUI.getInstancia().getColorOscuro());
        comboEstrategias.setMaximumSize(new java.awt.Dimension(200, 30));

        panelOpciones.add(Box.createHorizontalGlue());
        panelOpciones.add(comboEstrategias);
        panelOpciones.add(Box.createHorizontalGlue());

        /* Panel Botones */
        panelBotones = new JPanel();
        GestorGUI.configurarPanel(panelBotones, new BoxLayout(panelBotones, BoxLayout.X_AXIS), false);

        /* Botón Confirmar */
        botonConfirmar = GestorGUI.getBotonPredeterminado("Confirmar");
        configurarManejadorConfirmar();

        /* Botón Salir */
        botonSalir = GestorGUI.getBotonPredeterminado("Salir");
        manejadorSalir(botonSalir);

        /* Montaje*/
        panelBotones.add(Box.createHorizontalGlue());
        panelBotones.add(botonConfirmar);
        /*panelBotones.add(Box.createHorizontalStrut(MARGEN));
        panelBotones.add(botonSalir);*/
        panelBotones.add(Box.createHorizontalGlue());

        panelContenido.add(panelOpciones);
        panelContenido.add(Box.createVerticalStrut(MARGEN));
        panelContenido.add(panelBotones);
    }

    private void configurarManejadorConfirmar() {
        botonConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                estrategiaSeleccionada = (MetodoAprendizaje) comboEstrategias.getSelectedItem();
                dispose();
            }
        });
    }

    private void gestionarCierre() {
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                estrategiaSeleccionada = (MetodoAprendizaje) comboEstrategias.getSelectedItem();
            	dispose();
            }
        });
	}

    public Optional<MetodoAprendizaje> obtenerRespuesta() {
        return Optional.ofNullable(estrategiaSeleccionada);
    }
}

