# FUNIBER > Coordinador > iniciarSesion > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/iniciarSesion/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/iniciarSesion/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/iniciarSesion/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/iniciarSesion/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Analizar la autenticación de una persona cuyo rol aún no se conoce. El diagrama comienza con `UsuarioNoAutenticado`; el diagrama de contexto conserva `SESION_CERRADA` como estado previo. Si las credenciales son correctas, se crea la sesión y se abre `PANEL_PRINCIPAL_ABIERTO`. Si son incorrectas, la vista presenta el error y permite reintentar.

## Diagrama de colaboración

|![Análisis: iniciarSesion()](/images/RUP/01-analisis/casos-uso/coordinador/iniciarSesion/iniciarSesion-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

## Clases de análisis identificadas

### UsuarioNoAutenticado (Actor)
- Solicita acceder al sistema antes de que se conozca su rol.

### IniciarSesionView (Boundary)
- Recibe `iniciarSesion()` desde `SESION_CERRADA`.
- Recibe `introducirCredenciales(usuario, contrasena)` de `UsuarioNoAutenticado`.
- Presenta el error cuando las credenciales son incorrectas.
- Abre el panel principal cuando la autenticación es correcta.

### IniciarSesionController (Control)
- Coordina la autenticación.
- Solicita validar las credenciales.
- Crea la sesión cuando obtiene un usuario válido.

### UsuarioRepository, Usuario y Sesion (Entity)
- `UsuarioRepository` proporciona `validarCredenciales(usuario, contrasena) : Usuario`.
- `Usuario` representa al usuario autenticado.
- `Sesion` proporciona `crearSesion(usuario) : Sesion`.

## Flujo de colaboración

1. `SESION_CERRADA` -> `IniciarSesionView.iniciarSesion()`.
2. `UsuarioNoAutenticado` -> `IniciarSesionView.introducirCredenciales(usuario, contrasena)`.
3. `IniciarSesionView` -> `IniciarSesionController.autenticar(usuario, contrasena)`.
4. `IniciarSesionController` -> `UsuarioRepository.validarCredenciales(usuario, contrasena)`.
5. Si las credenciales son incorrectas, `IniciarSesionController` -> `IniciarSesionView.presentarCredencialesIncorrectas()` y se permite reintentar.
6. Si las credenciales son correctas, `IniciarSesionController` -> `Sesion.crearSesion(usuario)`.
7. `IniciarSesionView` -> `PANEL_PRINCIPAL_ABIERTO`.

## Decisiones de análisis

- El actor es `UsuarioNoAutenticado` porque el sistema aún no conoce su rol.
- El diagrama representa tanto las credenciales correctas como las incorrectas.
- La salida exitosa es `PANEL_PRINCIPAL_ABIERTO`.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/iniciarSesion/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
