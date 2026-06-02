# FUNIBER > Investigador > abrirPanelPrincipal > Diseño

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirPanelPrincipal/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirPanelPrincipal/README.md)|**Diseño**|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirPanelPrincipal/README.md)|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/abrirPanelPrincipal/README.md)|
> |-|-|-|-|-|-|-|

## Propósito

Detallar la obtención y presentación del panel principal del Investigador. La API recupera el rol de la sesión activa y devuelve exclusivamente las acciones autorizadas para este actor.

## Diagrama de secuencia

|![Diseño: abrirPanelPrincipal()](/images/RUP/02-diseño/casos-uso/investigador/abrirPanelPrincipal/secuencia.svg)|
|-|
|Código fuente: [secuencia.puml](secuencia.puml)|

## Participantes

- **PanelPrincipalPage**: Solicita y presenta las acciones del panel.
- **PanelPrincipalController**: Expone `GET /api/panel-principal`.
- **SesionService**: Recupera el usuario y el rol activos.
- **PanelPrincipalService**: Devuelve las acciones habilitadas para `INVESTIGADOR`.

## Decisiones de Diseño

- El panel no se persiste como entidad; se calcula desde el rol activo.
- El Investigador no recibe acceso a convocatorias ni a operaciones globales de coordinación.
- La carga de trabajo mostrada al Investigador corresponde a su información propia.
- Si la sesión está ausente o ha caducado, la API responde `401 Unauthorized` y el frontend presenta `SESION_CERRADA`.

## Referencias

- [Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirPanelPrincipal/README.md)
- [Análisis](/RUP/01-analisis/casos-uso/investigador/abrirPanelPrincipal/README.md)
