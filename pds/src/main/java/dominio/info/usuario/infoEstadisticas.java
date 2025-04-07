package dominio.info.usuario;

import java.time.Duration;
import java.util.Optional;

import dominio.info.Info;

public class infoEstadisticas extends Info{
    
    private int puntuacion;
    private int cursosCompletados;
    private String minutosUso;
    private String minutosUsoDiario;
    private int maxRacha;

    public infoEstadisticas (String texto, int puntuacion, int cursosCompletados, Duration tiempoUso, int diasUso, int maxRacha){
        super(texto);
        this.puntuacion = puntuacion;
        this.cursosCompletados = cursosCompletados;
        this.maxRacha = maxRacha;

        int totalSegundos = ((Long) tiempoUso.toSeconds()).intValue();
        
        /* Uso total */
        int horas = totalSegundos / 3600;
        int minutos = (totalSegundos % 3600) / 60;
        int segundos = totalSegundos - minutos * 60 - horas * 3600;

        this.minutosUso = String.format("%02d:%02d:%02d", horas, minutos, segundos);
        
        /* Uso medio por día */
        totalSegundos = diasUso == 0 ? 0 : totalSegundos/diasUso;
        horas = totalSegundos / 3600;
        minutos = (totalSegundos % 3600) / 60;
        segundos = totalSegundos - minutos * 60 - horas * 3600;
        

        this.minutosUsoDiario = String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    /* Getters */

    public int getPuntuacion (){
        return puntuacion;
    }

    public int getCursosCompletados (){
        return cursosCompletados;
    }

    public String getMinutosUso (){
        return minutosUso;
    }

    public String getMinutosUsoDiario (){
        return minutosUsoDiario;
    }

    public int getMaxRacha (){
        return maxRacha;
    }

}
