# Modelo de Estados y Transiciones


Este diagrama ilustra la dinámica de la aplicación HistoriApp y los cambios entre ventanas. El modelo de estados representa el flujo de navegación del usuario a través de la aplicación, mostrando las posibles transiciones entre diferentes interfaces y los eventos que las desencadenan.

```mermaid
stateDiagram
    VentanaLogin --> VentanaRegistro : registrarUsuario
    VentanaRegistro --> VentanaLogin : usuarioRegistrado / cancelarRegistro
    VentanaLogin --> VentanaMenu : logInUsuario
    VentanaMenu --> VentanaLogin : logOutUsuario
    VentanaMenu --> VentanaCurso : seleccionarCurso
    VentanaCurso --> VentanaMenu : volverMenu
    VentanaCurso --> VentanaPregunta_i : cursarBloque
    VentanaPregunta_i --> VentanaPregunta_i+1 : siguientePregunta
    VentanaPregunta_i+1 --> ... : siguientePregunta
    ... --> VentanaPregunta_n : siguientePregunta
    VentanaPregunta_n --> VentanaCurso : completarCurso / salirBloque
    VentanaPregunta_i --> VentanaCurso : salirBloque
    VentanaPregunta_i+1 --> VentanaCurso : salirBloque
    VentanaCurso --> VentanaMenu : salirBloque
```

## Referencias Adicionales

Para más información sobre la arquitectura de la aplicación, consulte:
- [Modelo de Dominio](modeloDominio.md)
- [Estructuras y Patrones de Diseño de la Interfaz Gráfica](modeloVista.md)
- [Casos de Uso](casosDeUso.md)
- [Documentación del Proyecto](README.md)
- [Funcionalidad extra](funcionalidadExtra.md)