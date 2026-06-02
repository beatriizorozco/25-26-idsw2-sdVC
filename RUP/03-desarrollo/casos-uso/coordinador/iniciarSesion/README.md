# FUNIBER > Coordinador > iniciarSesion > Desarrollo

> |[🏠️](/README.md)|[📊](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/iniciarSesion/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/iniciarSesion/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/iniciarSesion/README.md)|**Desarrollo**|[Pruebas](/RUP/04-pruebas/casos-uso/coordinador/iniciarSesion/README.md)|
> |-|-|-|-|-|-|-|

## Implementación

El acceso se implementa mediante `LoginPage`, `AuthController`, `AuthService`, `UsuarioRepository` y `SesionService`. El frontend obtiene un token CSRF, envía las credenciales y renueva el token después de crear la sesión.

## Código relacionado

- [`LoginPage.tsx`](/src/frontend/src/pages/LoginPage.tsx)
- [`api.ts`](/src/frontend/src/services/api.ts)
- [`AuthController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/AuthController.java)
- [`AuthService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/AuthService.java)
- [`SesionService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/SesionService.java)

