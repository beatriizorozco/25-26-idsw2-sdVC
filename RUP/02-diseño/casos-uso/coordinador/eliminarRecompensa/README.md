# FUNIBER > Coordinador > eliminarRecompensa > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarRecompensa/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarRecompensa/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/eliminarRecompensa/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la anulación segura de una recompensa mediante confirmación explícita y motivo aportado por el Coordinador.

## Diagrama de secuencia

|![Diseño: eliminarRecompensa()](/images/RUP/02-diseño/casos-uso/coordinador/eliminarRecompensa/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **RecompensaDetallePage**: Presenta la confirmación y conserva el detalle si se cancela.
- **RecompensaController**: Expone `PATCH /api/recompensas/{id}/anulacion`.
- **SesionService**: Exige una sesión de Coordinador.
- **RecompensaService**: Recupera y anula la recompensa, conservando su concesión y trazabilidad.
- **RecompensaRepository** y **ProyectoRepository**: Mantienen la trazabilidad y ejecutan la anulación.

## Decisiones de Diseño

- La API solo se invoca después de una confirmación explícita.
- Cancelar conserva `RECOMPENSA_ABIERTA` sin modificar datos.
- Eliminar una recompensa no modifica el proyecto ni la carga del investigador.
- La anulación correcta devuelve `204 No Content` y abre el listado.
- La anulación registra fecha, Coordinador responsable y motivo sin borrar la concesión original.
- Una recompensa inexistente devuelve `404 Not Found`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarRecompensa/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarRecompensa/README.md)
