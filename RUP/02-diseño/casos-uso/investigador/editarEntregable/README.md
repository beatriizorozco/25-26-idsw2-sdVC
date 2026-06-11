# FUNIBER > Investigador > editarEntregable > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarEntregable/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/editarEntregable/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la actualización de un entregable propio por el Investigador.

## Diagrama de secuencia

|![Diseño: editarEntregable()](/images/RUP/02-diseño/casos-uso/investigador/editarEntregable/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Decisiones de Diseño

- La consulta `obtenerActivoPorIdYAutor` protege la operación desde el servidor.
- Un participante que no sea autor puede consultar el entregable, pero no editarlo.
- Un archivo nuevo se conserva como versión adicional.
- Cancelar mantiene el detalle sin cambios.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarEntregable/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/editarEntregable/README.md)
