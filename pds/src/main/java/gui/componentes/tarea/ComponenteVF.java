package gui.componentes.tarea;

import dominio.info.tarea.InfoTipoTest;
import dominio.info.tarea.InfoVF;

public class ComponenteVF extends ComponenteTipoTest {

	public ComponenteVF(InfoVF info) {
		super(info.adaptarVF());
	}

}
