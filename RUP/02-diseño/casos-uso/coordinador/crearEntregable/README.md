# FUNIBER > Coordinador > crearEntregable > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/crearEntregable/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/crearEntregable/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar el registro de un entregable dentro del proyecto abierto, con archivo inicial opcional.

## Diagrama de secuencia

|![Diseño: crearEntregable()](/images/RUP/02-diseño/casos-uso/coordinador/crearEntregable/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **CrearEntregableForm**: Captura datos y archivo opcional.
- **EntregableController**, **SesionService** y **EntregableService**: Autorizan, validan y crean.
- **ProyectoRepository**, **EntregableRepository** y **ArchivoRepository**: Validan el proyecto y persisten el entregable y su archivo.

## Decisiones de Diseño

- La creación se realiza dentro de un proyecto activo.
- Se impiden títulos activos duplicados dentro del mismo proyecto.
- El archivo inicial es opcional y queda asociado a la autoría del Coordinador.
- Una creación correcta devuelve `201 Created` y abre el entregable.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/crearEntregable/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/crearEntregable/README.md)
