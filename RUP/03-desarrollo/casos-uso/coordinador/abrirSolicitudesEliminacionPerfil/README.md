# FUNIBER > Coordinador > abrirSolicitudesEliminacionPerfil > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirSolicitudesEliminacionPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Implementación

El Coordinador consulta las solicitudes pendientes desde el panel de perfil. El frontend invoca `GET /api/solicitudes-eliminacion-perfil` y permite filtrar por usuario, nombre o motivo.

## Código relacionado

- [`PerfilPage.tsx`](/src/frontend/src/pages/PerfilPage.tsx)
- [`SolicitudEliminacionPerfilController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/SolicitudEliminacionPerfilController.java)
- [`PerfilService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/PerfilService.java)
- [`SolicitudEliminacionPerfilRepository.java`](/src/backend/src/main/java/es/funiber/investigacion/repository/SolicitudEliminacionPerfilRepository.java)
