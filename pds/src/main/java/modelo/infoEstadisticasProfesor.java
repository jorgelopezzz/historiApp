package modelo;

import java.time.Duration;

public class infoEstadisticasProfesor extends infoEstadisticas{
    
    private int cursosPublicados;

    public infoEstadisticasProfesor (String texto, double puntuacion, int cursosCompletados, int bloquesCompletados, Duration minutosUso, int diasUso, int maxRacha, int cursosPublicados){
        super(texto, puntuacion, cursosCompletados, bloquesCompletados, minutosUso, diasUso, maxRacha);
        
        this.cursosPublicados = cursosPublicados;
    }


    /* Getters */
    public int getCursosPublicados (){
        return cursosPublicados;
    }
}
