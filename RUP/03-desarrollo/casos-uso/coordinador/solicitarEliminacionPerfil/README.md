# FUNIBER > Coordinador > solicitarEliminacionPerfil > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/solicitarEliminacionPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/solicitarEliminacionPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/solicitarEliminacionPerfil/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/solicitarEliminacionPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Implementación

La solicitud se confirma en un modal. Al confirmar, el frontend envía `POST /api/perfil/solicitud-eliminacion`, el backend registra una solicitud pendiente y cierra la sesión del usuario solicitante.

## Código relacionado

- [`PerfilPage.tsx`](/src/frontend/src/pages/PerfilPage.tsx)
- [`PerfilController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/PerfilController.java)
- [`SolicitudEliminacionPerfil.java`](/src/backend/src/main/java/es/funiber/investigacion/model/SolicitudEliminacionPerfil.java)
- [`SolicitudEliminacionPerfilRepository.java`](/src/backend/src/main/java/es/funiber/investigacion/repository/SolicitudEliminacionPerfilRepository.java)
- [`V2__perfil_y_solicitudes_eliminacion.sql`](/src/backend/src/main/resources/db/migration/V2__perfil_y_solicitudes_eliminacion.sql)
