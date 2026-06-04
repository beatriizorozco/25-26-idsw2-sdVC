# FUNIBER > Investigador > abrirOpcionesCargaTrabajo > Analisis

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirOpcionesCargaTrabajo/README.md)|**Analisis**|[Diseno](/RUP/02-diseño/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)|
> |-|-|-|-|-|-|-|

## Informacion del artefacto

- **Proyecto**: FUNIBER - Plataforma Interna de Investigacion
- **Fase RUP**: Elaboration (Elaboracion)
- **Disciplina**: Analisis y Diseno
- **Version**: 1.0
- **Fecha**: 2026-06-04
- **Autor**: Equipo de desarrollo

## Proposito

Analizar la colaboracion necesaria para que el Investigador consulte su propia carga de trabajo, visualice su resumen semanal y acceda a la edicion de sus datos.

## Diagrama de colaboracion

<div align=center>

|![Analisis: abrirOpcionesCargaTrabajo()](/images/RUP/01-analisis/casos-uso/investigador/abrirOpcionesCargaTrabajo/abrirOpcionesCargaTrabajo-analisis.svg)|
|-|
|Codigo fuente: [colaboracion.puml](colaboracion.puml)|

</div>

## Clases de analisis identificadas

### CargaTrabajoPropiaView
**Estereotipo**: Vista (Boundary)

**Responsabilidades**:
- Recibir `abrirOpcionesCargaTrabajo()` desde `PANEL_PRINCIPAL_ABIERTO`.
- Presentar resumen personal y detalle semanal.
- Mostrar investigacion, actividades academicas y total semanal.
- Mostrar docencia y margen docente solo si la sede clasifica al usuario como investigador-docente.
- Permitir navegar a `editarCargaTrabajo()` o volver al panel principal.

### CargaTrabajoController
**Estereotipo**: Control

**Responsabilidades**:
- Coordinar la consulta de carga propia del Investigador.
- Impedir que el Investigador consulte cargas de otras personas desde este caso.
- Preparar las acciones disponibles segun el rol.
- Calcular el resumen personal a partir de la carga recuperada.

### CargaTrabajoRepository
**Estereotipo**: Entidad

**Responsabilidades**:
- Obtener la carga por usuario autenticado.
- Calcular o proporcionar resumen personal.
- Gestionar instancias de `CargaTrabajo`.

### CargaTrabajo
**Estereotipo**: Entidad

**Responsabilidades**:
- Representar horas de docencia, investigacion y actividades academicas.
- Mantener total semanal, margen docente y observaciones.
- Calcular margen respecto al limite de 16 horas semanales de docencia solo si la sede permite docencia.

## Flujo de colaboracion

1. `PANEL_PRINCIPAL_ABIERTO` envia `abrirOpcionesCargaTrabajo()` a `CargaTrabajoPropiaView`.
2. La vista solicita `obtenerCargaPropia(investigador)` al controlador.
3. La vista solicita `prepararAccionesPropias(investigador)`.
4. El controlador consulta `CargaTrabajoRepository.obtenerPorUsuario(investigador)`.
5. El controlador solicita `calcularResumenPersonal(cargaTrabajo)`.
6. La entidad calcula `calcularMargenDocente(maximo16h)` cuando aplica por sede.
7. La vista presenta `OPCIONES_CARGA_TRABAJO_ABIERTAS`.
8. El Investigador puede navegar a `editarCargaTrabajo()` o volver al panel.

## Correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Metodo/Colaboracion|
|-|-|-|
|Mostrar carga propia|`CargaTrabajoController`|`obtenerCargaPropia(investigador)`|
|Mostrar resumen personal|`CargaTrabajoRepository`|`calcularResumenPersonal(cargaTrabajo)`|
|Mostrar margen docente si aplica|`CargaTrabajo`|`calcularMargenDocente(maximo16h)`|
|Restringir consulta a usuario autenticado|`CargaTrabajoController`|`prepararAccionesPropias(investigador)`|
|Permitir edicion propia|`CargaTrabajoPropiaView`|Navega a `editarCargaTrabajo()`|
|Volver al panel|`CargaTrabajoPropiaView`|Navega a `abrirPanelPrincipal()`|

## Reglas funcionales consideradas

- El Investigador solo consulta su carga de trabajo propia.
- No existe seleccion de persona ni filtros globales en este caso.
- Si el Investigador es investigador-docente segun su sede, el sistema muestra su margen respecto a 16 horas semanales de docencia.
- Si pertenece a una sede donde solo es investigador, el sistema no calcula margen docente ni permite acceder a recompensas por docencia.
- Las recompensas no se generan desde carga de trabajo; aparecen cuando se completa un proyecto y se elige entre recompensa economica o reduccion docente posterior.
- El caso no modifica datos; solo presenta informacion y opciones.
- La edicion posterior tambien debe operar sobre el usuario autenticado.

## Referencias

- [Especificacion detallada](/RUP/00-casos-uso/02-detalle/investigador/abrirOpcionesCargaTrabajo/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [conversation-log.md](/conversation-log.md)
