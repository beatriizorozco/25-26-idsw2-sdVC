# FUNIBER > Coordinador > editarCargaTrabajo > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarCargaTrabajo/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarCargaTrabajo/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/editarCargaTrabajo/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/editarCargaTrabajo/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigacion
- **Fase RUP**: Elaboration (Elaboracion)
- **Disciplina**: Analisis y Diseno
- **Version**: 1.0
- **Fecha**: 2026-06-04
- **Autor**: Equipo de desarrollo

## Propósito

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

### CargaTrabajo
**Estereotipo**: Entidad

**Responsabilidades**:
- Representar horas de docencia, investigacion y actividades academicas.
- Encapsular observaciones y disponibilidad.
- Validar que la docencia no supere 16 horas semanales cuando aplica por sede.

### Persona
**Estereotipo**: Entidad

**Responsabilidades**:
- Representar la persona propietaria de la carga modificada.
- Diferenciar si la persona es investigadora-docente o solo investigadora segun su sede.

## Flujo de colaboracion

1. `OPCIONES_CARGA_TRABAJO_ABIERTAS` envia `editarCargaTrabajo(idPersona)` a `EditarCargaTrabajoView`.
2. La vista solicita `obtenerCargaDePersona(coordinador, idPersona)`.
3. El controlador obtiene la persona con `PersonaRepository.obtenerPorId(idPersona)`.
4. El controlador obtiene su carga con `CargaTrabajoRepository.obtenerPorPersona(idPersona)`.
5. La vista envia datos modificados y el controlador ejecuta `validarHoras(datos)`.
6. Si los datos son validos, el controlador ejecuta `actualizarCargaDePersona(coordinador, idPersona, datos)`.
7. La entidad valida si la persona `esInvestigadorDocenteSegunSede()`.
8. La entidad ejecuta `validarLimiteDocente(maximo16h)` y rechaza la actualizacion si supera 16 horas semanales.
9. El repositorio persiste `actualizar(cargaTrabajo)`.
10. El repositorio ejecuta `recalcularPrioridadParaProyectosLibres(idPersona)` para mantener actualizada la prioridad de asignacion.
11. La vista vuelve a `OPCIONES_CARGA_TRABAJO_ABIERTAS`; si el actor cancela, vuelve sin cambios.

## Correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Metodo/Colaboracion|
|-|-|-|
|Editar carga de una persona|`EditarCargaTrabajoView`|Recibe `editarCargaTrabajo(idPersona)`|
|Presentar carga actual|`CargaTrabajoController`|`obtenerCargaDePersona(coordinador, idPersona)`|
|Validar datos introducidos|`CargaTrabajoController`|`validarHoras(datos)`|
|Validar docencia por sede|`Persona`|`esInvestigadorDocenteSegunSede()`|
|Impedir superar 16 horas docentes|`CargaTrabajo`|`validarLimiteDocente(maximo16h)`|
|Persistir cambios|`CargaTrabajoRepository`|`actualizar(cargaTrabajo)`|
|Actualizar prioridad para proyectos libres|`CargaTrabajoRepository`|`recalcularPrioridadParaProyectosLibres(idPersona)`|
|Cancelar sin cambios|`EditarCargaTrabajoView`|`cancelarEdicion()`|

## Reglas funcionales consideradas

- El Coordinador puede editar cargas de otras personas.
- La persona debe existir antes de modificar su carga.
- Las horas introducidas deben validarse antes de persistir.
- La carga actualizada influye en la prioridad de asignacion de proyectos libres a investigadores-docentes con menor carga.
- La condicion de investigador-docente depende de la sede FUNIBER.
- Un investigador-docente suele impartir 4 asignaturas por cuatrimestre.
- El maximo ordinario de docencia es 16 horas semanales.
- Si se intenta superar ese limite en una sede con docencia investigadora, el sistema rechaza la actualizacion.
- Si la sede clasifica a la persona como solo investigadora, no puede registrar horas docentes; la docencia queda en 0 horas y no se calcula margen docente.
- Las recompensas pertenecen al flujo de proyectos completados y pueden resolverse como recompensa economica o reduccion docente del siguiente cuatrimestre.
- La cancelacion no modifica la informacion existente.
- La salida natural vuelve a `OPCIONES_CARGA_TRABAJO_ABIERTAS`.

## Referencias

- [Especificacion detallada](/RUP/00-casos-uso/02-detalle/coordinador/editarCargaTrabajo/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [conversation-log.md](/conversation-log.md)
