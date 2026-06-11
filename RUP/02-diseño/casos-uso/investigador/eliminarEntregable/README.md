# FUNIBER > Investigador > eliminarEntregable > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/eliminarEntregable/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/eliminarEntregable/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la retirada lógica de un entregable propio por el Investigador.

## Diagrama de secuencia

|![Diseño: eliminarEntregable()](/images/RUP/02-diseño/casos-uso/investigador/eliminarEntregable/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Decisiones de Diseño

- Solo el autor puede retirar su entregable.
- La retirada requiere confirmación y motivo.
- Se conservan autoría, proyecto, archivos y versiones.
- La comprobación de autoría se aplica también en servidor.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/eliminarEntregable/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/eliminarEntregable/README.md)
