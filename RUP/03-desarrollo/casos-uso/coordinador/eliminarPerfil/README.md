# FUNIBER > Coordinador > eliminarPerfil > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/eliminarPerfil/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/eliminarPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Implementación

La desactivación requiere confirmación. El frontend envía `DELETE /api/solicitudes-eliminacion-perfil/{idSolicitud}/perfil`; el backend exige rol Coordinador, desactiva el perfil y marca la solicitud como resuelta. El uso de `DELETE` representa la baja funcional, no un borrado físico. Si la solicitud corresponde al único Coordinador activo, la desactivación se rechaza para no dejar el sistema sin coordinación.

## Código relacionado

- [`PerfilPage.tsx`](/src/frontend/src/pages/PerfilPage.tsx)
- [`SolicitudEliminacionPerfilController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/SolicitudEliminacionPerfilController.java)
- [`PerfilService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/PerfilService.java)
- [`DesactivacionPerfilResponse.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/DesactivacionPerfilResponse.java)
