# FUNIBER > Coordinador > abrirOpcionesCargaTrabajo > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesCargaTrabajo/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirOpcionesCargaTrabajo/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Estado

Verificado mediante `CargaTrabajoIntegrationTests.coordinadorPuedeAbrirOpcionesDeCargaTrabajo`.

## Resultado

- Se valida que el Coordinador puede consultar `GET /api/carga-trabajo`.
- Se comprueba que la respuesta incluye el máximo docente semanal de 16 horas.
- Se comprueba que el listado incluye perfiles de carga y sugerencias para proyectos libres.
- Se valida que las sugerencias priorizan investigadores-docentes disponibles, como `docente.santander`.

## Evidencia manual

- Se revisó en navegador el panel global de cargas del Coordinador.
- Se confirmó que muestra personas activas, investigadores-docentes y proyectos libres.
- Se confirmó que las sugerencias no dependen de recompensas por exceso de carga, sino de disponibilidad para proyectos.

