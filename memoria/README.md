# Documentación de la Aplicación

Este directorio contiene la documentación detallada sobre los aspectos fundamentales del diseño y funcionamiento de la aplicación. A continuación, se describen los elementos disponibles en esta carpeta y su propósito.

## Memoria del Proyecto

El archivo **`memoria.pdf`** contiene la documentación principal del proyecto, donde se detallan:

- **Casos de uso**: Se describen los diferentes escenarios en los que los usuarios interactúan con la aplicación, incluyendo sus objetivos y los flujos de trabajo asociados.
- **Modelo del dominio**: Se presenta una representación conceptual de los elementos clave del sistema y sus relaciones, proporcionando una base estructurada para el desarrollo del software.

> 📄 [memoria.pdf](./memoria.pdf)

## Casos de uso

### 1. Acceso de usuarios

#### 1.1 Registrar un nuevo usuario
**Resumen:** El usuario proporciona sus datos para registrarse en el sistema.

**Actores:** Usuario.

**Precondiciones:**
- El usuario no debe estar registrado previamente.

**Flujo principal:**
1. En la pantalla de inicio el usuario accede a la opción de registro.
2. El sistema muestra un formulario de registro.
3. El usuario introduce sus datos requeridos (nombre, correo, contraseña, etc.).
4. El sistema valida la información proporcionada.
5. El sistema crea la cuenta del usuario y lo redirige a la pantalla principal.

**Flujos alternativos:**
- 4a. Si el correo ya está registrado, el sistema muestra un mensaje de error y solicita otro correo.
- 4b. Si la contraseña no se introduce correctamente por segunda vez, el sistema informa al usuario y la solicita de nuevo.
- 4c. Si falta algún campo, el sistema informa al usuario y pide que se introduzcan los datos que falten.

**Postcondiciones:**
- El usuario queda registrado en el sistema y puede iniciar sesión.

#### 1.2 Iniciar sesión de usuario
**Resumen:** El usuario ingresa sus credenciales para acceder al sistema.

**Actores:** Usuario.

**Precondiciones:**
- El usuario debe estar registrado.

**Flujo principal:**
1. El sistema muestra un formulario de login.
2. El usuario introduce su nombre de usuario y contraseña.
3. El sistema valida las credenciales.
4. El sistema permite el acceso y redirige al usuario a la pantalla principal.

**Flujos alternativos:**
- 4a. Si las credenciales son incorrectas, el sistema muestra un mensaje de error y solicita reintentar.

**Postcondiciones:**
- El usuario accede correctamente a su cuenta.

#### 1.3 Cambiar imagen de usuario
**Resumen:** El usuario actualiza su imagen de perfil.

**Actores:** Usuario.

**Precondiciones:**
- El usuario debe estar identificado en el sistema.

**Flujo principal:**
1. El usuario accede a la configuración de perfil.
2. El sistema muestra la pantalla de configuración de perfil.
3. El usuario selecciona una imagen desde su dispositivo.
4. El sistema actualiza la imagen de perfil.

**Postcondiciones:**
- La imagen de perfil del usuario se actualiza correctamente.

#### 1.4 Cambiar saludo de usuario
**Resumen:** El usuario personaliza su saludo en el perfil.

**Actores:** Usuario.

**Precondiciones:**
- El usuario debe estar identificado en el sistema.

**Flujo principal:**
1. El usuario accede a la configuración de perfil.
2. El sistema muestra la opción de editar el saludo.
3. El usuario introduce un nuevo saludo.
4. El sistema guarda los cambios.

**Postcondiciones:**
- El saludo del usuario se actualiza correctamente.

### 2. Vista de ranking general

#### 2.1 Visualizar ranking general de puntuación
**Resumen:** El usuario consulta el ranking de puntuaciones de cursos y bloques.

**Actores:** Usuario.

**Precondiciones:**
- El usuario debe estar identificado en el sistema.

**Flujo principal:**
1. El usuario accede a la sección de ranking.
2. El sistema muestra la lista de puntuaciones organizadas.

**Postcondiciones:**
- El usuario visualiza las posiciones generales.

### 3. Gestión de contenidos

#### 3.1 Realizar un curso
**Resumen:** El usuario selecciona un curso y elige una estrategia de aprendizaje para completarlo.

**Actores:** Usuario.

**Precondiciones:**
- El usuario debe estar identificado en el sistema.

**Flujo principal:**
1. El sistema muestra la lista de cursos disponibles.
2. El usuario selecciona un curso.
3. El sistema muestra las estrategias de aprendizaje disponibles (secuencial, espaciada, aleatoria, etc.).
4. El usuario selecciona una estrategia.
5. El sistema muestra los bloques de contenido del curso.

**Postcondiciones:**
- El curso queda registrado como "en progreso".

#### 3.3 Realizar bloque de contenidos
**Resumen:** El usuario accede a un bloque de contenido dentro de un curso y comienza a realizar tareas.

**Actores:** Usuario.

**Precondiciones:**
- El usuario debe estar realizando un curso.

**Flujo principal:**
1. El usuario selecciona un bloque de contenidos.

**Postcondiciones:**
- El sistema comienza a mostrar las tareas del bloque.

#### 3.4 Salir de bloque de contenidos
**Resumen:** El usuario sale de un bloque de contenidos en curso.

