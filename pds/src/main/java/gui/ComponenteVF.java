package gui;

import dominio.InfoTipoTest;
import dominio.InfoVF;

public class ComponenteVF extends ComponenteTipoTest {

	public ComponenteVF(InfoVF info) {
		super(info.adaptarVF());
	}

}
