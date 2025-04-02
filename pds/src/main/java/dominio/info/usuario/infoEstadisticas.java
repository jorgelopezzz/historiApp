package dominio.info.usuario;

import java.util.Optional;

import dominio.info.Info;

public class infoEstadisticas extends Info{
    
    private int puntuacion;
    private int bloquesCompletados;
    private int cursosCompletados;
    private String minutosUso;
    private String minutosUsoDiario;
    private int maxRacha;

    public infoEstadisticas (String texto, int puntuacion, int bloquesCompletados, int cursosCompletados, int minutosUso, int diasUso, int maxRacha){
        super(texto);
        this.puntuacion = puntuacion;
        this.bloquesCompletados = bloquesCompletados;
        this.cursosCompletados = cursosCompletados;
        this.maxRacha = maxRacha;

        int dias = minutosUso / 1440;
        int horas = (minutosUso % 1440) / 60;
        int minutos = minutosUso % 60;

        this.minutosUso = String.format("%02d:%02d:%02d", dias, horas, minutos);

        int tiempo = minutosUso/diasUso;
        horas = tiempo / 60;
        minutos = tiempo % 60;

        this.minutosUsoDiario = String.format("%02d:%02d", horas, minutos);
    }

    /* Getters */

    public int getPuntuacion (){
        return puntuacion;
    }
    
    public int getBloquesCompletados (){
        return bloquesCompletados;
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
