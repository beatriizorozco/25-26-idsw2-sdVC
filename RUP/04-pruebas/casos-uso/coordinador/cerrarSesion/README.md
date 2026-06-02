# FUNIBER > Coordinador > cerrarSesion > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/cerrarSesion/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/cerrarSesion/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/cerrarSesion/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/cerrarSesion/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Objetivo

Comprobar que el Coordinador puede cancelar el cierre y permanecer en `PANEL_PRINCIPAL_ABIERTO`, o confirmarlo para volver a `SESION_CERRADA`.

## Casos verificados

|Caso|Resultado esperado|Evidencia|
|-|-|-|
|Confirmar cierre|La sesión queda invalidada|`cerrarSesionImpideVolverAConsultarElPanel()`|
|Consultar panel después del cierre|Respuesta `401 Unauthorized`|`cerrarSesionImpideVolverAConsultarElPanel()`|

## Comprobación visual pendiente

- Abrir el modal, pulsar Cancelar y confirmar que el panel permanece visible.
- Abrir de nuevo el modal, confirmar el cierre y comprobar que vuelve el formulario de acceso.

## Evidencia visual

|Confirmación de cierre del Coordinador|
|-|
|![Confirmación de cierre del Coordinador](/images/RUP/04-pruebas/coordinador-cerrarSesion-confirmacion.png)|

