# FUNIBER > Investigador > abrirEntregables > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirEntregables/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirEntregables/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta de entregables activos de un proyecto en el que participa el Investigador.

## Diagrama de secuencia

|![Diseño: abrirEntregables()](/images/RUP/02-diseño/casos-uso/investigador/abrirEntregables/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Decisiones de Diseño

- La consulta siempre recibe `proyectoId`.
- El servicio comprueba que el Investigador participa en el proyecto.
- Se muestran todos los entregables activos del proyecto visible, no solo los propios.
- La autoría limitará las acciones disponibles al abrir cada entregable.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirEntregables/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/abrirEntregables/README.md)
