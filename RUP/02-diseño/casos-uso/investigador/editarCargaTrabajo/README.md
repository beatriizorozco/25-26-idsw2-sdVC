# FUNIBER > Investigador > editarCargaTrabajo > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/editarCargaTrabajo/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/editarCargaTrabajo/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la edición de la carga de trabajo propia del Investigador, manteniendo la restricción de que no puede modificar cargas ajenas ni superar el límite docente de 16 horas semanales cuando su sede aplica docencia investigadora.

## Diagrama de secuencia

|![Diseño: editarCargaTrabajo()](/images/RUP/02-diseño/casos-uso/investigador/editarCargaTrabajo/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **EditarCargaTrabajoPropiaForm**: Precarga y captura los cambios de carga del Investigador.
- **CargaTrabajoController**: Expone `GET /api/carga-trabajo/me` y `PATCH /api/carga-trabajo/me`.
- **SesionService**: Valida la sesión activa.
- **CargaTrabajoService**: Valida y actualiza la carga propia.
- **CargaTrabajoRepository**: Recupera y guarda la carga del usuario autenticado.

## Decisiones de Diseño

- El Investigador solo puede editar su propia carga.
- La API no acepta identificadores externos para este rol.
- El formulario se precarga con la carga actual antes de guardar cambios.
- La actualización usa `PATCH`.
- La carga docente se compara con el límite de 16 horas semanales solo en sedes con docencia investigadora.
- Si se intenta superar el límite docente en una sede con docencia, el sistema rechaza la actualización.
- Si la sede no tiene docencia investigadora, la docencia debe permanecer en 0 horas y el sistema rechaza cualquier valor superior.
- Las recompensas se gestionan aparte cuando se completa un proyecto.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarCargaTrabajo/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/editarCargaTrabajo/README.md)
