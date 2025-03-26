package gui.componentes.tarea;

import gui.info.tarea.InfoTipoTest;
import gui.info.tarea.InfoVF;

public class ComponenteVF extends ComponenteTipoTest {

	public ComponenteVF(InfoVF info) {
		super(info.adaptarVF());
	}

}
