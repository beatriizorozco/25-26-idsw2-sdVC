# FUNIBER > Coordinador > abrirProyecto > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirProyecto/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirProyecto/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la apertura directa de un proyecto seleccionado y la composición de su información, equipo y entregables.

## Diagrama de secuencia

|![Diseño: abrirProyecto()](/images/RUP/02-diseño/casos-uso/coordinador/abrirProyecto/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **ProyectoDetallePage**: Presenta el proyecto abierto y sus acciones.
- **ProyectoController**: Expone `GET /api/proyectos/{id}`.
- **SesionService**: Exige una sesión de Coordinador.
- **ProyectoService**: Compone el detalle completo.
- **ProyectoRepository**, **InvestigadorRepository** y **EntregableRepository**: Recuperan proyecto, equipo y entregables.

## Decisiones de Diseño

- La selección abre directamente el proyecto, sin repetir el listado.
- El Coordinador recibe la visión global del proyecto.
- Un proyecto inexistente devuelve `404 Not Found`.
- El detalle habilita las navegaciones definidas desde `PROYECTO_ABIERTO`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirProyecto/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirProyecto/README.md)
