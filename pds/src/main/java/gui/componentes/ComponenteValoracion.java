package gui.componentes;

import javax.swing.*;

import dominio.info.InfoValoracion;
import gui.GestorGUI;

import java.awt.*;

@SuppressWarnings("serial")
public class ComponenteValoracion extends Componente {

	public static int ANCHO_PREDETERMINADO = 300;
	public static int ALTO_PREDETERMINADO = 100;
	
    private int nota; 
    private String comentario;

    public ComponenteValoracion(InfoValoracion info) {
    	this(info.getPuntuacion(), info.getTitulo());
    }
    
    private ComponenteValoracion(int nota, String comentario) {
        super(ANCHO_PREDETERMINADO, ALTO_PREDETERMINADO);
        this.nota = nota;
        this.comentario = comentario;

        construirInterfaz();
    }

    private void construirInterfaz() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, true));

        JPanel panelContenido = new JPanel();
        GestorGUI.configurarPanel(panelContenido, new BoxLayout(panelContenido, BoxLayout.Y_AXIS), GestorGUI.getInstancia().getColorOscuro());
        panelContenido.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Representación de la nota con estrellas
        JLabel estrellas = new JLabel(generarEstrellas(nota));
        estrellas.setFont(new Font("SansSerif", Font.PLAIN, 18));
        estrellas.setForeground(GestorGUI.getInstancia().getColorBlanco()); // color tipo estrella dorada
        estrellas.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelContenido.add(estrellas);

        panelContenido.add(Box.createRigidArea(new Dimension(0, 8)));

        // Comentario
        JTextArea areaComentario = new JTextArea(comentario);
        areaComentario.setWrapStyleWord(true);
        areaComentario.setLineWrap(true);
        areaComentario.setEditable(false);
        areaComentario.setFocusable(false);
        areaComentario.setBackground(GestorGUI.getInstancia().getColorClaro());
        areaComentario.setForeground(GestorGUI.getInstancia().getColorOscuro());
        areaComentario.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        areaComentario.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelContenido.add(areaComentario);

        add(panelContenido, BorderLayout.CENTER);
    }

    private String generarEstrellas(int nota) {
        int estrellasLlenas = Math.min(nota, 10);
        int estrellasVacias = 10 - estrellasLlenas;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < estrellasLlenas; i++) {
            sb.append("★");
        }
        for (int i = 0; i < estrellasVacias; i++) {
            sb.append("☆");
        }
        return sb.toString();
    }
}
