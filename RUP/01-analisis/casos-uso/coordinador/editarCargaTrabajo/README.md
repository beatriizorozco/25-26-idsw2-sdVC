# FUNIBER > Coordinador > editarCargaTrabajo > Analisis

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarCargaTrabajo/README.md)|**Analisis**|[Diseno](/RUP/02-diseño/casos-uso/coordinador/editarCargaTrabajo/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarCargaTrabajo/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/editarCargaTrabajo/README.md)|
> |-|-|-|-|-|-|-|

## Informacion del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigacion
- **Fase RUP**: Elaboration (Elaboracion)
- **Disciplina**: Analisis y Diseno
- **Version**: 1.0
- **Fecha**: 2026-06-04
- **Autor**: Equipo de desarrollo

## Proposito

Analizar la colaboracion necesaria para que el Coordinador actualice la carga de trabajo de una persona seleccionada, validando los datos antes de guardar y manteniendo retorno a las opciones de carga de trabajo.

## Diagrama de colaboracion

<div align=center>

|![Analisis: editarCargaTrabajo()](/images/RUP/01-analisis/casos-uso/coordinador/editarCargaTrabajo/editarCargaTrabajo-analisis.svg)|
|-|
|Codigo fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de analisis identificadas

### EditarCargaTrabajoView
**Estereotipo**: Vista (Boundary)

**Responsabilidades**:
- Recibir `editarCargaTrabajo(idPersona)` desde `OPCIONES_CARGA_TRABAJO_ABIERTAS`.
- Presentar la carga actual de la persona seleccionada.
- Capturar modificaciones de horas, dedicacion y observaciones.
- Presentar errores de validacion o confirmacion de actualizacion.
- Permitir cancelar la edicion sin modificar datos.

### CargaTrabajoController
**Estereotipo**: Control

**Responsabilidades**:
- Coordinar la obtencion de la carga de la persona seleccionada.
- Validar que los datos introducidos sean coherentes.
- Aplicar permisos del Coordinador sobre la carga de trabajo global.
- Solicitar la persistencia de la carga actualizada.

### CargaTrabajoRepository
**Estereotipo**: Entidad

**Responsabilidades**:
- Obtener la carga de trabajo asociada a una persona.
- Persistir la carga de trabajo actualizada.
- Mantener la consistencia conceptual de las horas registradas.

### PersonaRepository
**Estereotipo**: Entidad

**Responsabilidades**:
- Localizar la persona cuya carga se va a editar.
- Permitir validar que la persona existe antes de modificar su carga.
- Aportar su sede para saber si aplica docencia investigadora.

### RecompensaRepository
**Estereotipo**: Entidad

**Responsabilidades**:
- Registrar una compensacion pendiente solo cuando la carga supera el limite docente y la sede permite docencia investigadora.
- Mantener la relacion entre exceso docente y recompensa economica.

### CargaTrabajo
**Estereotipo**: Entidad

**Responsabilidades**:
- Representar horas de docencia, investigacion y actividades academicas.
- Encapsular observaciones y disponibilidad.
- Calcular exceso docente cuando las horas de docencia superan 16 horas semanales y aplica por sede.

### Persona
**Estereotipo**: Entidad

**Responsabilidades**:
- Representar la persona propietaria de la carga modificada.
- Diferenciar si la persona es investigadora-docente o solo investigadora segun su sede.

### Recompensa
**Estereotipo**: Entidad

**Responsabilidades**:
- Representar la compensacion economica pendiente por exceso docente.

## Flujo de colaboracion

1. `OPCIONES_CARGA_TRABAJO_ABIERTAS` envia `editarCargaTrabajo(idPersona)` a `EditarCargaTrabajoView`.
2. La vista solicita `obtenerCargaDePersona(coordinador, idPersona)`.
3. El controlador obtiene la persona con `PersonaRepository.obtenerPorId(idPersona)`.
4. El controlador obtiene su carga con `CargaTrabajoRepository.obtenerPorPersona(idPersona)`.
5. La vista envia datos modificados y el controlador ejecuta `validarHoras(datos)`.
6. Si los datos son validos, el controlador ejecuta `actualizarCargaDePersona(coordinador, idPersona, datos)`.
7. La entidad valida si la persona `esInvestigadorDocenteSegunSede()`.
8. La entidad calcula `calcularExcesoDocenteSiAplica(maximo16h)`.
9. El repositorio persiste `actualizar(cargaTrabajo)`.
10. El repositorio ejecuta `recalcularPrioridadParaProyectosLibres(idPersona)` para mantener actualizada la prioridad de asignacion.
11. Si hay exceso docente aplicable por sede, `RecompensaRepository.registrarCompensacionPendienteSiExcede(cargaTrabajo)` deja la compensacion preparada.
12. La vista vuelve a `OPCIONES_CARGA_TRABAJO_ABIERTAS`; si el actor cancela, vuelve sin cambios.

## Correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Metodo/Colaboracion|
|-|-|-|
|Editar carga de una persona|`EditarCargaTrabajoView`|Recibe `editarCargaTrabajo(idPersona)`|
|Presentar carga actual|`CargaTrabajoController`|`obtenerCargaDePersona(coordinador, idPersona)`|
|Validar datos introducidos|`CargaTrabajoController`|`validarHoras(datos)`|
|Validar docencia por sede|`Persona`|`esInvestigadorDocenteSegunSede()`|
|Persistir cambios|`CargaTrabajoRepository`|`actualizar(cargaTrabajo)`|
|Actualizar prioridad para proyectos libres|`CargaTrabajoRepository`|`recalcularPrioridadParaProyectosLibres(idPersona)`|
|Registrar compensacion por exceso docente|`RecompensaRepository`|`registrarCompensacionPendienteSiExcede(cargaTrabajo)`|
|Cancelar sin cambios|`EditarCargaTrabajoView`|`cancelarEdicion()`|

## Reglas funcionales consideradas

- El Coordinador puede editar cargas de otras personas.
- La persona debe existir antes de modificar su carga.
- Las horas introducidas deben validarse antes de persistir.
- La carga actualizada influye en la prioridad de asignacion de proyectos libres a investigadores-docentes con menor carga.
- La condicion de investigador-docente depende de la sede FUNIBER.
- Un investigador-docente suele impartir 4 asignaturas por cuatrimestre.
- El maximo ordinario de docencia es 16 horas semanales.
- Si se supera ese limite en una sede con docencia investigadora, el sistema registra una compensacion economica pendiente.
- Si la sede clasifica a la persona como solo investigadora, no se genera compensacion docente.
- La cancelacion no modifica la informacion existente.
- La salida natural vuelve a `OPCIONES_CARGA_TRABAJO_ABIERTAS`.

## Referencias

- [Especificacion detallada](/RUP/00-casos-uso/02-detalle/coordinador/editarCargaTrabajo/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [conversation-log.md](/conversation-log.md)
