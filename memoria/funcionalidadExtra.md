# Funcionalidad extra de HistoriApp

## Caso de uso adicional

Como parte de la funcionalidad adicional de **HistoriApp**, se ha añadido un **caso de uso adicional** que permite a los estudiantes dejar **reseñas sobre los cursos**. Esta herramienta busca fomentar una mejora continua en la calidad del contenido y en la experiencia educativa de la aplicación. Se desarrolló en el issue #69.

### Detalles

Una vez que un estudiante ha cursado parcial o totalmente un curso, tiene la posibilidad de evaluarlo mediante dos vías:

- **Valoración numérica**: El estudiante puede asignar una puntuación del 0 al 10 al curso.
- **Comentario**: El estudiante puede escribir una reseña textual expresando su opinión, recomendaciones o sugerencias sobre el contenido, estructura o utilidad del curso.

## Otras características adicionales

Como adiciones al enunciado de la práctica, hemos desarrollado una ventana de estadísticas globales que permiten a los usuarios ver su posición en un ranking basado en su desempeño dentro de la aplicación. Este ranking se genera a partir de un coeficiente calculado en función de diversas métricas que reflejan la actividad del estudiante.

### Detalles

La aplicación calcula una **puntuación numérica** para cada estudiante, basada en los siguientes factores:

1. **Cursos Completados**: El número de cursos que el estudiante ha finalizado con éxito.
2. **Bloques Completados**: La cantidad de bloques de contenidos dentro de los cursos que el estudiante ha completado.
3. **Puntuaciones Académicas**: Las calificaciones obtenidas por el estudiante en los diferentes bloques de contenidos.
4. **Tiempo de Uso**: La cantidad de tiempo que el estudiante ha pasado en la aplicación, lo que refleja su dedicación y participación.

Esta puntuación es utilizada para generar un ranking global de estudiantes, donde los usuarios pueden ver su posición comparada con la de otros. Los estudiantes con mayores puntuaciones aparecen en las primeras posiciones, incentivando la participación activa y el progreso en la plataforma.

La tabla de ranking global se presenta con los siguientes campos para cada estudiante:

- **Posición**: El puesto del estudiante en el ranking basado en el coeficiente calculado.
- **Nombre de Usuario**: El identificador único del estudiante.
- **Puntuación Total**: La puntuación que resulta de la combinación de los factores anteriores.
