# Patrones de Diseño en la Aplicación HistoriApp

Este documento detalla los patrones de diseño utilizados en la aplicación **HistoriApp**, una plataforma interactiva para el aprendizaje de historia. Los patrones aplicados han sido elegidos con el objetivo de mejorar la **mantenibilidad**, **escalabilidad** y **testabilidad** del software, siguiendo buenas prácticas de diseño orientado a objetos.

---

## 1. Patrón **Iterador** en `BloqueContenidos`

Se aplica el patrón **Iterator** para recorrer las tareas (`Tarea`) contenidas en un `BloqueContenidos`. El iterador se implementa de forma que su **orden de recorrido varía** en función del `MetodoAprendizaje` seleccionado por el usuario (por ejemplo: cronológico, aleatorio, por dificultad).

- **Propósito:** encapsular la lógica de iteración y hacerla extensible según el método de aprendizaje.
- **Ventaja:** separación clara entre la colección (`BloqueContenidos`) y la forma de recorrerla.
- **Implementación:** la incorporación de esta funcionalidad a la rama principal del proyecto se hizo en [este pull request](https://github.com/usuario/repositorio/pull/26).

---

## 2. Patrón **State** en `SelectorVentana`

La clase `SelectorVentana` aplica el patrón **State** para manejar dinámicamente el estado actual de la GUI. Permite cambiar entre **ventanas principales** y **ventanas emergentes** de forma flexible y desacoplada.

- **Propósito:** facilitar la gestión del estado visual de la aplicación sin lógica condicional dispersa.
- **Ventaja:** claridad y facilidad de ampliación en la navegación de la interfaz.


---

## 3. Patrón **Estrategia** para la construcción gráfica (`Ventana` y `Emergente`)

Tanto las clases `Ventana` como `Emergente` utilizan el patrón **Strategy** para definir diferentes estrategias de construcción gráfica, delegando en objetos especializados la disposición y comportamiento visual.

- **Propósito:** permitir personalizar y reutilizar lógicas de construcción visual en diferentes contextos.
- **Ventaja:** mayor flexibilidad y reutilización en la interfaz de usuario.

---

## 4. **Inyección de Dependencias** para pruebas y desacoplamiento

Se utiliza el patrón de **Dependency Injection** para desacoplar:
- Los **repositorios** del controlador `HistoriApp`
- El `EntityManagerFactory` y el `ServicioJSON` de `RepositorioUsuario` y `RepositorioCurso`.

Esto permite:
- Ejecutar **pruebas de integración** mediante objetos **Mock**.
- Mejorar la **modularidad** y la **testabilidad** del sistema.

- **Implementación:** la incorporación de esta funcionalidad a la rama principal del proyecto se hizo en [este pull request](https://github.com/usuario/repositorio/pull/73).

---

## 5. **Fábrica jerarquizada** en `Tarea` e `Info` → `ComponenteTarea`

La jerarquía de `Tarea` cuenta con una **factoría jerarquizada** que genera objetos `Info`, encargados de transportar datos del dominio hacia la GUI. Posteriormente, otra **factoría** convierte los `Info` en `ComponenteTarea`, su equivalente gráfico.

- **Propósito:** desacoplar la lógica de construcción entre modelo y vista.
- **Ventaja:** diseño limpio y expansible para nuevas representaciones gráficas o tipos de tareas.
- **Implementación:** la incorporación de esta funcionalidad a la rama principal del proyecto se hizo en [este pull request](https://github.com/usuario/repositorio/pull/28).

---

## 6. Patrón **Estrategia** en validación de campos (`Registro` y `Login`)

La verificación de validez de campos en las ventanas de **registro** y **login** se realiza aplicando el patrón **Strategy**, permitiendo definir diversas reglas de validación de forma intercambiable.

- **Propósito:** separar la lógica de validación del flujo general de la ventana.
- **Ventaja:** permite agregar o modificar criterios de validación sin alterar la lógica principal.

---

## 7. Clase `HistoriApp` como **Controlador MVC**

La clase `HistoriApp` actúa como **controlador** dentro del patrón **Modelo-Vista-Controlador (MVC)**, coordinando la interacción entre el modelo (dominio), las vistas (GUI) y los servicios.

- **Propósito:** mantener una arquitectura organizada y desacoplada.
- **Ventaja:** facilita el mantenimiento y la comprensión general del flujo de la aplicación.

---

## 8. Patrón **Singleton** en `GestorGUI`

La clase `GestorGUI` aplica el patrón **Singleton** y centraliza la gestión de temas de colores y estilos visuales. Esto permite asegurar una **consistencia gráfica** entre todas las ventanas y componentes.

- **Propósito:** tener una única instancia global accesible para toda la interfaz.
- **Ventaja:** uniformidad visual y facilidad para aplicar cambios de estilo.

---

## Conclusión

El uso consciente de estos patrones ha permitido construir una aplicación modular, extensible y fácilmente testeable. Su correcta aplicación mejora no solo la calidad técnica del código, sino también la experiencia del equipo de desarrollo y de los usuarios finales.
