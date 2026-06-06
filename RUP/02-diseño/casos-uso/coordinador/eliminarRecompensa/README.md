# FUNIBER > Coordinador > eliminarRecompensa > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarRecompensa/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarRecompensa/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/eliminarRecompensa/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la eliminación segura de una recompensa mediante confirmación explícita del Coordinador.

## Diagrama de secuencia

|![Diseño: eliminarRecompensa()](/images/RUP/02-diseño/casos-uso/coordinador/eliminarRecompensa/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **RecompensaDetallePage**: Presenta la confirmación y conserva el detalle si se cancela.
- **RecompensaController**: Expone `DELETE /api/recompensas/{id}`.
- **SesionService**: Exige una sesión de Coordinador.
- **RecompensaService**: Recupera y elimina la recompensa.
- **RecompensaRepository** y **ProyectoRepository**: Mantienen la trazabilidad y ejecutan la eliminación.

## Decisiones de Diseño

- La API solo se invoca después de una confirmación explícita.
- Cancelar conserva `RECOMPENSA_ABIERTA` sin modificar datos.
- Eliminar una recompensa no modifica el proyecto ni la carga del investigador.
- La eliminación correcta devuelve `204 No Content` y abre el listado.
- Una recompensa inexistente devuelve `404 Not Found`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarRecompensa/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarRecompensa/README.md)
