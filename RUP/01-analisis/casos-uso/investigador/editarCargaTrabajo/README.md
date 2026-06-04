# FUNIBER > Investigador > editarCargaTrabajo > Analisis

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarCargaTrabajo/README.md)|**Analisis**|[Diseno](/RUP/02-diseño/casos-uso/investigador/editarCargaTrabajo/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/editarCargaTrabajo/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/editarCargaTrabajo/README.md)|
> |-|-|-|-|-|-|-|

## Informacion del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigacion
- **Fase RUP**: Elaboration (Elaboracion)
- **Disciplina**: Analisis y Diseno
- **Version**: 1.0
- **Fecha**: 2026-06-04
- **Autor**: Equipo de desarrollo

## Proposito

Analizar la colaboracion necesaria para que el Investigador actualice su propia carga de trabajo, validando las horas introducidas y permitiendo cancelar sin modificar datos.

## Diagrama de colaboracion

<div align=center>

|![Analisis: editarCargaTrabajo()](/images/RUP/01-analisis/casos-uso/investigador/editarCargaTrabajo/editarCargaTrabajo-analisis.svg)|
|-|
|Codigo fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de analisis identificadas

### EditarCargaTrabajoPropiaView
**Estereotipo**: Vista (Boundary)

**Responsabilidades**:
- Recibir `editarCargaTrabajo()` desde `OPCIONES_CARGA_TRABAJO_ABIERTAS`.
- Presentar la carga actual del Investigador autenticado.
- Capturar modificaciones de horas y observaciones.
- Presentar errores de validacion o confirmacion de actualizacion.
- Permitir cancelar la edicion sin cambios.

### CargaTrabajoController
**Estereotipo**: Control

**Responsabilidades**:
- Coordinar la obtencion de la carga propia.
- Validar horas y datos introducidos.
- Garantizar que la actualizacion se aplica solo al Investigador autenticado.
- Solicitar la persistencia de la carga actualizada.

### CargaTrabajoRepository
**Estereotipo**: Entidad

**Responsabilidades**:
- Obtener la carga de trabajo por usuario autenticado.
- Persistir la carga de trabajo actualizada.
- Mantener la consistencia de las horas semanales.

### RecompensaRepository
**Estereotipo**: Entidad

**Responsabilidades**:
- Registrar una compensacion pendiente solo cuando la carga propia supera el limite docente y la sede aplica docencia.
- Mantener la relacion entre exceso docente y recompensa economica.

### CargaTrabajo
**Estereotipo**: Entidad

**Responsabilidades**:
- Representar docencia, investigacion, actividades academicas y observaciones.
- Mantener el total semanal y las reglas conceptuales de carga.
- Calcular exceso docente cuando las horas de docencia superan 16 horas semanales y aplica por sede.

### Recompensa
**Estereotipo**: Entidad

**Responsabilidades**:
- Representar la compensacion economica pendiente por exceso docente.

## Flujo de colaboracion

1. `OPCIONES_CARGA_TRABAJO_ABIERTAS` envia `editarCargaTrabajo()` a `EditarCargaTrabajoPropiaView`.
2. La vista solicita `obtenerCargaPropia(investigador)`.
3. El controlador consulta `CargaTrabajoRepository.obtenerPorUsuario(investigador)`.
4. La vista envia los cambios y el controlador ejecuta `validarHoras(datos)`.
5. Si los datos son validos, el controlador ejecuta `actualizarCargaPropia(investigador, datos)`.
6. La entidad calcula `calcularExcesoDocenteSiAplica(maximo16h)`.
7. El repositorio persiste `actualizar(cargaTrabajo)`.
8. Si hay exceso docente, `RecompensaRepository.registrarCompensacionPendienteSiExcede(cargaTrabajo)` deja la compensacion preparada.
9. La vista vuelve a `OPCIONES_CARGA_TRABAJO_ABIERTAS`; si el actor cancela, vuelve sin cambios.

## Correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Metodo/Colaboracion|
|-|-|-|
|Editar carga propia|`EditarCargaTrabajoPropiaView`|Recibe `editarCargaTrabajo()`|
|Presentar carga actual|`CargaTrabajoController`|`obtenerCargaPropia(investigador)`|
|Validar datos introducidos|`CargaTrabajoController`|`validarHoras(datos)`|
|Persistir cambios propios|`CargaTrabajoRepository`|`actualizar(cargaTrabajo)`|
|Registrar compensacion por exceso docente|`RecompensaRepository`|`registrarCompensacionPendienteSiExcede(cargaTrabajo)`|
|Cancelar sin cambios|`EditarCargaTrabajoPropiaView`|`cancelarEdicion()`|

## Reglas funcionales consideradas

- El Investigador solo puede editar su propia carga de trabajo.
- No se recibe `idPersona` desde la vista en este caso.
- Las horas deben validarse antes de guardar.
- Un investigador-docente suele impartir 4 asignaturas por cuatrimestre.
- El maximo ordinario de docencia es 16 horas semanales.
- La condicion de investigador-docente depende de la sede FUNIBER.
- Si se supera ese limite en una sede con docencia investigadora, el sistema registra una compensacion economica pendiente.
- Si la sede clasifica al usuario como solo investigador, no se genera compensacion docente.
- La cancelacion no altera la carga existente.
- La salida natural vuelve a `OPCIONES_CARGA_TRABAJO_ABIERTAS`.

## Referencias

- [Especificacion detallada](/RUP/00-casos-uso/02-detalle/investigador/editarCargaTrabajo/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [conversation-log.md](/conversation-log.md)