**Actores:** Usuario.

**Precondiciones:**
- El usuario debe estar cursando un bloque de contenidos.
- El usuario no ha terminado el bloque de contenidos.

**Flujo principal:**
1. El usuario cierra el bloque.
2. El sistema muestra una petición de confirmación de cierre del bloque. Se avisa al usuario de que, en caso de confirmar el cierre, se perderá el progreso del bloque de contenidos.
3. El sistema muestra los bloques de contenido del curso.

**Flujo alternativo:**
- 2b. Si el usuario rechaza la confirmación de cierre, el sistema retorna al bloque de contenido.

#### 3.5 Completar bloque de contenidos
**Resumen:** El usuario completa un bloque de contenidos.

**Actores:** Usuario.

**Precondiciones:**
- El usuario debe estar cursando un bloque de contenidos.
- El usuario ha terminado el bloque de contenidos.

**Flujo principal:**
1. El usuario completa la última tarea del bloque.
2. El sistema muestra un mensaje para notificar al usuario de que ha completado el bloque.
3. El sistema vuelve a la pantalla de bloques de contenido del curso.

#### 3.6 Cerrar curso
**Resumen:** El usuario cierra un curso en progreso, registrando su estado actual.

**Actores:** Usuario.

**Precondiciones:**
- El usuario debe estar realizando un curso.

**Flujo principal:**
1. El usuario selecciona la opción de cerrar curso.
2. El sistema guarda el estado actual del curso.

**Postcondiciones:**
- El sistema almacena el estado del curso.
- Se actualizan las estadísticas del usuario.

### 4. Interacción de tareas

#### 4.1 Realizar tarea
**Resumen:** El usuario realiza una tarea dentro de un bloque de contenidos. En el caso de las preguntas, el usuario responde y, en el caso de las flashcards, el usuario las voltea.

**Actores:** Usuario.

**Precondiciones:**
- El usuario debe estar realizando un bloque de contenidos.

**Flujo principal:**
1. El sistema muestra una tarea.
2. El usuario introduce una respuesta o interactúa con una flashcard.

**Flujo alternativo:**
- 2a En caso de ser una pregunta, el sistema verifica la respuesta y muestra retroalimentación al usuario.
- 2b En caso de ser una flashcard, la voltea.

#### 4.2 Avanzar a la siguiente tarea

**Resumen:** El usuario pasa a la siguiente tarea dentro de un curso.

**Actores**
- Usuario.

**Precondiciones**
- El usuario debe estar en un bloque de contenido.

**Flujo principal**
1. El usuario avanza a la siguiente tarea.

**Postcondiciones**
- El usuario continúa con la siguiente tarea en la secuencia.
- Si se avanza en la última tarea, el sistema termina el bloque y muestra la lista de bloques de contenidos del curso.

### 5. Vista de estadísticas de usuario

#### 5.1 Visualizar estadísticas

**Resumen:** El usuario puede ver sus estadísticas de uso de la aplicación y de rendimiento en cursos y bloques de contenido.

**Actores:**  
- Usuario.

**Precondiciones:**  
- El usuario debe estar registrado e identificado en el sistema.

**Flujo principal:**  
1. El usuario accede a la sección de estadísticas.  
2. El sistema muestra las estadísticas del usuario:  
   - Cursos completados.  
   - Bloques completados.  
   - Puntuación obtenida en preguntas.  
   - Tiempo de uso de la aplicación.  
   - Tiempo medio de uso diario de la aplicación.  
   - Mejor racha.  

**Postcondiciones:**  
- El usuario visualiza sus estadísticas.

### 6. Administración de cursos

## 6.1 Crear o actualizar curso

**Resumen:** El usuario sube un nuevo curso o actualiza uno existente.

**Actores:**  
- Usuario.

**Precondiciones:**  
- El usuario debe estar registrado e identificado en el sistema.  
- Para actualizar un curso, debe existir previamente en el sistema.  

**Flujo principal:**  
1. El usuario accede a la sección de creación de cursos.  
2. El usuario sube un archivo `.json` del curso.  
3. El sistema almacena el curso.  

**Flujos alternativos:**  
- 3a En caso de subirse un curso que ya existe, el sistema muestra una solicitud de confirmación al usuario.  
- 4a Si se confirma la solicitud, se sobrescribe el curso.  

**Postcondiciones:**  
- El sistema almacena el curso actualizado o nuevo.


## Diagrama de Estados

Como complemento a la memoria, se incluye un **diagrama de estados** que ilustra el comportamiento dinámico de la aplicación. Este diagrama representa:

- Los distintos estados de la aplicación en función de la interacción del usuario.
- La transición entre pantallas y funcionalidades en respuesta a los diferentes eventos generados por el usuario o el sistema.

El diagrama permite comprender mejor la lógica interna del software y su estructura de navegación.

## Diseño de la Interfaz Gráfica

Además, se presentan ejemplos de **estructuras escogidas para el diseño de la interfaz gráfica**, los cuales reflejan las decisiones tomadas en cuanto a usabilidad, patrones de diseño, distribución de componentes y experiencia del usuario.

Estos ejemplos incluyen:

- Disposición de los elementos visuales en la aplicación.
- Relación entre las diferentes ventanas y componentes interactivos.
- Aplicación de patrones de diseño concretos.
