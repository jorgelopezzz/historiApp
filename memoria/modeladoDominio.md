# Modelo de Dominio - HistoriApp


Este diagrama representa el modelo de dominio de la aplicación HistoriApp. El modelo de dominio ilustra las entidades principales del sistema, sus atributos y las relaciones entre ellas, estableciendo la base conceptual sobre la que se construye toda la aplicación.

```mermaid
classDiagram
    class Usuario {
        - nombre : String
        - contraseña : String
        - imagen : Imagen
        - saludo : String
        - tiempoDeUso : Duration
        + getPuntuacion() : Int
        + getRacha() : Int
    }
    class Curso {
        - nombre : String
    }
    class RealizacionCurso {
        - fechaInicio : localDate
    }
    class RealizacionBloque {
        - puntuacion : float
    }
    class EstrategiaAprendizaje {
    }
    class BloqueContenidos {
        - nombre : String
    }
    class Tarea {
        - enunciado : String
    }
    class Tip {
        - imagen : Imagen
    }
    class Pregunta {
        - evaluar() : Boolean
    }
    class PreguntaVF {
        - esVerdadero : Boolean
    }
    class PreguntaTipoTest {
        - opciones : String[]
        - correcta : Integer
    }
    class PreguntaRellenar {
        - respuesta : String
    }
    Usuario "1" --o "*" Curso : creador
    Usuario "1" --o "*" RealizacionCurso : realiza
    Curso "1" --o "*" BloqueContenidos : contiene
    BloqueContenidos "1" --o "*" Tarea : contiene
    RealizacionCurso "*" --o "1" Curso : asociado
    RealizacionCurso "1" -- "1" EstrategiaAprendizaje : usa
    RealizacionCurso "1" -- "1..*" RealizacionBloque : contiene
    RealizacionBloque "*" -- "1" BloqueContenidos : asociado
    Tarea <|-- Tip
    Tarea <|-- Pregunta
    Pregunta <|-- PreguntaVF
    Pregunta <|-- PreguntaTipoTest
    Pregunta <|-- PreguntaRellenar
    class EstrategiaAprendizaje {
        <<enumeration>>
    }
```

## Referencias Adicionales

Para más información sobre la arquitectura de la aplicación, consulte:
- [Estructuras y Patrones de Diseño de la Interfaz Gráfica](modeloVista.md)
- [Modelo de Estados y Transiciones](modeloEstados.md)
- [Casos de Uso](casosDeUso.md)
- [Documentación del Proyecto](README.md)
