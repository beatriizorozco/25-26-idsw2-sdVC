# FUNIBER > Coordinador > abrirOpcionesCargaTrabajo > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la consulta global de carga de trabajo disponible para el Coordinador. El caso permite revisar la carga por persona, detectar proyectos libres y sugerir investigadores-docentes con menor carga para evitar asignaciones arbitrarias.

## Diagrama de secuencia

|![Diseño: abrirOpcionesCargaTrabajo()](/images/RUP/02-diseño/casos-uso/coordinador/abrirOpcionesCargaTrabajo/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **CargaTrabajoPage**: Presenta filtros, resumen global, detalle por persona, proyectos libres y sugerencias.
- **CargaTrabajoController**: Expone `GET /api/carga-trabajo`.
- **SesionService**: Valida que exista una sesión activa de Coordinador.
- **CargaTrabajoService**: Orquesta la consulta de carga, candidatos y margen docente.
- **CargaTrabajoRepository**: Recupera cargas de trabajo.
- **PersonaRepository**: Identifica qué personas pertenecen a sedes con perfil investigador-docente.
- **ProyectoRepository**: Recupera proyectos libres o pendientes de asignación.

## Decisiones de Diseño

- El Coordinador consulta una vista global; no modifica datos en este caso.
- La API exige rol `COORDINADOR`.
- El resumen incluye carga total, sede, tipo de vinculación, carga docente cuando aplica y detalle por persona.
- Si existen proyectos libres, el servicio calcula sugerencias priorizando investigadores-docentes con menor carga.
- La condición de investigador-docente depende de la sede FUNIBER.
- El margen docente se muestra solo en sedes donde existe docencia investigadora.
- Las recompensas no dependen de exceder horas de docencia; se gestionan cuando un proyecto se completa.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesCargaTrabajo/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)
