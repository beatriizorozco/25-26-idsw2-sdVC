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
- Mostrar docencia, investigacion, actividades academicas, total semanal y margen docente.
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

### RecompensaRepository
**Estereotipo**: Entidad

**Responsabilidades**:
- Consultar si el Investigador tiene compensacion pendiente por exceso docente.
- Mantener la relacion entre carga de trabajo y recompensa economica.

### CargaTrabajo
**Estereotipo**: Entidad

**Responsabilidades**:
- Representar horas de docencia, investigacion y actividades academicas.
- Mantener total semanal, margen docente y observaciones.
- Calcular margen o exceso respecto al limite de 16 horas semanales de docencia.

### Recompensa
**Estereotipo**: Entidad

**Responsabilidades**:
- Representar la compensacion economica pendiente si la docencia supera el limite.

## Flujo de colaboracion

1. `PANEL_PRINCIPAL_ABIERTO` envia `abrirOpcionesCargaTrabajo()` a `CargaTrabajoPropiaView`.
2. La vista solicita `obtenerCargaPropia(investigador)` al controlador.
3. La vista solicita `prepararAccionesPropias(investigador)`.
4. El controlador consulta `CargaTrabajoRepository.obtenerPorUsuario(investigador)`.
5. El controlador solicita `calcularResumenPersonal(cargaTrabajo)`.
6. El controlador consulta `RecompensaRepository.obtenerCompensacionPendiente(investigador)`.
7. La vista presenta `OPCIONES_CARGA_TRABAJO_ABIERTAS`.
8. El Investigador puede navegar a `editarCargaTrabajo()` o volver al panel.

## Correspondencia con requisitos

|Requisito del caso de uso|Clase responsable|Metodo/Colaboracion|
|-|-|-|
|Mostrar carga propia|`CargaTrabajoController`|`obtenerCargaPropia(investigador)`|
|Mostrar resumen personal|`CargaTrabajoRepository`|`calcularResumenPersonal(cargaTrabajo)`|
|Mostrar compensacion pendiente|`RecompensaRepository`|`obtenerCompensacionPendiente(investigador)`|
|Restringir consulta a usuario autenticado|`CargaTrabajoController`|`prepararAccionesPropias(investigador)`|
|Permitir edicion propia|`CargaTrabajoPropiaView`|Navega a `editarCargaTrabajo()`|
|Volver al panel|`CargaTrabajoPropiaView`|Navega a `abrirPanelPrincipal()`|

## Reglas funcionales consideradas

- El Investigador solo consulta su carga de trabajo propia.
- No existe seleccion de persona ni filtros globales en este caso.
- Si el Investigador es investigador-docente, el sistema muestra su margen respecto a 16 horas semanales de docencia.
- Si supera 16 horas semanales de docencia, el sistema puede mostrar que existe compensacion economica pendiente.
- El caso no modifica datos; solo presenta informacion y opciones.
- La edicion posterior tambien debe operar sobre el usuario autenticado.

## Referencias

- [Especificacion detallada](/RUP/00-casos-uso/02-detalle/investigador/abrirOpcionesCargaTrabajo/README.md)
- [Diagramas de contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)
- [Modelo del dominio](/RUP/00-casos-uso/00-modelo-del-dominio/modelo-dominio.md)
- [conversation-log.md](/conversation-log.md)
