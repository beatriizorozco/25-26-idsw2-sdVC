# FUNIBER > Coordinador > abrirPanelPrincipal > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirPanelPrincipal/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirPanelPrincipal/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la obtención y presentación del panel principal del Coordinador. La API recupera el rol de la sesión activa y devuelve exclusivamente las acciones autorizadas para este actor.

## Diagrama de secuencia

|![Diseño: abrirPanelPrincipal()](/images/RUP/02-diseño/casos-uso/coordinador/abrirPanelPrincipal/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **PanelPrincipalPage**: Solicita y presenta las acciones del panel.
- **PanelPrincipalController**: Expone `GET /api/panel-principal`.
- **SesionService**: Recupera el usuario y el rol activos.
- **PanelPrincipalService**: Devuelve las acciones habilitadas para `COORDINADOR`.

## Decisiones de Diseño

- El panel no se persiste como entidad; se calcula desde el rol activo.
- El Coordinador recibe acceso a convocatorias y operaciones globales.
- La API devuelve códigos, etiquetas y rutas para que el frontend pueda presentar la navegación.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirPanelPrincipal/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirPanelPrincipal/README.md)
