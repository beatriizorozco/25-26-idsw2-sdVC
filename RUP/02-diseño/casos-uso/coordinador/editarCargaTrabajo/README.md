# FUNIBER > Coordinador > editarCargaTrabajo > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarCargaTrabajo/README.md)|**Diseño**|Desarrollo|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la edición de la carga de trabajo de una persona seleccionada por el Coordinador, recalculando su prioridad para proyectos libres y registrando compensación económica si supera el límite ordinario de 16 horas semanales de docencia en una sede donde aplique el perfil investigador-docente.

## Diagrama de secuencia

|![Diseño: editarCargaTrabajo()](/images/RUP/02-diseño/casos-uso/coordinador/editarCargaTrabajo/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **EditarCargaTrabajoForm**: Precarga la carga de la persona seleccionada y captura cambios.
- **CargaTrabajoController**: Expone `GET /api/carga-trabajo/{personaId}` y `PATCH /api/carga-trabajo/{personaId}`.
- **SesionService**: Valida la sesión activa y el rol `COORDINADOR`.
- **CargaTrabajoService**: Valida horas, actualiza carga, recalcula prioridad y gestiona compensaciones.
- **PersonaRepository**: Comprueba que la persona exista, su sede y si aplica como investigadora-docente.
- **CargaTrabajoRepository**: Recupera y persiste la carga de trabajo.
- **RecompensaRepository**: Registra compensación pendiente si hay exceso docente.

## Decisiones de Diseño

- Solo el Coordinador puede editar cargas de otras personas.
- El formulario se precarga con los datos actuales antes de permitir guardar.
- La actualización usa `PATCH` para modificar únicamente los campos enviados.
- La carga docente se compara con el límite de 16 horas semanales solo si la sede clasifica a la persona como investigadora-docente.
- Si se supera el límite docente en una sede con docencia, se registra o actualiza una compensación pendiente.
- Si la sede clasifica a la persona como solo investigadora, no se genera compensación docente.
- Tras guardar, se recalcula la prioridad de asignación a proyectos libres.
- La salida correcta vuelve a `OPCIONES_CARGA_TRABAJO_ABIERTAS`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarCargaTrabajo/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/editarCargaTrabajo/README.md)
