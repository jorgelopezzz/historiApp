# Funcionalidad Extra de HistoriApp

## Descripción General

La funcionalidad extra de **HistoriApp** se centra en mostrar estadísticas globales que permiten a los usuarios ver su posición en un ranking basado en su desempeño dentro de la aplicación. Este ranking se genera a partir de un coeficiente calculado en función de diversas métricas que reflejan la actividad del estudiante.

## Detalles de la Funcionalidad

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
