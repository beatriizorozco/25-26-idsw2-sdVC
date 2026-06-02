# FUNIBER > Investigador > abrirPanelPrincipal > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirPanelPrincipal/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirPanelPrincipal/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirPanelPrincipal/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirPanelPrincipal/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Objetivo

Comprobar que una sesión válida de Investigador recibe únicamente las acciones autorizadas para su rol.

## Casos verificados

|Caso|Resultado esperado|Evidencia|
|-|-|-|
|Abrir panel como Investigador|Respuesta `200` con rol `INVESTIGADOR`|`investigadorNoRecibeConvocatoriasEnSuPanel()`|
|Consultar Convocatorias|La acción `convocatorias` no está disponible|`investigadorNoRecibeConvocatoriasEnSuPanel()`|

## Evidencia visual

|Panel principal del Investigador|
|-|
|![Panel principal del Investigador](/images/RUP/04-pruebas/investigador-abrirPanelPrincipal.png)|

