# FUNIBER > Coordinador > abrirRecompensas > Desarrollo

> |[Inicio](/README.md)|[Contexto](/RUP/00-casos-uso/01-actores-casos-uso/diagramas-contexto.md)|[Detalle](/RUP/00-casos-uso/02-detalle/coordinador/abrirRecompensas/README.md)|[Análisis](/RUP/01-analisis/casos-uso/coordinador/abrirRecompensas/README.md)|[Diseño](/RUP/02-diseño/casos-uso/coordinador/abrirRecompensas/README.md)|**Desarrollo**|Pruebas|
> |-|-|-|-|-|-|-|

## Implementación

El Coordinador consulta `GET /api/recompensas` y `RecompensasPage` presenta el listado global, filtrado y acceso al detalle.

## Código relacionado

- [`RecompensasPage.tsx`](/src/frontend/src/pages/RecompensasPage.tsx)
- [`RecompensaController.java`](/src/backend/src/main/java/es/funiber/investigacion/controller/RecompensaController.java)
- [`RecompensaService.java`](/src/backend/src/main/java/es/funiber/investigacion/service/RecompensaService.java)
