package dominio.metodoAprendizaje;

import dominio.tarea.Tarea;

import java.util.List;

/**
 * Factoría para crear diferentes tipos de iteradores según el método de aprendizaje.
 */
public class FactoriaIteradorTarea {
    public static IteradorTarea crearIterador(List<Tarea> tareas, MetodoAprendizaje metodoAprendizaje) {
        switch (metodoAprendizaje) {
            case SECUENCIAL:
                return new IteradorSecuencial(tareas);
            case ESPACIADA:
                return new IteradorEspaciado(tareas);
            case ALEATORIA:
                return new IteradorAleatoria(tareas);
            default:
                throw new IllegalArgumentException("Método de aprendizaje no soportado.");
        }
    }
}
