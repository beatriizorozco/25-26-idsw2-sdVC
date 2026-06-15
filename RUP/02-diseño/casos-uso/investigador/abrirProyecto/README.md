# FUNIBER > Investigador > abrirProyecto > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirProyecto/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirProyecto/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirProyecto/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la apertura de un proyecto visible para el Investigador autenticado.

## Diagrama de secuencia

|![Diseño: abrirProyecto()](/images/RUP/02-diseño/casos-uso/investigador/abrirProyecto/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **ProyectoDetallePage**: Presenta la visión permitida del proyecto.
- **ProyectoController**: Expone `GET /api/proyectos/me/{id}`.
- **SesionService**: Resuelve al Investigador autenticado.
- **ProyectoService**: Comprueba visibilidad antes de componer el detalle.
- **ProyectoRepository**, **InvestigadorRepository** y **EntregableRepository**: Recuperan únicamente información activa y visible.

## Decisiones de Diseño

- La autorización se comprueba en servidor con la identidad de sesión.
- Un proyecto ajeno, archivado o inexistente responde `404 Not Found` para no revelar información fuera del alcance.
- El detalle no ofrece acciones exclusivas del Coordinador.
- Solo se muestran equipo y entregables visibles para el Investigador.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirProyecto/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/abrirProyecto/README.md)
