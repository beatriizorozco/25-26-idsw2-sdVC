# FUNIBER > Coordinador > abrirEntregables > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirEntregables/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirEntregables/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta de entregables activos del proyecto actualmente abierto por el Coordinador.

## Diagrama de secuencia

|![Diseño: abrirEntregables()](/images/RUP/02-diseño/casos-uso/coordinador/abrirEntregables/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **EntregablesPage**: Presenta y filtra los entregables del proyecto.
- **EntregableController** y **SesionService**: Exponen la consulta y exigen rol Coordinador.
- **EntregableService**: Valida el proyecto y coordina el listado.
- **ProyectoRepository** y **EntregableRepository**: Recuperan el proyecto y sus entregables activos.

## Decisiones de Diseño

- El endpoint queda siempre acotado por `proyectoId`; no existe listado global de entregables.
- Solo se presentan entregables activos, manteniendo los retirados para trazabilidad.
- El filtrado se aplica dentro del proyecto seleccionado.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirEntregables/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirEntregables/README.md)
