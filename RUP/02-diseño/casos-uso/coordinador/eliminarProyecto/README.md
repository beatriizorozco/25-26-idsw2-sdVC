# FUNIBER > Coordinador > eliminarProyecto > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarProyecto/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarProyecto/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la eliminación segura de un proyecto, comprobando previamente sus dependencias y trazabilidad.

## Diagrama de secuencia

|![Diseño: eliminarProyecto()](/images/RUP/02-diseño/casos-uso/coordinador/eliminarProyecto/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **ProyectoDetallePage**: Presenta el diagnóstico y solicita confirmación.
- **ProyectoController**: Expone comprobación y `DELETE /api/proyectos/{id}`.
- **SesionService**: Exige una sesión de Coordinador.
- **ProyectoService**: Decide si la eliminación preserva la trazabilidad.
- **ProyectoRepository**, **EntregableRepository** y **RecompensaRepository**: Comprueban dependencias y eliminan cuando procede.

## Decisiones de Diseño

- La confirmación solo se habilita cuando la eliminación es compatible con la trazabilidad.
- Cancelar o impedir la eliminación conserva `PROYECTO_ABIERTO`.
- La API no elimina entregables, recompensas ni perfiles asociados de forma implícita.
- La eliminación correcta devuelve `204 No Content` y abre el listado.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarProyecto/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarProyecto/README.md)
