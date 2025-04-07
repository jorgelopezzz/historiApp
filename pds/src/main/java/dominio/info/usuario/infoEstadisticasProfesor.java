package dominio.info.usuario;

import java.time.Duration;
import java.util.Optional;

public class infoEstadisticasProfesor extends infoEstadisticas{
    
    private int cursosPublicados;

    public infoEstadisticasProfesor (String texto, int puntuacion, int cursosCompletados, Duration minutosUso, int diasUso, int maxRacha, int cursosPublicados){
        super(texto, puntuacion, cursosCompletados, minutosUso, diasUso, maxRacha);
        
        this.cursosPublicados = cursosPublicados;

    }


    /* Getters */
    public int getCursosPublicados (){
        return cursosPublicados;
    }
}
