# FUNIBER > Coordinador > abrirSolicitudEliminacionPerfil > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirSolicitudEliminacionPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirSolicitudEliminacionPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirSolicitudEliminacionPerfil/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirSolicitudEliminacionPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Implementación

El detalle se abre al seleccionar una solicitud pendiente. El frontend invoca `GET /api/solicitudes-eliminacion-perfil/{id}` y presenta la información necesaria para cancelar o confirmar la eliminación.

## Código relacionado

- [`PerfilPage.tsx`](/src/frontend/src/pages/PerfilPage.tsx)
- [`SolicitudEliminacionPerfilController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/SolicitudEliminacionPerfilController.java)
- [`SolicitudEliminacionPerfilResponse.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/SolicitudEliminacionPerfilResponse.java)
