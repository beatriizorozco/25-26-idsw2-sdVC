# FUNIBER > Coordinador > editarProyecto > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarProyecto/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarProyecto/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la actualización controlada de los datos de un proyecto abierto.

## Diagrama de secuencia

|![Diseño: editarProyecto()](/images/RUP/02-diseño/casos-uso/coordinador/editarProyecto/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **EditarProyectoForm**: Presenta los datos actuales y captura cambios.
- **ProyectoController**: Expone `PATCH /api/proyectos/{id}`.
- **SesionService**: Exige una sesión de Coordinador.
- **ProyectoService**: Valida cambios, fechas y convocatoria.
- **ProyectoRepository** y **ConvocatoriaRepository**: Recuperan y actualizan los datos de proyectos activos.

## Decisiones de Diseño

- El formulario se precarga desde el detalle vigente.
- Solo se modifican los campos enviados.
- Cancelar conserva `PROYECTO_ABIERTO`.
- Datos incompatibles devuelven `400 Bad Request`; un proyecto inexistente o archivado devuelve `404 Not Found`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarProyecto/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/editarProyecto/README.md)
