package gui.emergentes;

import gui.GestorGUI;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class EmergenteEstadisticas extends Emergente {

    // Dimensiones
    private static final int ANCHO_VENTANA = 526;
    private static final int ALTO_VENTANA = 440;

    private static final int ANCHO_PANEL = 486;
    private static final int ALTO_PANEL = 370;

    private static final int MARGEN = 10;
    private static final int MARGEN_INTERNO = 5;

    private static final int ANCHO_ETIQUETAS = 150;
    private static final int ANCHO_DATOS = 80;

    private static final int ALTO_ESTATISTICA = 40;

    // Componentes
    private JPanel panelPrincipal;
    private JPanel panelEnvolvente;

    public EmergenteEstadisticas(JFrame ventanaMadre) {
        super("Estadísticas de Uso", GestorGUI.getInstancia().getColorBlanco(), ventanaMadre, ANCHO_VENTANA, ALTO_VENTANA);
    }

    @Override
    protected void construir() {
        // Panel principal
        panelPrincipal = new JPanel();
        GestorGUI.configurarPanel(panelPrincipal, new BorderLayout(), false);
        GestorGUI.centrarPanel(panelPrincipal, this);

        // Construcción del panel envolvente
        construirPanelEnvolvente();

        // Montaje
        panelPrincipal.add(panelEnvolvente, BorderLayout.CENTER);

        this.add(panelPrincipal);
    }

    private void construirPanelEnvolvente() {
        // panelEnvolvente
        panelEnvolvente = new JPanel();
        GestorGUI.configurarPanel(panelEnvolvente, new BoxLayout(panelEnvolvente, BoxLayout.Y_AXIS), 
                GestorGUI.getInstancia().getColorBlanco());
        GestorGUI.fijarTamano(ANCHO_PANEL, ALTO_PANEL, panelEnvolvente);
        panelEnvolvente.setBorder(GestorGUI.bordeTexto("Estadísticas de Uso",
                GestorGUI.getInstancia().getFuenteTexto(),
                GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.getInstancia().getColorOscuro()));

        // Añadir margen
        panelEnvolvente.add(Box.createVerticalStrut(MARGEN));

        // Añadir estadísticas
        panelEnvolvente.add(crearPanelEstadistica("Cursos completados:", "3"));
        panelEnvolvente.add(Box.createVerticalStrut(MARGEN_INTERNO));
        panelEnvolvente.add(crearPanelEstadistica("Bloques completados:", "15"));
        panelEnvolvente.add(Box.createVerticalStrut(MARGEN_INTERNO));
        panelEnvolvente.add(crearPanelEstadistica("Puntuación:", "1200"));
        panelEnvolvente.add(Box.createVerticalStrut(MARGEN_INTERNO));
        panelEnvolvente.add(crearPanelEstadistica("Tiempo de uso:", "80 horas"));
        panelEnvolvente.add(Box.createVerticalStrut(MARGEN_INTERNO));
        panelEnvolvente.add(crearPanelEstadistica("Tiempo de uso diario:", "1.5 horas"));
        panelEnvolvente.add(Box.createVerticalStrut(MARGEN_INTERNO));
        panelEnvolvente.add(crearPanelEstadistica("Mejor racha:", "5 días"));
    }

    private JPanel crearPanelEstadistica(String nombre, String dato) {
        JPanel panelContenedor = new JPanel();
        GestorGUI.configurarPanel(panelContenedor, new BoxLayout(panelContenedor, BoxLayout.Y_AXIS), false);
        panelContenedor.setBorder(BorderFactory.createLineBorder(GestorGUI.getInstancia().getColorOscuro()));

        // Panel interno para la estadística
        JPanel panel = new JPanel();
        GestorGUI.configurarPanel(panel, new BoxLayout(panel, BoxLayout.X_AXIS), false);
        GestorGUI.fijarTamano(ANCHO_PANEL - 2 * (MARGEN + MARGEN_INTERNO), ALTO_ESTATISTICA, panel);

        panel.add(Box.createHorizontalStrut(MARGEN_INTERNO));

        JLabel lblNombre = new JLabel(nombre);
        GestorGUI.configurarEtiqueta(lblNombre, false, GestorGUI.getInstancia().getColorOscuro(), 
                GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteTexto()));
        GestorGUI.fijarTamano(ANCHO_ETIQUETAS, ALTO_ESTATISTICA, lblNombre);
        panel.add(lblNombre);

        panel.add(Box.createHorizontalGlue()); // Espacio

        JLabel lblDato = new JLabel(dato);
        GestorGUI.configurarEtiqueta(lblDato, false, GestorGUI.getInstancia().getColorOscuro(),
                GestorGUI.getInstancia().getFuenteTexto());
        GestorGUI.fijarTamano(ANCHO_DATOS, ALTO_ESTATISTICA, lblDato);
        panel.add(lblDato);

        // Margen a la derecha
        panel.add(Box.createHorizontalStrut(MARGEN_INTERNO));

        // Añadir el panel interno
        panelContenedor.add(Box.createVerticalStrut(MARGEN_INTERNO));
        panelContenedor.add(panel);
        panelContenedor.add(Box.createVerticalStrut(MARGEN_INTERNO));

        return panelContenedor;
    }
}