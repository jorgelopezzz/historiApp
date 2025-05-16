package gui;

import javax.swing.*;

import controlador.HistoriApp;
import utils.GestorGUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class EmergenteValoracion extends Emergente {

    private JSlider sliderValoracion;
    private JTextArea campoComentario;
    private JButton botonEnviar;

    public EmergenteValoracion(JFrame ventanaMadre) {
        super("Valoración", GestorGUI.getInstancia().getColorOscuro(), ventanaMadre);
        construir();
    }

    @Override
    protected void construir() {
        setLayout(new BorderLayout(20, 20));

        // Panel principal con márgenes
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(getContentPane().getBackground());
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Etiqueta
        JLabel etiqueta = new JLabel("Valoración");
        GestorGUI.configurarEtiqueta(etiqueta, false, GestorGUI.getInstancia().getColorClaro(), GestorGUI.hacerNegrita(GestorGUI.getInstancia().getFuenteGrande()));
        
        etiqueta.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        /* Montaje título */
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(etiqueta);
        panelPrincipal.add(Box.createVerticalStrut(20));

        /* Configuración de slider */
        sliderValoracion = new JSlider(0, 10, 5); // valor mínimo, máximo, inicial
        sliderValoracion.setMajorTickSpacing(1);
        
        sliderValoracion.setPaintTicks(true);
        sliderValoracion.setPaintLabels(true);
        sliderValoracion.setSnapToTicks(true);
        
        /* Ajustes gráficos */
        sliderValoracion.setOpaque(false);
        sliderValoracion.setForeground(GestorGUI.getInstancia().getColorClaro());
        
        sliderValoracion.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(sliderValoracion);
        panelPrincipal.add(Box.createVerticalStrut(20));

        // Campo de comentario
        campoComentario = new JTextArea(5, 20);
        campoComentario.setLineWrap(true);
        campoComentario.setWrapStyleWord(true);
        
        /* Ajustes gráficos */
        campoComentario.setForeground(GestorGUI.getInstancia().getColorOscuro());
        campoComentario.setBackground(GestorGUI.getInstancia().getColorClaro());
        
        JScrollPane scroll = new JScrollPane(campoComentario);
        scroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        
        JLabel etiquetaComentario = new JLabel("Deja un comentario:");
        GestorGUI.configurarEtiqueta(etiquetaComentario, false, GestorGUI.getInstancia().getColorClaro(), GestorGUI.getInstancia().getFuenteGrande());
        etiquetaComentario.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        /* Montaje */
        panelPrincipal.add(etiquetaComentario);
        panelPrincipal.add(Box.createVerticalStrut(10));
        panelPrincipal.add(scroll);
        panelPrincipal.add(Box.createVerticalStrut(20));

        // Botón Enviar
        botonEnviar = GestorGUI.getBotonPredeterminado("Enviar");
        botonEnviar.setForeground(GestorGUI.getInstancia().getColorOscuro());
        botonEnviar.setBackground(GestorGUI.getInstancia().getColorClaro());
        botonEnviar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelPrincipal.add(botonEnviar);

        // Acción del botón
        botonEnviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int valor = sliderValoracion.getValue();
                String comentario = campoComentario.getText();
                HistoriApp.INSTANCE.hacerValoracion(comentario, valor);
                cerrar();
            }
        });

        // Agregamos panel al contenedor
        add(panelPrincipal, BorderLayout.CENTER);
    }
}
