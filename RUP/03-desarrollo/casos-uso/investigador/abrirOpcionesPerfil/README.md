# FUNIBER > Investigador > abrirOpcionesPerfil > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirOpcionesPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirOpcionesPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirOpcionesPerfil/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/investigador/abrirOpcionesPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Implementación

El Investigador abre su perfil desde el módulo `Perfil`. El frontend consulta `/api/perfil` y presenta sus datos sin acceso a la revisión de solicitudes de otros perfiles.

## Código relacionado

- [`PerfilPage.tsx`](/src/frontend/src/pages/PerfilPage.tsx)
- [`api.ts`](/src/frontend/src/services/api.ts)
- [`PerfilController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/PerfilController.java)
- [`PerfilService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/PerfilService.java)
