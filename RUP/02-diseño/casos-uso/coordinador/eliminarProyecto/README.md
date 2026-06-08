# FUNIBER > Coordinador > eliminarProyecto > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarProyecto/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarProyecto/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la baja lógica de un proyecto, retirándolo de la gestión activa y conservándolo íntegramente en el histórico.

## Diagrama de secuencia

|![Diseño: eliminarProyecto()](/images/RUP/02-diseño/casos-uso/coordinador/eliminarProyecto/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **ProyectoDetallePage**: Presenta las consecuencias del archivado y solicita confirmación.
- **ProyectoController**: Expone las opciones de archivado y `DELETE /api/proyectos/{id}`.
- **SesionService**: Exige una sesión de Coordinador.
- **ProyectoService**: Coordina la baja lógica y registra su autoría.
- **ProyectoRepository**: Conserva el proyecto y marca sus metadatos de archivado.

## Decisiones de Diseño

- El nombre `eliminarProyecto()` se conserva por trazabilidad, pero la operación realiza una baja lógica.
- Cancelar conserva `PROYECTO_ABIERTO`.
- Estado, equipo, entregables, recompensas y demás relaciones permanecen intactos.
- El archivado registra `archivado`, `fechaArchivado` y `archivadoPor`.
- Los listados activos excluyen proyectos archivados.
- El archivado correcto devuelve `204 No Content` y abre el listado.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarProyecto/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarProyecto/README.md)
