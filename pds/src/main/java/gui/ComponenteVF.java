package gui;

import modelo.InfoTipoTest;
import modelo.InfoVF;

public class ComponenteVF extends ComponenteTipoTest {

	public ComponenteVF(InfoVF info) {
		super(info.adaptarVF());
	}

}
