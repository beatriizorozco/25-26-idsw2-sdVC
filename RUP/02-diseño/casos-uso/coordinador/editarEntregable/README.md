# FUNIBER > Coordinador > editarEntregable > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarEntregable/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarEntregable/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarEntregable/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la actualización de un entregable activo y la incorporación opcional de una nueva versión documental.

## Diagrama de secuencia

|![Diseño: editarEntregable()](/images/RUP/02-diseño/casos-uso/coordinador/editarEntregable/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Decisiones de Diseño

- El Coordinador puede actualizar cualquier entregable activo.
- Un nuevo archivo se añade como versión y no sustituye el histórico.
- Cancelar conserva `ENTREGABLE_ABIERTO` sin cambios.
- Los datos inválidos o entregables retirados no se actualizan.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarEntregable/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/editarEntregable/README.md)
