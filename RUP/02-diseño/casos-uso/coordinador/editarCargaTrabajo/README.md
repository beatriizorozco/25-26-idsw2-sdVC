# FUNIBER > Coordinador > editarCargaTrabajo > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarCargaTrabajo/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarCargaTrabajo/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/editarCargaTrabajo/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la edición de la carga de trabajo de una persona seleccionada por el Coordinador, recalculando su prioridad para proyectos libres e impidiendo superar el límite ordinario de 16 horas semanales de docencia en una sede donde aplique el perfil investigador-docente.

## Diagrama de secuencia

|![Diseño: editarCargaTrabajo()](/images/RUP/02-diseño/casos-uso/coordinador/editarCargaTrabajo/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **EditarCargaTrabajoForm**: Precarga la carga de la persona seleccionada y captura cambios.
- **CargaTrabajoController**: Expone `GET /api/carga-trabajo/{personaId}` y `PATCH /api/carga-trabajo/{personaId}`.
- **SesionService**: Valida la sesión activa y el rol `COORDINADOR`.
- **CargaTrabajoService**: Valida horas, actualiza carga y recalcula prioridad.
- **PersonaRepository**: Comprueba que la persona exista, su sede y si aplica como investigadora-docente.
- **CargaTrabajoRepository**: Recupera y persiste la carga de trabajo.

## Decisiones de Diseño

- Solo el Coordinador puede editar cargas de otras personas.
- El formulario se precarga con los datos actuales antes de permitir guardar.
- La actualización usa `PATCH` para modificar únicamente los campos enviados.
- La carga docente se compara con el límite de 16 horas semanales solo si la sede clasifica a la persona como investigadora-docente.
- Si se intenta superar el límite docente en una sede con docencia, el sistema rechaza la actualización.
- Si la sede no tiene docencia investigadora, la docencia debe permanecer en 0 horas y el sistema rechaza cualquier valor superior.
- Las recompensas se originan por proyectos completados y se resuelven como compensación económica o reducción docente en el siguiente cuatrimestre.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarCargaTrabajo/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/editarCargaTrabajo/README.md)
