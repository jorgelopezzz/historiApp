# Casos de uso

#### Actores
En los casos de uso del proyecto se utilizarán los actores **Profesor** y **Estudiante**. A nivel de aplicación, las diferencias son las siguientes:
- El rol de Estudiante se centra en el aprendizaje de contenidos y todo lo relativo a la realización de cursos académicos. 
- El rol de Profesor se centra en la importación de cursos para favorecer la biblioteca de la aplicación. 

Nótese que un miembro de la aplicación puede registrarse como Profesor y cursar contenidos, de cara a revisar su propio material o para aprender de otros profesores. En cambio, un Estudiante no podrá importar cursos. Así, en los casos de uso habrá situaciones donde un actor pueda ser tanto Profesor como Estudiante. Nos referiremos a dicho actor como Estudiante, entendiendo que el Profesor actuará como Estudiante a la hora de cursar contenidos. 

### 1. Acceso

#### 1.1 Registro
**Resumen:** El Estudiante/Profesor proporciona sus datos para registrarse en el sistema.

**Actores:** 
- Estudiante.
- Profesor

**Precondiciones:**
- El Estudiante/Profesor no debe estar registrado previamente.

**Flujo principal:**
1. En la pantalla de inicio el Estudiante/Profesor accede a la opción de registro.
2. El sistema muestra un formulario de registro.
3. El Estudiante/Profesor introduce sus datos requeridos (nombre, correo, contraseña, rol, etc.).
4. El sistema valida la información proporcionada.
5. El sistema crea la cuenta del Estudiante/Profesor y lo redirige a la pantalla principal.

**Flujos alternativos:**
- 4a. Si el correo o nombre ya está registrado, el sistema muestra un mensaje de error y solicita otro correo.
- 4b. Si la contraseña no se introduce correctamente por segunda vez, el sistema informa al Estudiante/Profesor y la solicita de nuevo.
- 4c. Si falta algún campo, el sistema informa al Estudiante/Profesor y pide que se introduzcan los datos que falten.

**Postcondiciones:**
- El Estudiante/Profesor queda registrado en el sistema y puede iniciar sesión.

#### 1.2 Inicio de sesión
**Resumen:** El Estudiante/Profesor ingresa sus credenciales para acceder al sistema.

**Actores:** 
- Estudiante.
- Profesor

**Precondiciones:**
- El Estudiante/Profesor debe estar registrado.

**Flujo principal:**
1. El sistema muestra un formulario de login.
2. El Estudiante/Profesor introduce su nombre y contraseña.
3. El sistema valida las credenciales.
4. El sistema permite el acceso y muestra la pantalla principal.
5. El sistema comienza a contar el tiempo de sesión del Estudiante/Profesor.

**Flujos alternativos:**
- 4a. Si las credenciales son incorrectas, el sistema muestra un mensaje de error y solicita reintentar.

**Postcondiciones:**
- El Estudiante/Profesor accede correctamente a su cuenta.

#### 1.3 Cierre de sesión
**Resumen:** El Estudiante/Profesor cierra su sesión para salir de la aplicación.

**Actores:** 
- Estudiante.
- Profesor

**Precondiciones:**
- El Estudiante/Profesor debe haber ingresado en la aplicación correctamente.

**Flujo principal:**
1. La ventana correspondiente muestra el botón de cerrar sesión.
2. El Estudiante/Profesor hace click en el botón.
3. El sistema devuelve al Estudiante/Profesor a la ventana de Log In y deja de contar tiempo de sesión.
4. El sistema actualiza las estadísticas de tiempo de uso de la aplicación, tiempo medio de uso diario de la aplicación y mejor racha.

**Postcondiciones:**
- El Estudiante/Profesor deja de tener una sesión activa.

#### 1.3 Cambiar información de perfil
**Resumen:** El Estudiante/Profesor proporciona sus datos para registrarse en el sistema.

**Actores:** 
- Estudiante.
- Profesor.

**Precondiciones:**
- El Estudiante/Profesor debe estar registrado en el sistema.

**Flujo principal:**
1. El Estudiante/Profesor accede a la configuración de perfil.
2. El sistema muestra la pantalla de configuración de perfil.
3. El Estudiante/Profesor introduce sus datos requeridos (nombre, correo, contraseña, etc.).
4. El Estudiante/Profesor selecciona una imagen desde su dispositivo o introduce un nuevo saludo.
5. El sistema guarda los cambios.

**Postcondiciones:**
- La imagen de perfil del Estudiante/Profesor se actualiza correctamente.
- El saludo del Estudiante/Profesor se actualiza correctamente.

> [!NOTE]
> Este caso de uso no ha sido obtenido directamente del caso de estudio, sino que ha sido introducido por el equipo.


### 2. Gestión de contenidos

#### 2.1 Realizar un curso
**Resumen:** El estudiante selecciona un curso y elige una estrategia de aprendizaje para completarlo. Una vez cursados los contenidos que crea conveniente, el estudiante puede salir del curso.

**Actores:** 
- Estudiante.

**Precondiciones:**
- El estudiante debe estar identificado en el sistema.

**Flujo principal:**
1. El sistema muestra la lista de cursos disponibles.
2. El estudiante selecciona un curso.
3. El sistema muestra las estrategias de aprendizaje disponibles (secuencial, espaciada, aleatoria, etc.).
4. El estudiante selecciona una estrategia.
5. El sistema muestra los bloques de contenido del curso.
6. El estudiante cursa los bloques de contenidos que cree conveniente.
7. El estudiante sale del curso.

**Flujo alternativo:**
- 6a. Si se han completado todos los bloques del curso, el sistema actualiza la estadística de cursos completados.
- 6b. Si un estudiante se sale de un bloque sin haberlo terminado, el progreso en dicho bloque no se guardará.

**Postcondiciones:**
- Se guarda el progreso del estudiante en el curso.

### 3. Administración de cursos

#### 3.1 Importar un curso

**Resumen:** El profesor sube un nuevo curso.

**Actores:**  
- Profesor.

**Precondiciones:**  
- El profesor debe estar registrado e identificado en el sistema.  

**Flujo principal:**  
1. El profesor accede a la sección de creación de cursos.  
2. El profesor sube un archivo `.json` del curso.  

**Flujos alternativos:**  
- 3a En caso de subirse un curso que ya existe, el sistema muestra una solicitud de confirmación al profesor.  
- 4a Si se confirma la solicitud, se sobrescribe el curso.  

**Postcondiciones:**  
- El sistema almacena el curso.

## Referencias de Arquitectura

La arquitectura de HistoriApp está documentada en detalle en los siguientes archivos:

- [Modelado de Dominio](modeladoDominio.md): Representa las entidades principales del sistema y sus relaciones.
- [Estructuras y Patrones de Diseño de la Interfaz Gráfica](modeloVista.md): Detalla la organización de los componentes visuales y patrones implementados.
- [Modelo de Estados y Transiciones](modeloEstados.md): Ilustra el flujo de navegación y las transiciones entre diferentes interfaces de la aplicación.
- [Casos de Uso](casosDeUso.md)
- [Documentación del Proyecto](README.md)

Estos documentos proporcionan una visión completa de los aspectos estructurales y dinámicos de la aplicación HistoriApp.