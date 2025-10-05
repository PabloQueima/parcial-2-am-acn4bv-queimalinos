PLATAFORMA DE ENTRENAMIENTO

Parcial 1 – Aplicaciones Móviles
Alumno: Pablo Queimaliños
Comisión: ACN4BV
Materia: Aplicaciones Móviles
Escuela Da Vinci

************************************************************************************************************************************************************

Descripción general

Este proyecto corresponde a la primera entrega del parcial de la materia Aplicaciones Móviles, desarrollado en Android Studio con Java.
La aplicación forma parte de una plataforma de entrenamiento, que más adelante se integrará con la versión web desarrollada en paralelo.
El objetivo de esta etapa fue implementar una pantalla funcional del usuario cliente, respetando la estructura, diseño y comportamiento dinámico requeridos.

************************************************************************************************************************************************************

Objetivo de la entrega

El propósito de esta instancia fue construir una pantalla principal que permita al usuario:
Visualizar las distintas sesiones de entrenamiento disponibles.
Desplegar los ejercicios de cada sesión.
Marcar los ejercicios como completados, con cambios visuales y contador de progreso.

************************************************************************************************************************************************************

Estructura del proyecto

La aplicación está organizada según la convención estándar de Android Studio:
app/
 ├── java/com/example/parcial_1_am_acn4bv_queimalinos/
 │     ├── MainActivity.java
 │     ├── models/
 │     │     ├── Ejercicio.java
 │     │     └── Sesion.java
 │
 └── res/
       ├── layout/
       │     └── activity_main.xml
       ├── values/
       │     ├── strings.xml
       │     ├── colors.xml
       │     └── dimens.xml
       └── drawable/
             ├── logo.png
             └── fondo.png

************************************************************************************************************************************************************

Descripción funcional

La aplicación inicia mostrando el logo de la plataforma sobre un fondo personalizado.
Debajo se presenta la lista de sesiones disponibles.

Cada sesión tiene un botón para mostrar u ocultar sus ejercicios.
Los ejercicios se renderizan dinámicamente desde una lista de objetos Ejercicio, incluyendo su nombre, series, repeticiones y descripción.

Cada tarjeta de ejercicio incluye un CheckBox que permite marcarlo como completado.
Al hacerlo, se modifica el color de fondo y se tacha el texto, brindando feedback visual al usuario.
Además, se actualiza automáticamente el contador de progreso de la sesión, mostrando la cantidad de ejercicios completados sobre el total.

************************************************************************************************************************************************************

Diseño y recursos

Se respetó la paleta de colores definida previamente para la plataforma:
Celeste: #05A3CB
Lila: #BB81B6
Azul oscuro: #15114D
Azul intermedio: #0C3264
Blanco: #FFFFFF

Todos los textos, colores y dimensiones se manejan desde los archivos de recursos (strings.xml, colors.xml, dimens.xml), manteniendo una buena organización y facilitando futuras modificaciones.

************************************************************************************************************************************************************

Alcance de esta entrega

Para esta primera instancia, la aplicación no se conecta aún con la versión web ni utiliza datos externos.
Toda la información de sesiones y ejercicios se genera de forma mockeada desde el código.
En la próxima etapa se implementará la comunicación con la plataforma web, el manejo de imágenes específicas para cada ejercicio desde API y la persistencia de datos.
