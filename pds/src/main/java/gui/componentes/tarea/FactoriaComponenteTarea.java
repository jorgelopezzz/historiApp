package gui.componentes.tarea;

import dominio.info.Info;
import dominio.info.tarea.InfoRellenar;
import dominio.info.tarea.InfoTip;
import dominio.info.tarea.InfoTipoTest;
import dominio.info.tarea.InfoVF;

public class FactoriaComponenteTarea {
    
	  public static ComponenteTarea crearTarea(Info infoTarea) {
	        if (infoTarea instanceof InfoTip) {
	            return new ComponenteTip((InfoTip) infoTarea);
	        } 
	        
	        if (infoTarea instanceof InfoTipoTest) {
	            return new ComponenteTipoTest((InfoTipoTest) infoTarea);
	        } 
	        
	        if (infoTarea instanceof InfoVF) {
	            return new ComponenteVF((InfoVF) infoTarea);
	        } 
	        
	        if (infoTarea instanceof InfoRellenar) {
	        	return new ComponenteRellenar((InfoRellenar) infoTarea);
	        }
	        
	        throw new IllegalArgumentException("Tipo de Info no reconocido: ");
    	    }
}
