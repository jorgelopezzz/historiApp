# Modelo de Dominio - HistoriApp


Este diagrama representa el modelo de dominio de la aplicación HistoriApp. El modelo de dominio ilustra las entidades principales del sistema, sus atributos y las relaciones entre ellas, estableciendo la base conceptual sobre la que se construye toda la aplicación.

```mermaid
classDiagram
    class Usuario {
        - nombre : String
        - contraseña : String
        - imagen : Imagen
        - saludo : String
    }

    class Curso {
        - nombre : String
    }

    class Matricula {
    }

    class BloqueContenidos {
        - nombre : String
    }

    class CertificadoBloque {
        - fechaCert : localdate
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
    Usuario "*" --o "1" Matricula : realiza
    Curso "*" --o "*" Usuario : matriculado
    Curso "1" --o "*" BloqueContenidos : contiene
    BloqueContenidos "1" --o "*" Tarea : contiene
    Matricula "1" --o "1" CertificadoBloque : contiene
    BloqueContenidos "*" --o "1" CertificadoBloque : completa
    Matricula "1" --o "1" BloqueContenidos : asociada
    Tarea <|-- Tip
    Tarea <|-- Pregunta
    Pregunta <|-- PreguntaVF
    Pregunta <|-- PreguntaTipoTest
    Pregunta <|-- PreguntaRellenar
```

## Referencias Adicionales

Para más información sobre la arquitectura de la aplicación, consulte:
- [Estructuras y Patrones de Diseño de la Interfaz Gráfica](modeloVista.md)
- [Modelo de Estados y Transiciones](modeloEstados.md)
- [Casos de Uso](casosDeUso.md)
- [Documentación del Proyecto](README.md)
