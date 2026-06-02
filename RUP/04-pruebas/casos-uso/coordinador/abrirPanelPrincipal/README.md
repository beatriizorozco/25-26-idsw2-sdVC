# FUNIBER > Coordinador > abrirPanelPrincipal > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirPanelPrincipal/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirPanelPrincipal/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirPanelPrincipal/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirPanelPrincipal/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Objetivo

Comprobar que una sesión válida de Coordinador recibe su panel principal y las acciones autorizadas para su rol.

## Casos verificados

|Caso|Resultado esperado|Evidencia|
|-|-|-|
|Abrir panel como Coordinador|Respuesta `200` con rol `COORDINADOR`|`coordinadorRecibeConvocatoriasEnSuPanel()`|
|Consultar Convocatorias|La acción `convocatorias` está disponible|`coordinadorRecibeConvocatoriasEnSuPanel()`|

## Evidencia visual

|Panel principal del Coordinador|
|-|
|![Panel principal del Coordinador](/images/RUP/04-pruebas/coordinador-abrirPanelPrincipal.png)|

