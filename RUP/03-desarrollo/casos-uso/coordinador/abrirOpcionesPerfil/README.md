# FUNIBER > Coordinador > abrirOpcionesPerfil > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirOpcionesPerfil/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirOpcionesPerfil/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirOpcionesPerfil/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/abrirOpcionesPerfil/README.md)|
> |-|-|-|-|-|-|-|

## Implementación

El perfil se abre desde el módulo `Perfil` del panel principal. El frontend consulta `/api/perfil`, presenta los datos actuales y habilita las acciones de edición, solicitud de eliminación y revisión de solicitudes pendientes para el Coordinador.

## Código relacionado

- [`PerfilPage.tsx`](/src/frontend/src/pages/PerfilPage.tsx)
- [`api.ts`](/src/frontend/src/services/api.ts)
- [`PerfilController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/PerfilController.java)
- [`PerfilService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/PerfilService.java)
- [`Usuario.java`](/src/backend/src/main/java/es/funiber/investigacion/model/Usuario.java)
