package gui.emergentes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dominio.HistoriApp;
import gui.GestorGUI;

@SuppressWarnings("serial")
public class EmergenteClasificacion extends Emergente {

    // Dimensiones de la ventana
    private static final int ANCHO_VENTANA = 520;
    private static final int ALTO_VENTANA = 600;

    // Dimensiones de los paneles
    private static final int ANCHO_CAJA = 400;
    private static final int ALTO_CAJA = 60;

    private static final int MARGEN_EXTERNO = 20;
    private static final int ESPACIADO_VERTICAL_ENTRE_CAJAS = 10;
    private static final int ESPACIADO_VERTICAL_INTERIOR = 5;

    private static final int MAX_USUARIOS_MOSTRADOS = 6;

    // Colores
    private static final Color COLOR_DORADO = new Color(218, 165, 32);

    // Componentes
    private JPanel panelPrincipal;
    private JPanel panelEnvolvente;
    private JButton botonCerrar;

    public EmergenteClasificacion(JFrame ventanaMadre) {
        super("Clasificación", GestorGUI.getInstancia().getColorBlanco(), ventanaMadre, ANCHO_VENTANA, ALTO_VENTANA);
		
		construir();
    }

    @Override
    protected void construir() {
        panelPrincipal = new JPanel();
        GestorGUI.configurarPanel(panelPrincipal, new BorderLayout(), false);
        GestorGUI.centrarPanel(panelPrincipal, this);

        construirPanelEnvolvente();

        JPanel panelBoton = new JPanel();
        GestorGUI.configurarPanel(panelBoton, new BoxLayout(panelBoton, BoxLayout.Y_AXIS), false);
        panelBoton.add(Box.createVerticalStrut(MARGEN_EXTERNO));

        JPanel panelBotonCentrado = new JPanel();
        GestorGUI.configurarPanel(panelBotonCentrado, new BoxLayout(panelBotonCentrado, BoxLayout.X_AXIS), false);

        botonCerrar = GestorGUI.getBotonPredeterminado("Cerrar");
        manejadorSalir(botonCerrar);

        panelBotonCentrado.add(Box.createHorizontalGlue());
        panelBotonCentrado.add(botonCerrar);
        panelBotonCentrado.add(Box.createHorizontalGlue());

        panelBoton.add(panelBotonCentrado);
        panelBoton.add(Box.createVerticalStrut(MARGEN_EXTERNO));

        panelPrincipal.add(panelEnvolvente, BorderLayout.CENTER);
        panelPrincipal.add(panelBoton, BorderLayout.SOUTH);

        this.add(panelPrincipal);
    }

    private void construirPanelEnvolvente() {
        panelEnvolvente = new JPanel();
        GestorGUI.configurarPanel(panelEnvolvente, new BoxLayout(panelEnvolvente, BoxLayout.Y_AXIS), false);
        panelEnvolvente.setBackground(GestorGUI.getInstancia().getColorBlanco());

        panelEnvolvente.setBorder(new EmptyBorder(MARGEN_EXTERNO, MARGEN_EXTERNO, MARGEN_EXTERNO, MARGEN_EXTERNO));

        JPanel panelContenedor = new JPanel();
        GestorGUI.configurarPanel(panelContenedor, new BoxLayout(panelContenedor, BoxLayout.Y_AXIS), false);
        panelContenedor.setBackground(GestorGUI.getInstancia().getColorBlanco());

        panelContenedor.setBorder(GestorGUI.bordeTexto("Clasificación", GestorGUI.getInstancia().getFuenteTitulo(),
                GestorGUI.getInstancia().getColorOscuro(), GestorGUI.getInstancia().getColorOscuro()));

        Map<String, String> clasificacion = HistoriApp.INSTANCE.obtenerInfoRanking().getRanking();

        System.out.println(clasificacion.toString());

        int i = 0;
        for (Map.Entry<String, String> entrada : clasificacion.entrySet()) {

            boolean esPrimero = (i == 0);
            System.out.println(entrada.toString());

            String nombre = entrada.getKey();
            String puntuacion = entrada.getValue();

            System.out.println(nombre);
            System.out.println(puntuacion);

            panelContenedor.add(crearPanelPersona(nombre, puntuacion, esPrimero));
            panelContenedor.add(Box.createVerticalStrut(ESPACIADO_VERTICAL_ENTRE_CAJAS));
            i++;
            if(i >= MAX_USUARIOS_MOSTRADOS) break;
        }

        panelEnvolvente.add(panelContenedor);

    }

    private JPanel crearPanelPersona(String nombre, String puntuacion, boolean esPrimero) {
        JPanel panel = new JPanel();
        GestorGUI.configurarPanel(panel, new BoxLayout(panel, BoxLayout.Y_AXIS), GestorGUI.getInstancia().getColorBlanco());
        GestorGUI.fijarTamano(ANCHO_CAJA, ALTO_CAJA, panel);

        Color color = esPrimero ? COLOR_DORADO : GestorGUI.getInstancia().getColorOscuro();

        panel.setBorder(GestorGUI.bordeTexto("Usuario: " + nombre, GestorGUI.getInstancia().getFuenteTexto(), color, color));

        JLabel etiquetaPuntaje = new JLabel("Puntos: " + puntuacion);
        etiquetaPuntaje.setFont(GestorGUI.getInstancia().getFuenteTexto());
        etiquetaPuntaje.setForeground(color);
        etiquetaPuntaje.setAlignmentX(Component.LEFT_ALIGNMENT);

        panel.add(Box.createVerticalStrut(ESPACIADO_VERTICAL_INTERIOR));
        panel.add(etiquetaPuntaje);
        panel.add(Box.createVerticalStrut(ESPACIADO_VERTICAL_INTERIOR));

        return panel;
    }
}
