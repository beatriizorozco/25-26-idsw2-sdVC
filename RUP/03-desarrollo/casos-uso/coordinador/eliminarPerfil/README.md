# FUNIBER > Coordinador > eliminarPerfil > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/eliminarPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/eliminarPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/eliminarPerfil/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/eliminarPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Implementación

La eliminación requiere confirmación. El frontend envía `DELETE /api/solicitudes-eliminacion-perfil/{idSolicitud}/perfil`; el backend exige rol Coordinador, desactiva el perfil y marca la solicitud como resuelta.

## Código relacionado

- [`PerfilPage.tsx`](/src/frontend/src/pages/PerfilPage.tsx)
- [`SolicitudEliminacionPerfilController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/SolicitudEliminacionPerfilController.java)
- [`PerfilService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/PerfilService.java)
- [`EliminacionPerfilResponse.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/EliminacionPerfilResponse.java)
