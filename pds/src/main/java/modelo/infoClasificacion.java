package modelo;

import java.util.Map;

public class infoClasificacion {

    private Map<String, String> clasificacion;

    public infoClasificacion(Map<String, String> clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Map<String, String> getClasificacion() {
        return clasificacion;
    }

}
