# FUNIBER > Coordinador > editarPerfil > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/editarPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/editarPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/editarPerfil/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/editarPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Implementación

La edición usa un formulario precargado con `PerfilResponse`. Al guardar, el frontend envía `PATCH /api/perfil` con `PerfilUpdateRequest`; el backend valida la sesión, actualiza los campos del usuario y devuelve el perfil actualizado.

## Código relacionado

- [`PerfilPage.tsx`](/src/frontend/src/pages/PerfilPage.tsx)
- [`api.ts`](/src/frontend/src/services/api.ts)
- [`PerfilController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/PerfilController.java)
- [`PerfilService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/PerfilService.java)
- [`PerfilUpdateRequest.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/PerfilUpdateRequest.java)
