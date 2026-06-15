# FUNIBER > Investigador > abrirEntregable > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirEntregable/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirEntregable/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirEntregable/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta de un entregable perteneciente a un proyecto visible para el Investigador.

## Diagrama de secuencia

|![Diseño: abrirEntregable()](/images/RUP/02-diseño/casos-uso/investigador/abrirEntregable/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Decisiones de Diseño

- El Investigador puede consultar entregables de proyectos en los que participa.
- El detalle devuelve `esAutor` para habilitar edición y retirada únicamente al autor.
- Los archivos y versiones son visibles para los participantes del proyecto.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirEntregable/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/abrirEntregable/README.md)
