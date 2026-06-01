# FUNIBER > Coordinador > cerrarSesion > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/cerrarSesion/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/cerrarSesion/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/cerrarSesion/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

## Implementación

El cierre comienza en un modal de confirmación. Cancelar conserva el panel; confirmar envía `POST /api/auth/logout` con CSRF e invalida la sesión.

## Código relacionado

- [`ConfirmarCierreSesionModal.tsx`](/src/frontend/src/components/ConfirmarCierreSesionModal.tsx)
- [`api.ts`](/src/frontend/src/services/api.ts)
- [`AuthController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/AuthController.java)
- [`SesionService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java)

