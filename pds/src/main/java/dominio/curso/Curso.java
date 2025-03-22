package dominio.curso;

import java.util.List;

public class Curso {
    private String nombre;
    private List<BloqueContenidos> bloquesContenidos;

    public Curso(String nombre, List<BloqueContenidos> bloquesContenidos) {
        this.nombre = nombre;
        this.bloquesContenidos = bloquesContenidos;
    }

    public String getNombre() {
        return nombre;
    }

    public List<BloqueContenidos> getBloquesContenidos() {
        return bloquesContenidos;
    }
}
