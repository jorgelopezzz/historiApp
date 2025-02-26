package gui.scroll;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import gui.GestorGUI;
import gui.componentes.Componente;
import gui.componentes.ComponenteCurso;
import gui.emergentes.EmergenteClasificacion;
import gui.emergentes.EmergenteMensaje;
import gui.emergentes.EmergenteSiNo;
import gui.info.InfoCurso;
import gui.ventanas.SelectorVentana;
import gui.ventanas.VentanaBloques;
import gui.ventanas.VentanaCursos;
import gui.ventanas.VentanaMenu;

@SuppressWarnings("serial")
public class Scroll extends JScrollPane {
	
	/* Selector */
	
	SelectorVentana selector;
	
	/* Lista de componentes */
	private JList<Componente> listaComponentes;
	private JFrame ventanaMadre;
	
	public Scroll(JFrame ventanaMadre, SelectorVentana selector, ComponenteCurso[] componentes) {
		super();
		
		this.selector = selector;
		
		/* Ventana madre */
		
		this.ventanaMadre = ventanaMadre;
		
		/* Aspectos gráficos */
		GestorGUI.fijarTamano(800,600, this);
		setBackground(GestorGUI.getInstancia().getColorBlanco());
        setBorder(BorderFactory.createLineBorder(GestorGUI.getInstancia().getColorClaro()));
        setViewportBorder(new EmptyBorder(5, 5, 5, 5));
        personalizacionBarras();
        
        /* Comportamiento de barras de desplazamiento */
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		/* Vinculación de lista */
		configurarLista(componentes);
		setViewportView(listaComponentes);	
		
	}
	
	private void personalizacionBarras() {
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

	private void configurarLista(ComponenteCurso[] componentes) {
		/* Aspectos básicos */
		listaComponentes = new JList<Componente>(componentes);
		listaComponentes.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		
		/* Dimensiones de celda */
		listaComponentes.setFixedCellHeight(Componente.ALTO_COMPONENTE);
		listaComponentes.setFixedCellWidth(Componente.ANCHO_COMPONENTE);
		listaComponentes.setVisibleRowCount(-1);
		
		/* Comportamiento */
		dibujarLista();
		detectarDobleClick();

	}
	
	private void dibujarLista() {
		listaComponentes.setCellRenderer(new ListCellRenderer<Componente>() {
	        @Override
	        public Component getListCellRendererComponent(JList<? extends Componente> list, Componente co, int index,
	                                                      boolean isSelected, boolean cellHasFocus) {
	            if(isSelected)
	            	co.seleccionar();
	            else
	            	co.deseleccionar();
	            return co;
	        }
	    });
		
	}
	
	private void detectarDobleClick() {
	    listaComponentes.addMouseListener(new java.awt.event.MouseAdapter() {
	        @Override
	        public void mouseClicked(java.awt.event.MouseEvent evt) {
	            if (evt.getClickCount() == 2) {
	                int index = listaComponentes.locationToIndex(evt.getPoint());
	                if (index != -1) {
	                    ComponenteCurso seleccionado = (ComponenteCurso) listaComponentes.getModel().getElementAt(index);
	                    EmergenteSiNo emergente = new EmergenteSiNo(ventanaMadre, "ey");
	    				emergente.mostrar();
	    				if(emergente.obtenerRespuesta())
	    					selector.cambiarVentana(new VentanaBloques(selector,seleccionado));
	                }
	            }
	        }
	    });
	}
	
	/*
	    li.addListSelectionListener(event -> {
	        // por si no funciona el mouseListener
	    });
	 * 
	 */
	
	
}
