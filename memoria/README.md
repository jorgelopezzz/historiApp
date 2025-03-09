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
**Resumen:** El usuario selecciona un curso y elige una estrategia de aprendizaje para completarlo. Una vez cursados los contenidos que crea conveniente, el usuario puede salir del curso.

**Actores:** Usuario.

**Precondiciones:**
- El usuario debe estar identificado en el sistema.

**Flujo principal:**
1. El sistema muestra la lista de cursos disponibles.
2. El usuario selecciona un curso.
3. El sistema muestra las estrategias de aprendizaje disponibles (secuencial, espaciada, aleatoria, etc.).
4. El usuario selecciona una estrategia.
5. El sistema muestra los bloques de contenido del curso.
6. El usuario cursa los bloques de contenidos que cree conveniente.
7. El usuario sale del curso.

**Postcondiciones:**
- Se guarda el progreso del usuario en el curso.

#### 3.2 Realizar bloque de contenidos
**Resumen:** El usuario accede a un bloque de contenido dentro de un curso y comienza a realizar tareas.

**Actores:** Usuario.

**Precondiciones:**
- El usuario debe estar realizando un curso.

**Flujo principal:**
1. El usuario selecciona un bloque de contenidos.
2. El usuario completa todas las tareas del curso.
3. El usuario sale del bloque de contenidos.

**Flujo alternativo:**
- 2b. Si el usuario no completa el bloque de contenidos, el progreso en dicho bloque se perderá.

**Postcondiciones:**
- El bloque de contenidos se marca como completado.

### 5. Vista de estadísticas de usuario

>[!NOTE]
>Este apartado cubre la funcionalidad adicional a desarrollar en el proyecto.

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
