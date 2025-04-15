# Estructuras y Patrones de Diseño - Interfaz Gráfica


Este esquema presenta las estructuras y patrones de diseño aplicados en la arquitectura de la interfaz gráfica de HistoriApp. El diagrama muestra la organización de los componentes visuales, su jerarquía y las relaciones que permiten una experiencia de usuario coherente y eficiente.

```mermaid
classDiagram
    class SelectorVentana {
        - ventana : Ventana
        + SelectorVentana(ventanaInicial)
        + cambiarVentana(ventana)
    }

    class Ventana {
        - selector : SelectorVentana
        + mostrar()
    }

    class VentanaPregunta
    class VentanaLogin
    class VentanaRegistro
    class VentanaMenu

    class Pop {
    }

    class PopUp {
        + PopUp(texto)
    }

    class PopEstadisticas
    class ComponenteScroll
    class TarjetaTarea
    class TarjetaTip
    class TarjetaPregunta
    class TarjetaPreguntaVF
    class TarjetaPreguntaTipoTest
    class TarjetaPreguntaRellenar
    class ComponenteBloque
    class ComponenteCurso

    Ventana <|-- VentanaPregunta
    Ventana <|-- VentanaLogin
    Ventana <|-- VentanaRegistro
    Ventana <|-- VentanaMenu

    Pop <|-- PopUp
    Pop <|-- PopEstadisticas

    ComponenteScroll <|-- ComponenteBloque
    ComponenteScroll <|-- ComponenteCurso

    TarjetaTarea <|-- TarjetaTip
    TarjetaTarea <|-- TarjetaPregunta

    TarjetaPregunta <|-- TarjetaPreguntaVF
    TarjetaPregunta <|-- TarjetaPreguntaTipoTest
    TarjetaPregunta <|-- TarjetaPreguntaRellenar

    Ventana o-- SelectorVentana
    SelectorVentana .. Ventana
```

## Referencias Adicionales

Para más información sobre la arquitectura de la aplicación, consulte:
- [Modelo de Dominio](modeloDominio.md)
- [Modelo de Estados y Transiciones](modeloEstados.md)
- [Casos de Uso](casosDeUso.md)
- [Documentación del Proyecto](README.md)
- [Funcionalidad extra](funcionalidadExtra.md)
