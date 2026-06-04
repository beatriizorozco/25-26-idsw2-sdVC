# FUNIBER > Coordinador > abrirSolicitudesEliminacionPerfil > Pruebas

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|[Desarrollo](/RUP/03-desarrollo/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|**Pruebas**|
> |-|-|-|-|-|-|-|

## Estado

Verificado mediante `PerfilIntegrationTests.coordinadorPuedeListarSolicitudesPendientes` y `PerfilIntegrationTests.investigadorNoPuedeListarSolicitudesDeEliminacion`.

## Resultado

- Se valida que el Coordinador puede listar solicitudes pendientes.
- Se valida que el Investigador recibe `403 Forbidden` al intentar listar solicitudes.
- Se comprueba que las solicitudes resueltas dejan de aparecer en el listado pendiente.
- Se completa evidencia visual manual del listado filtrable con una solicitud pendiente del Investigador.

## Evidencia visual

|Listado de solicitudes pendientes|
|-|
|![Listado de solicitudes pendientes](/images/RUP/04-pruebas/coordinador-listado-solicitudes-eliminacion.png)|
