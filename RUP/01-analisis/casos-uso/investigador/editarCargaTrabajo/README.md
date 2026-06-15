# FUNIBER > Investigador > editarCargaTrabajo > Análisis

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarCargaTrabajo/README.md)|**Análisis**|[Diseño](/RUP/02-diseño/casos-uso/investigador/editarCargaTrabajo/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/editarCargaTrabajo/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/editarCargaTrabajo/README.md)|
> |-|-|-|-|-|-|-|

## Información del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigacion
- **Fase RUP**: Elaboration (Elaboracion)
- **Disciplina**: Analisis y Diseno
- **Version**: 1.0
- **Fecha**: 2026-06-04
- **Autor**: Equipo de desarrollo

## Propósito

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

### CargaTrabajo
**Estereotipo**: Entidad

**Responsabilidades**:
- Representar docencia, investigacion, actividades academicas y observaciones.
- Mantener el total semanal y las reglas conceptuales de carga.
- Validar que la docencia no supere 16 horas semanales cuando aplica por sede.

## Flujo de colaboracion

1. `OPCIONES_CARGA_TRABAJO_ABIERTAS` envia `editarCargaTrabajo()` a `EditarCargaTrabajoPropiaView`.
2. La vista solicita `obtenerCargaPropia(investigador)`.
3. El controlador consulta `CargaTrabajoRepository.obtenerPorUsuario(investigador)`.
4. La vista envia los cambios y el controlador ejecuta `validarHoras(datos)`.
5. Si los datos son validos, el controlador ejecuta `actualizarCargaPropia(investigador, datos)`.
6. La entidad ejecuta `validarLimiteDocente(maximo16h)` cuando la sede aplica docencia investigadora.
7. El repositorio persiste `actualizar(cargaTrabajo)`.
8. La vista vuelve a `OPCIONES_CARGA_TRABAJO_ABIERTAS`; si el actor cancela, vuelve sin cambios.

## Correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Metodo/Colaboracion|
|-|-|-|
|Editar carga propia|`EditarCargaTrabajoPropiaView`|Recibe `editarCargaTrabajo()`|
|Presentar carga actual|`CargaTrabajoController`|`obtenerCargaPropia(investigador)`|
|Validar datos introducidos|`CargaTrabajoController`|`validarHoras(datos)`|
|Impedir superar 16 horas docentes|`CargaTrabajo`|`validarLimiteDocente(maximo16h)`|
|Persistir cambios propios|`CargaTrabajoRepository`|`actualizar(cargaTrabajo)`|
|Cancelar sin cambios|`EditarCargaTrabajoPropiaView`|`cancelarEdicion()`|

## Reglas funcionales consideradas

- El Investigador solo puede editar su propia carga de trabajo.
- No se recibe `idPersona` desde la vista en este caso.
- Las horas deben validarse antes de guardar.
- Un investigador-docente suele impartir 4 asignaturas por cuatrimestre.
- El maximo ordinario de docencia es 16 horas semanales.
- La condicion de investigador-docente depende de la sede FUNIBER.
- Si se intenta superar ese limite en una sede con docencia investigadora, el sistema rechaza la actualizacion.
- Si la sede clasifica al usuario como solo investigador, no puede registrar horas docentes; la docencia queda en 0 horas y no se calcula margen docente.
- Las recompensas pertenecen al flujo de proyectos completados y pueden resolverse como recompensa economica o reduccion docente del siguiente cuatrimestre.
- La cancelacion no altera la carga existente.
- La salida natural vuelve a `OPCIONES_CARGA_TRABAJO_ABIERTAS`.

## Referencias

- [Especificacion detallada](/RUP/00-casos-uso/02-detalle/investigador/editarCargaTrabajo/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [conversation-log.md](/conversation-log.md)
