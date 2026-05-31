# FUNIBER > Coordinador > cerrarSesion > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/cerrarSesion/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/cerrarSesion/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/cerrarSesion/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/cerrarSesion/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Analizar el cierre de sesión solicitado por el Coordinador. El caso comienza en `PANEL_PRINCIPAL_ABIERTO`, solicita confirmación y distingue entre cancelar o confirmar el cierre.

## Diagrama de colaboración

|![Análisis: cerrarSesion()](/images/RUP/01-analisis/casos-uso/coordinador/cerrarSesion/cerrarSesion-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

## Clases de análisis identificadas

### Coordinador (Actor)
- Solicita cerrar la sesión.
- Decide si confirma o cancela.

### CerrarSesionView (Boundary)
- Solicita confirmación antes de cerrar.
- Regresa a `PANEL_PRINCIPAL_ABIERTO` si el actor cancela.
- Presenta `SESION_CERRADA` si el actor confirma.

### CerrarSesionController (Control)
- Coordina la solicitud de confirmación.
- Solicita cerrar la sesión únicamente tras la confirmación.

### Sesion (Entity)
- `Sesion` proporciona `cerrarSesion()`.

## Flujo de colaboración

1. `PANEL_PRINCIPAL_ABIERTO` -> `CerrarSesionView.cerrarSesion()`.
2. `Coordinador` -> `CerrarSesionView.cerrarSesion()`.
3. `CerrarSesionView` -> `CerrarSesionController.solicitarConfirmacion()`.
4. `CerrarSesionController` -> `CerrarSesionView.presentarConfirmacion()`.
5. Si el actor cancela, `CerrarSesionView` -> `PANEL_PRINCIPAL_ABIERTO`.
6. Si el actor confirma, `CerrarSesionView` -> `CerrarSesionController.confirmarCierre()`.
7. `CerrarSesionController` -> `Sesion.cerrarSesion()`.
8. `CerrarSesionView` -> `SESION_CERRADA`.

## Decisiones de análisis

- La sesión solo se cierra tras la confirmación del actor.
- La cancelación y la confirmación son salidas distintas.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/coordinador/cerrarSesion/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
