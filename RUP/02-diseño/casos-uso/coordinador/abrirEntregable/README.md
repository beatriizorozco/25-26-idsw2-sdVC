# FUNIBER > Coordinador > abrirEntregable > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirEntregable/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirEntregable/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la apertura de un entregable activo, sus archivos y versiones por el Coordinador.

## Diagrama de secuencia

|![Diseño: abrirEntregable()](/images/RUP/02-diseño/casos-uso/coordinador/abrirEntregable/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **EntregableDetallePage**: Presenta el detalle y las acciones de gestión.
- **EntregableController**, **SesionService** y **EntregableService**: Autorizan y coordinan la consulta.
- **EntregableRepository** y **ArchivoRepository**: Recuperan el entregable, archivos y versiones.

## Decisiones de Diseño

- El Coordinador puede consultar, editar y retirar cualquier entregable activo.
- El detalle incorpora archivos y versiones para preservar la trazabilidad documental.
- Un entregable inexistente o retirado no se presenta en la gestión activa.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirEntregable/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirEntregable/README.md)
