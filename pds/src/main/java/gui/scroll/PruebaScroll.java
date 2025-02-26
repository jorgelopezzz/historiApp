package gui.scroll;

import java.awt.Component;
import java.util.List;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import gui.ContactBox;
import gui.componentes.Componente;
import gui.componentes.ComponenteCurso;
import gui.info.InfoCurso;

public class PruebaScroll extends JScrollPane {

	/* Lista de componentes */
	private JList<Componente> listaComponentes;
	
	public PruebaScroll(ComponenteCurso[] componentes) {
		super();
		
		/* Vinculación de lista */
		configurarLista(componentes);
		setViewportView(listaComponentes);
		
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		
		
	}
	
	private void configurarLista(ComponenteCurso[] componentes) {
		
		/* Aspectos básicos */
		listaComponentes = new JList(componentes);
		listaComponentes.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		
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
	
}
