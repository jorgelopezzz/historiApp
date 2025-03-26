# Casos de uso

### 1. Acceso de usuarios

#### 1.1 Registrar un nuevo usuario
**Resumen:** El usuario proporciona sus datos para registrarse en el sistema.

**Actores:** 
-Usuario.
-Sistema

**Precondiciones:**
- El usuario no debe estar registrado previamente.

**Flujo principal:**
1. En la pantalla de inicio el usuario accede a la opción de registro.
2. El sistema muestra un formulario de registro.
3. El usuario introduce sus datos requeridos (nombre, correo, contraseña, rol, etc.).
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

**Actores:** 
-Usuario.
-Sistema.

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

#### 1.3 Cambiar información de perfil de usuario
**Resumen:** El usuario proporciona sus datos para registrarse en el sistema.

**Actores:** 
-Usuario.
-Sistema.

**Precondiciones:**
- El usuario debe estar registrado en el sistema.

**Flujo principal:**
1. El usuario accede a la configuración de perfil.
2. El sistema muestra la pantalla de configuración de perfil.
3. El usuario introduce sus datos requeridos (nombre, correo, contraseña, etc.).
4. El usuario selecciona una imagen desde su dispositivo o introduce un nuevo saludo.
5. El sistema guarda los cambios.

**Postcondiciones:**
- La imagen de perfil del usuario se actualiza correctamente.
- El saludo del usuario se actualiza correctamente.

### 3. Gestión de contenidos

#### 3.1 Realizar un curso
**Resumen:** El estudiante selecciona un curso y elige una estrategia de aprendizaje para completarlo. Una vez cursados los contenidos que crea conveniente, el estudiante puede salir del curso.

**Actores:** 
- Estudiante.
- Sistema

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

**Postcondiciones:**
- Se guarda el progreso del usuestudianteario en el curso.

#### 3.2 Realizar bloque de contenidos
**Resumen:** El estudiante accede a un bloque de contenido dentro de un curso y comienza a realizar tareas.

**Actores:** 
- Estudiante.
- Sistema

**Precondiciones:**
- El usuario debe estar realizando un curso.

**Flujo principal:**
1. El estudiante selecciona un bloque de contenidos.
2. El estudiante completa todas las tareas del curso.
3. El estudiante sale del bloque de contenidos.

**Flujo alternativo:**
- 2b. Si el estudiante no completa el bloque de contenidos, el progreso en dicho bloque se perderá.

**Postcondiciones:**
- El sistema marca el bloque de contenidos como completado.

### 5. Administración de cursos

#### 5.1 Importar un curso

**Resumen:** El profesor sube un nuevo curso.

**Actores:**  
- Profesor.
- Sistema

**Precondiciones:**  
- El profesor debe estar registrado e identificado en el sistema.  

**Flujo principal:**  
1. El profesor accede a la sección de creación de cursos.  
2. El profesor sube un archivo `.json` del curso.  

**Flujos alternativos:**  
- 3a En caso de subirse un curso que ya existe, el sistema muestra una solicitud de confirmación al usuario.  
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