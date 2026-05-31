# FUNIBER > Investigador > abrirPanelPrincipal > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirPanelPrincipal/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirPanelPrincipal/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirPanelPrincipal/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/abrirPanelPrincipal/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Analizar la presentación del panel principal del Investigador y de las acciones habilitadas para su rol. El panel se modela como vista de navegación, no como entidad persistente.

## Diagrama de colaboración

|![Análisis: abrirPanelPrincipal()](/images/RUP/01-analisis/casos-uso/investigador/abrirPanelPrincipal/abrirPanelPrincipal-analisis.svg)|
|-|
|Código fuente: [colaboracion.puml](colaboracion.puml)|

## Clases de análisis identificadas

### Investigador (Actor)
- Solicita abrir el panel principal.

### PanelPrincipalView (Boundary)
- Presenta las acciones habilitadas para el Investigador.
- Permite solicitar una colaboración disponible.

### AbrirPanelPrincipalController (Control)
- Obtiene las acciones disponibles a partir de la sesión.
- Solicita el rol activo y prepara la respuesta para la vista.

### Sesion (Entity)
- `Sesion` proporciona `obtenerRolActivo()`.

## Trazabilidad con el diagrama de contexto

El panel puede solicitarse como retorno desde `INVESTIGADORES_ABIERTOS`, `OPCIONES_CARGA_TRABAJO_ABIERTAS`, `OPCIONES_PERFIL_ABIERTO`, `MIS_PUBLICACIONES_ABIERTAS`, `PUBLICACIONES_ABIERTAS`, `RECOMPENSAS_ABIERTAS` y `PROYECTOS_ABIERTOS`. La salida visible es `PANEL_PRINCIPAL_ABIERTO`.

## Acciones disponibles

- `abrirOpcionesPerfil()`
- `abrirOpcionesCargaTrabajo()`
- `abrirProyectos()`
- `abrirInvestigadores()`
- `abrirMisPublicaciones()`
- `abrirPublicaciones()`
- `abrirRecompensas()`
- `cerrarSesion()`

## Decisiones de análisis

- El actor aparece porque inicia la conversación con la vista.
- La vista presenta exclusivamente las acciones permitidas para el rol activo.

## Referencias

- [Especificación detallada](/RUP/00-casos-uso/02-detalle/investigador/abrirPanelPrincipal/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
