# FUNIBER > Coordinador > eliminarEntregable > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarEntregable/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarEntregable/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la retirada lógica de un entregable por el Coordinador.

## Diagrama de secuencia

|![Diseño: eliminarEntregable()](/images/RUP/02-diseño/casos-uso/coordinador/eliminarEntregable/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Decisiones de Diseño

- La operación requiere confirmación y motivo.
- Se registra fecha y Coordinador responsable.
- Se conservan autoría, proyecto, archivos y versiones.
- Cancelar conserva `ENTREGABLE_ABIERTO`; confirmar vuelve a `ENTREGABLES_ABIERTOS`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarEntregable/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarEntregable/README.md)
