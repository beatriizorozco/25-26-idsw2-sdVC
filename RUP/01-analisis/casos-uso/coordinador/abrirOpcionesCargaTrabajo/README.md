# FUNIBER > Coordinador > abrirOpcionesCargaTrabajo > Analisis

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesCargaTrabajo/README.md)|**Analisis**|[Diseno](/RUP/02-diseño/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)|
> |-|-|-|-|-|-|-|

## Informacion del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigacion
- **Fase RUP**: Elaboration (Elaboracion)
- **Disciplina**: Analisis y Diseno
- **Version**: 1.0
- **Fecha**: 2026-06-04
- **Autor**: Equipo de desarrollo

## Proposito

Analizar la colaboracion necesaria para que el Coordinador consulte la carga de trabajo global, aplique filtros y visualice el detalle por persona antes de decidir si edita una carga concreta.

## Diagrama de colaboracion

<div align=center>

|![Analisis: abrirOpcionesCargaTrabajo()](/images/RUP/01-analisis/casos-uso/coordinador/abrirOpcionesCargaTrabajo/abrirOpcionesCargaTrabajo-analisis.svg)|
|-|
|Codigo fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de analisis identificadas

### PanelCargaTrabajoView
**Estereotipo**: Vista (Boundary)

**Responsabilidades**:
- Recibir `abrirOpcionesCargaTrabajo()` desde `PANEL_PRINCIPAL_ABIERTO`.
- Presentar filtros, resumen global, detalle por persona y detalle de la persona seleccionada.
- Capturar criterios de filtrado y seleccion de persona.
- Permitir navegar a `editarCargaTrabajo(idPersona)` o volver al panel principal.

### CargaTrabajoController
**Estereotipo**: Control

**Responsabilidades**:
- Coordinar la consulta global de carga de trabajo.
- Aplicar los filtros solicitados por el Coordinador.
- Solicitar el resumen global y el detalle por persona.
- Preparar la seleccion de una persona para su posible edicion.

### CargaTrabajoRepository
**Estereotipo**: Entidad

**Responsabilidades**:
- Obtener el resumen global de carga de trabajo.
- Obtener el detalle por persona segun filtros.
- Gestionar instancias de `CargaTrabajo`.

### PersonaRepository
**Estereotipo**: Entidad

**Responsabilidades**:
- Obtener la persona seleccionada por el Coordinador.
- Mantener la trazabilidad entre carga de trabajo y persona.
- Identificar si una persona pertenece a una sede con perfil investigador-docente.

### ProyectoRepository
**Estereotipo**: Entidad

**Responsabilidades**:
- Consultar proyectos libres o pendientes de asignacion.
- Aportar contexto para sugerir investigadores-docentes con menor carga cuando exista un proyecto libre.

### CargaTrabajo
**Estereotipo**: Entidad

**Responsabilidades**:
- Representar dedicacion, disponibilidad, horas semanales y observaciones.
- Relacionar la carga con la persona responsable.
- Calcular margen respecto al maximo de 16 horas semanales de docencia solo cuando aplica por sede.

### Persona
**Estereotipo**: Entidad

**Responsabilidades**:
- Representar los datos basicos de la persona cuya carga se consulta.
- Aportar sede, perfil y area de investigacion al resumen.
- Diferenciar si actua como investigadora-docente o solo investigadora segun la sede FUNIBER.

### Proyecto
**Estereotipo**: Entidad

**Responsabilidades**:
- Representar un proyecto libre o pendiente de asignacion.
- Aportar criterios para seleccionar candidatos adecuados.

## Flujo de colaboracion

1. `PANEL_PRINCIPAL_ABIERTO` envia `abrirOpcionesCargaTrabajo()` a `PanelCargaTrabajoView`.
2. La vista solicita `obtenerResumenGlobal(coordinador, filtros)` al controlador.
3. La vista solicita `listarCargaPorPersona(filtros)` para poblar el detalle.
4. El controlador consulta `CargaTrabajoRepository.obtenerResumenGlobal(filtros)`.
5. El controlador consulta `CargaTrabajoRepository.obtenerDetallePorPersona(filtros)`.
6. El controlador consulta `ProyectoRepository.obtenerProyectosLibres()`.
7. El controlador consulta `PersonaRepository.obtenerInvestigadoresDocentesPorSede()` para identificar que sedes aplican docencia.
8. Si existe un proyecto libre, el controlador consulta `CargaTrabajoRepository.sugerirInvestigadoresDocentesConMenorCarga(proyectoLibre, sedesDocentes)`.
9. Si el Coordinador selecciona una persona, el controlador consulta `PersonaRepository.obtenerPorId(idPersona)`.
10. La vista presenta `OPCIONES_CARGA_TRABAJO_ABIERTAS` y permite editar o volver al panel.

## Correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Metodo/Colaboracion|
|-|-|-|
|Mostrar filtros disponibles|`PanelCargaTrabajoView`|Presenta criterios de filtrado|
|Mostrar resumen global|`CargaTrabajoController`|`obtenerResumenGlobal(coordinador, filtros)`|
|Mostrar detalle por persona|`CargaTrabajoRepository`|`obtenerDetallePorPersona(filtros)`|
|Mostrar detalle de persona seleccionada|`PersonaRepository`|`obtenerPorId(idPersona)`|
|Consultar proyectos libres|`ProyectoRepository`|`obtenerProyectosLibres()`|
|Identificar investigadores-docentes por sede|`PersonaRepository`|`obtenerInvestigadoresDocentesPorSede()`|
|Sugerir investigadores-docentes con menor carga|`CargaTrabajoRepository`|`sugerirInvestigadoresDocentesConMenorCarga(proyectoLibre, sedesDocentes)`|
|Permitir editar carga de trabajo|`PanelCargaTrabajoView`|Navega a `editarCargaTrabajo(idPersona)`|

## Reglas funcionales consideradas

- El Coordinador tiene vision global de cargas de trabajo.
- La consulta puede filtrarse por sede, perfil, investigador-docente, area o proyecto.
- La condicion de investigador-docente depende de la sede FUNIBER: por ejemplo, Santander puede operar con investigadores-docentes y Barcelona con investigadores sin docencia.
- Cuando hay un proyecto libre, el sistema debe sugerir investigadores-docentes con menor carga de trabajo para evitar asignaciones arbitrarias, respetando la sede donde aplica la docencia.
- La carga de trabajo debe evitar asignaciones arbitrarias que sobrecarguen siempre a las mismas personas.
- Un investigador-docente suele impartir 4 asignaturas por cuatrimestre.
- El maximo ordinario de docencia es 16 horas semanales.
- Si una persona de una sede con docencia investigadora alcanza ese limite, el sistema no debe permitir registrar mas horas docentes.
- Si la persona pertenece a una sede donde solo es investigadora, no se calcula margen docente ni se aplica limite de docencia.
- Las recompensas no nacen por exceso de docencia; se gestionan cuando se completa un proyecto, con opcion economica o reduccion docente posterior.
- La edicion se realiza sobre una persona concreta seleccionada desde el listado.
- El caso no asigna el proyecto; solo prepara informacion para decidir la asignacion en el flujo de proyectos.

## Referencias

- [Especificacion detallada](/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesCargaTrabajo/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [conversation-log.md](/conversation-log.md)
