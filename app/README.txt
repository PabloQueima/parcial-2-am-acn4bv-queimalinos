Plataforma de Entrenamiento — Aplicaciones Móviles

Parcial 2 – Android Studio (Java)
Alumno: Pablo Queimaliños — ACN4BV
Materia: Aplicaciones Móviles – Escuela Da Vinci

1. Descripción general
Este proyecto corresponde a la segunda instancia del parcial de la materia Aplicaciones Móviles.
La aplicación está desarrollada en Android Studio con Java y forma parte de una plataforma de entrenamiento que, 
en etapas posteriores, se integrará con la versión web del mismo sistema.

El objetivo de esta entrega es implementar:
Autenticación real mediante Firebase Auth
Navegación entre múltiples pantallas
Comportamientos dinámicos
Uso de layouts adecuados (ConstraintLayout + LinearLayout)
Interacción real del usuario
Manejo correcto de recursos (strings, colores, dimensiones)
Envío de datos entre actividades mediante Intent + extras

2. Objetivos cumplidos
Funcionales
Login real con Firebase (email + password)
Registro de usuarios mediante Firebase
Acceso a pantalla principal solo con usuario autenticado
Logout con cierre de sesión real
Visualización de sesiones de entrenamiento
Despliegue dinámico de ejercicios por sesión
Estado completado / no completado para cada ejercicio
Actualización visual y de progreso dinámico
Pasaje del email del usuario autenticado a la pantalla principal

Técnicos
Uso de ConstraintLayout (LoginActivity)
Uso intensivo de LinearLayout horizontal y vertical
Uso de Button, TextView, CheckBox
Manejo de eventos: clicks, toggles, listeners
Comportamiento dinámico: cambios de color, tachado, contadores
Navegación entre actividades con Intent
Uso de Intent extras para enviar datos
Integración con Firebase Auth
Organización de recursos en: strings.xml, colors.xml, dimens.xml
Múltiples commits organizados y convenciones claras

3. Estructura del proyecto
app/
 ├── java/com/example/parcial_1_am_acn4bv_queimalinos/
 │     ├── LoginActivity.java
 │     ├── RegisterActivity.java
 │     ├── MainActivity.java
 │     ├── models/
 │     │     ├── Ejercicio.java
 │     │     └── Sesion.java
 │
 └── res/
       ├── layout/
       │     ├── activity_login.xml
       │     ├── activity_register.xml
       │     ├── activity_main.xml
       ├── values/
       │     ├── strings.xml
       │     ├── colors.xml
       │     └── dimens.xml
       └── drawable/
             ├── logo.png
             └── fondo.png

4. Pantallas y flujo de uso
4.1. LoginActivity (pantalla de inicio)
Layout: ConstraintLayout
Responsabilidad:
Permitir que el usuario ingrese con email y password utilizando FirebaseAuth.

Componentes principales:
EditText email
EditText password
Button Ingresar
Button Registrarme

Flujo:
El usuario ingresa email y contraseña.
Firebase valida credenciales.
Si es correcto → navegación a MainActivity
Se envía el email mediante Intent + extras.

4.2. RegisterActivity
Layout: LinearLayout vertical
Responsabilidad:
Registrar usuarios nuevos mediante Firebase Auth.

Flujo:
Usuario ingresa email y contraseña.
Se ejecuta createUserWithEmailAndPassword.
Si es correcto → navegación a MainActivity
Se envía el email mediante Intent extras.

4.3. MainActivity (pantalla principal de entrenamiento)
Layout: LinearLayout
Responsabilidad:
Mostrar las sesiones disponibles y los ejercicios de cada una.

Funcionalidades:
Mostrar mensaje “Bienvenido <email>”
Renderizar dinámicamente una lista de sesiones
Expandir/contraer ejercicios
Checkbox por ejercicio

Cambios visuales:
Fondo verde cuando está completado
Texto tachado

Actualización del contador:
“X de Y ejercicios completados”

Evento adicional:
Botón de logout, que:
ejecuta FirebaseAuth.getInstance().signOut()
redirige a LoginActivity

5. Comportamiento dinámico implementado
Checkbox que cambia estado y estilo visual de la tarjeta
Texto tachado al completar ejercicio
Contador por sesión que se actualiza en tiempo real
Despliegue / ocultamiento dinámico de ejercicios
Navegación entre actividades
Autenticación real contra Firebase

6. Pasaje de datos entre activities
Se envía el email del usuario desde Login/Register hacia MainActivity:
Intent i = new Intent(this, MainActivity.class);
i.putExtra("usuarioEmail", email);
startActivity(i);

En MainActivity:
String email = getIntent().getStringExtra("usuarioEmail");
Se usa para personalizar la pantalla con un mensaje de bienvenida.

7. Firebase Auth
La aplicación utiliza:
implementation 'com.google.firebase:firebase-auth:22.3.1'
apply plugin: 'com.google.gms.google-services'

Operaciones implementadas:
Login (signInWithEmailAndPassword)
Registro (createUserWithEmailAndPassword)
Validación de sesión activa
Logout

8. Diseño y recursos
Paleta de colores
Celeste: #05A3CB
Lila: #BB81B6
Azul oscuro: #15114D
Azul intermedio: #0C3264
Blanco: #FFFFFF

Todos los textos, colores y tamaños se administran desde:
strings.xml
colors.xml
dimens.xml

9. Requerimientos pendientes descartados
La entrega actual no incluye:
Descarga de imágenes desde URL
Contenido multimedia remoto
La exclusión es intencional y por alcance definido.