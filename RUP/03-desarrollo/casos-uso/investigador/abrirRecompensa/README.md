# FUNIBER > Investigador > abrirRecompensa > Desarrollo

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/investigador/abrirRecompensa/README.md)|[Análisis](/RUP/01-analisis/casos-uso/investigador/abrirRecompensa/README.md)|[Diseño](/RUP/02-diseño/casos-uso/investigador/abrirRecompensa/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

## Implementación

`GET /api/recompensas/me/{id}` presenta el detalle solo cuando el Investigador autenticado es beneficiario. Una recompensa ajena devuelve `403` y la interfaz no ofrece acciones CRUD.

## Código relacionado

- [`RecompensasPage.tsx`](/src/frontend/src/pages/RecompensasPage.tsx)
- [`RecompensaController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/RecompensaController.java)
- [`RecompensaIntegrationTests.java`](/src/backend/src/test/java/es/funiber/investigacion/controller/RecompensaIntegrationTests.java)
