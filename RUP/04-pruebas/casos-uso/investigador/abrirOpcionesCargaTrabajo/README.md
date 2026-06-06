# FUNIBER > Investigador > abrirOpcionesCargaTrabajo > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirOpcionesCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/investigador/abrirOpcionesCargaTrabajo/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Estado

Verificado mediante `CargaTrabajoIntegrationTests.investigadorNoPuedeAbrirPanelGlobalDeCargaTrabajo` y pruebas de panel en `FlujoSesionIntegrationTests`.

## Resultado

- Se valida que el Investigador no puede acceder al panel global de cargas.
- Se confirma que el Investigador consulta solo su propia carga mediante `GET /api/carga-trabajo/me`.
- Se comprueba que `investigador.barcelona` recibe acceso de consulta a recompensas económicas aunque no tenga docencia por sede.
- Se comprueba que `docente.santander` recibe acceso de consulta a recompensas económicas o de reducción docente.

## Evidencia manual

- Se revisó en navegador la carga propia de `docente.santander`, con límite de 16 horas docentes.
- Se revisó en navegador la carga propia de `investigador.barcelona`, con docencia 0 y estado no aplicable por sede.
- Se confirmó que el panel principal de `investigador.barcelona` muestra acceso de consulta a recompensas, sin permitir operaciones globales.
