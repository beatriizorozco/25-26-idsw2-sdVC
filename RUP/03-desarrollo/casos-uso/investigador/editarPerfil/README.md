# FUNIBER > Investigador > editarPerfil > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/editarPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/editarPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/editarPerfil/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/editarPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Implementación

El formulario se precarga con el perfil propio y guarda cambios mediante `PATCH /api/perfil`. El backend solo permite modificar el perfil de la sesión actual para este rol.

## Código relacionado

- [`PerfilPage.tsx`](/src/frontend/src/pages/PerfilPage.tsx)
- [`PerfilController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/PerfilController.java)
- [`PerfilService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/PerfilService.java)
- [`PerfilUpdateRequest.java`](/src/backend/src/main/java/es/funiber/investigacion/dto/PerfilUpdateRequest.java)
