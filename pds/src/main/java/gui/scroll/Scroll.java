package gui.scroll;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public abstract class Scroll extends JScrollPane {

    public Scroll(JPanel panelMenu) {
        super(panelMenu, VERTICAL_SCROLLBAR_ALWAYS, HORIZONTAL_SCROLLBAR_NEVER);

        // Personalizar el JScrollPane
        setBackground(new Color(240, 240, 240));
        setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));
        setViewportBorder(new EmptyBorder(5, 5, 5, 5));

        // Personalizar las barras de desplazamiento
        JScrollBar verticalScrollBar = getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(20);
        verticalScrollBar.setBackground(new Color(230, 230, 230));
        verticalScrollBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createArrowButton(orientation);
            }
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createArrowButton(orientation);
            }
        });
    }

    private JButton createArrowButton(int orientation) {
        JButton arrowButton = new JButton();
        arrowButton.setPreferredSize(new Dimension(0, 0));
        return arrowButton;
    }
}
