package gui;

import dominio.Info;
import dominio.InfoRellenar;
import dominio.InfoTip;
import dominio.InfoTipoTest;
import dominio.InfoVF;

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
