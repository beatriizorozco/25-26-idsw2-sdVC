# FUNIBER > Coordinador > editarRecompensa > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarRecompensa/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarRecompensa/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarRecompensa/README.md)|Pruebas|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la edición de una recompensa existente manteniendo el proyecto de origen, el beneficiario y las restricciones de tipo.

## Diagrama de secuencia

|![Diseño: editarRecompensa()](/images/RUP/02-diseño/casos-uso/coordinador/editarRecompensa/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **EditarRecompensaForm**: Precarga y captura los cambios.
- **RecompensaController**: Expone la preparación de edición y `PATCH /api/recompensas/{id}`.
- **SesionService**: Exige una sesión de Coordinador.
- **RecompensaService**: Valida y aplica los cambios.
- **RecompensaRepository**, **ProyectoRepository** e **InvestigadorRepository**: Recuperan contexto y persisten la actualización.

## Decisiones de Diseño

- El formulario se precarga con datos actuales, beneficiarios elegibles del proyecto y tipos permitidos.
- El proyecto de origen debe continuar completado.
- El proyecto de origen permanece fijo; el beneficiario solo puede cambiarse por otro investigador elegible del mismo proyecto.
- No se permite asignar reducción docente a un beneficiario sin docencia.
- Cancelar conserva `RECOMPENSA_ABIERTA` y no invoca `PATCH`.
- `PATCH` modifica únicamente los campos editables enviados.
- Un cambio inválido conserva el formulario y devuelve `400 Bad Request`.
- Una recompensa inexistente devuelve `404 Not Found`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarRecompensa/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/editarRecompensa/README.md)
