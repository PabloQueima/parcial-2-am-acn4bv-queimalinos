Plataforma de Entrenamiento — Aplicaciones Móviles

Parcial 2 – Android Studio (Java)
Alumno: Pablo Queimaliños — ACN4BV
Materia: Aplicaciones Móviles – Escuela Da Vinci

1. Descripción general

Este proyecto corresponde a la segunda instancia del parcial de la materia Aplicaciones Móviles.
La aplicación está desarrollada en Android Studio con Java y forma parte de una plataforma de entrenamiento que,
en etapas posteriores, se integrará con la versión web del mismo sistema.

Objetivos de esta entrega:
Implementar autenticación real mediante Firebase Auth.
Navegación entre múltiples pantallas.
Comportamientos dinámicos interactivos.
Uso adecuado de layouts (ConstraintLayout y LinearLayout).
Manejo correcto de recursos (strings, colores, dimensiones).
Envío de datos entre actividades mediante Intent + extras.

2. Objetivos cumplidos

Funcionales
Login real con Firebase (email + password).
Registro de usuarios mediante Firebase.
Acceso a pantalla principal solo con usuario autenticado.
Logout con cierre de sesión real.
Visualización de sesiones de entrenamiento.
Despliegue dinámico de ejercicios por sesión.
Estado completado / no completado para cada ejercicio con actualización visual y de progreso.
Pasaje del email del usuario autenticado a la pantalla principal.

Técnicos
Uso de ConstraintLayout y LinearLayout (vertical y horizontal).
Componentes: Button, TextView, CheckBox.
Manejo de eventos: clicks, toggles y listeners.
Cambios dinámicos de estilo visual: color de fondo, tachado de texto, contadores.
Navegación entre actividades con Intent y Intent extras.
Integración completa con Firebase Auth.
Organización de recursos en strings.xml, colors.xml, dimens.xml.
Múltiples commits organizados y convenciones claras.

3. Estructura del proyecto
app/
 ├── java/com/example/parcial_2_am_acn4bv_queimalinos/
 │     ├── LoginActivity.java
 │     ├── RegisterActivity.java
 │     ├── MainActivity.java
 │     ├── DetalleEjercicioActivity.java
 │     ├── models/
 │     │     ├── Ejercicio.java
 │     │     └── Sesion.java
 │
 └── res/
       ├── layout/
       │     ├── activity_login.xml
       │     ├── activity_register.xml
       │     ├── activity_main.xml
       │     ├── activity_detalle_ejercicio.xml
       ├── values/
       │     ├── strings.xml
       │     ├── colors.xml
       │     └── dimens.xml
       └── drawable/
             ├── logo.png
             ├── fondo.png
             └── placeholder.png

4. Pantallas y flujo de uso

4.1. LoginActivity
Layout: ConstraintLayout
Responsabilidad: Permitir que el usuario ingrese con email y password usando Firebase Auth.
Componentes principales:
EditText email
EditText password
Button Ingresar
Button Registrarme

Flujo:
El usuario ingresa email y contraseña.
Firebase valida credenciales.
Si es correcto → navegación a MainActivity con el email enviado por Intent extras.

4.2. RegisterActivity
Layout: LinearLayout vertical
Responsabilidad: Registrar usuarios nuevos mediante Firebase Auth.
Flujo:
Usuario ingresa email y contraseña.
Se ejecuta createUserWithEmailAndPassword.
Si es correcto → navegación a MainActivity con email enviado por Intent extras.

4.3. MainActivity
Layout: LinearLayout
Responsabilidad: Mostrar las sesiones disponibles y los ejercicios de cada una.
Funcionalidades:
Mensaje “Bienvenido <email>”.
Renderizado dinámico de sesiones y ejercicios.
Checkbox por ejercicio con cambio visual: fondo verde y texto tachado al completar.
Contador dinámico “X de Y ejercicios completados”.
Botón de logout: cierra sesión y redirige a LoginActivity.

4.4. DetalleEjercicioActivity
Responsabilidad: Mostrar detalle de cada ejercicio con imagen de referencia.
Características:
Imágenes cargadas desde URLs preseleccionadas de Unsplash.
Glide ajusta automáticamente la imagen al ImageView con centerCrop.
Evita resultados ambiguos usando fotos seleccionadas manualmente.

5. Comportamiento dinámico implementado
Checkbox que cambia estado y estilo visual de la tarjeta.
Texto tachado al completar ejercicio.
Contador por sesión que se actualiza en tiempo real.
Despliegue y ocultamiento dinámico de ejercicios.
Navegación entre actividades con datos de usuario.
Autenticación real contra Firebase.

6. Pasaje de datos entre activities
Ejemplo de envío de email:
Intent i = new Intent(this, MainActivity.class);
i.putExtra("usuarioEmail", email);
startActivity(i);
Recepción en MainActivity:
String email = getIntent().getStringExtra("usuarioEmail");
Se utiliza para mostrar mensaje personalizado al usuario.

7. Firebase Auth
Dependencias:
implementation 'com.google.firebase:firebase-auth:22.3.1'
apply plugin: 'com.google.gms.google-services'

Operaciones implementadas:
Login: signInWithEmailAndPassword
Registro: createUserWithEmailAndPassword
Validación de sesión activa
Logout: FirebaseAuth.getInstance().signOut()

8. Diseño y recursos
Paleta de colores:
Celeste: #05A3CB
Lila: #BB81B6
Azul oscuro: #15114D
Azul intermedio: #0C3264
Blanco: #FFFFFF

Todos los textos, colores, temas y tamaños se administran desde:
strings.xml
colors.xml
dimens.xml
themes.xml

9. Consideraciones finales
La aplicación combina autenticación real, interacción dinámica y visualización de ejercicios con imágenes preseleccionadas.
Todas las imágenes críticas provienen de Unsplash, garantizando consistencia y relevancia.
La arquitectura del proyecto permite escalar fácilmente para futuras integraciones con la versión web del sistema.
