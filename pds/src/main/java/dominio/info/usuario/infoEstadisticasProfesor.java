package dominio.info.usuario;

import java.util.Optional;

public class infoEstadisticasProfesor extends infoEstadisticas{
    
    private int cursosPublicados;

    public infoEstadisticasProfesor (String texto, int puntuacion, int bloquesCompletados, int cursosCompletados, int minutosUso, int diasUso, int maxRacha, int cursosPublicados){
        super(texto, puntuacion, bloquesCompletados, cursosCompletados, minutosUso, diasUso, maxRacha);
        
        this.cursosPublicados = cursosPublicados;

    }


    /* Getters */
    public int getCursosPublicados (){
        return cursosPublicados;
    }
}
